package muzhou.com.utils;

import java.io.*;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Content;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class Index {
    public static final String DIR_PATH = "D:\\index\\test";
    public List<Content> contents = new ArrayList<>();
    private final int CONTENT_LENGTH = 60; //高亮最佳匹配句子长度
    private final int MAX_SAERCH_COUNT = 999999; //最大选取个数
    public static final String TITLE = "title", KEYWORD = "keyword",
            CONTENT = "content", SEARCH_FIELD = "searchField", ID = "id";

    /***
     * 获取indxeWriter
     * @param dir 索引存放目录
     * @return IndexWriter
     */
    private IndexWriter getIndexWriter(String dir) {
        try {
            FSDirectory directory = FSDirectory.open(Paths.get(dir));
            Analyzer analyzer = new SmartChineseAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, iwc);
            return indexWriter;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 写入信息到索引
     * contents中第一个应为title，第二个应为keywords，第三为content
     * @param dirPath 索引目录位置
     * @param id 当前Id
     * @param contents 对应各个Field写入的数据
     * @return 创建索引是否成功
     */
    public boolean writeToIndex(String dirPath, int id, String[] contents) {
        IndexWriter iw = getIndexWriter(dirPath);
        Document doc = new Document();
        doc.add(new TextField(ID, String.valueOf(id), Field.Store.YES));
        doc.add(new TextField(TITLE, contents[0], Field.Store.YES));
        int length = contents[2].length() > CONTENT_LENGTH ? CONTENT_LENGTH : contents[2].length();
        doc.add(new TextField(CONTENT, contents[2].substring(0, length) +"...", Field.Store.YES));
        doc.add(new TextField(SEARCH_FIELD, contents[0] + contents[0]
                + contents[0] + contents[1] + contents[2], Field.Store.NO));
        try {
            iw.addDocument(doc);
            iw.commit();
            iw.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入信息到索引,目录为默认目录
     * @param id 当前Id
     * @param fields 写入数据的各个Field名
     * @param contents 对应各个Field写入的数据
     * @return 创建索引是否成功
     */
    public boolean writeToIndex(int id, String[] fields, String[] contents) {
        return writeToIndex(DIR_PATH, id, contents);
    }

    /**
     * 把数据库中全部数据写入索引
     * @param dirPath 索引目录位置
     * @param rs 数据库查询结果集
     * @return 创建索引是否成功
     */
    public boolean writeAllToIndex(String dirPath, ResultSet rs) {
        try {
            IndexWriter iw = getIndexWriter(dirPath);
            int i = 0;
            while (rs.next()) {
                Document doc = new Document();
                doc.add(new TextField(ID, String.valueOf(rs.getInt(1)), Field.Store.YES));
                String title = rs.getString("title");
                //标题存三次增加搜索准确性
                String keyword = rs.getString("keyword") + title + title;
                String content = rs.getString("content");
                doc.add(new TextField(TITLE, title, Field.Store.YES));
                int length = content.length() > CONTENT_LENGTH ? CONTENT_LENGTH : content.length();
                doc.add(new TextField(CONTENT, content.substring(0, length) + "...", Field.Store.YES));
                doc.add(new TextField(SEARCH_FIELD, title + keyword + content, Field.Store.NO));
                iw.addDocument(doc);
                i += keyword.length() + content.length() + title.length();
            }
            iw.commit();
            iw.close();
            System.out.println("搜索字符数:" + i);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 把数据库中全部数据写入索引，目录为默认目录；
     * @param rs 数据库查询结果集
     * @return 创建索引是否成功
     */
    public boolean writeAllToIndex(ResultSet rs) {
        return writeAllToIndex(DIR_PATH, rs);
    }

    private static Analyzer getAnalyzer() {
        return new SmartChineseAnalyzer();
    }

    /**
     * 根据索引查询结果
     * @param dirPath 索引目录位置
     * @param queryString 查询的语句/关键字
     * @param startPage 开始项的编号(分页使用)
     * @param pageCount 一页的结果个数
     * @return 查询结果id数组
     */
    public int getSeachResult(String dirPath, String queryString, int startPage,
                                         int pageCount) {
        int result = 0;
        try {
            Directory dir = FSDirectory.open(Paths.get(dirPath));
            IndexReader indexReader = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(indexReader);

            Analyzer analyzer = getAnalyzer();
            QueryParser parser = new QueryParser(SEARCH_FIELD, analyzer);
            Query query = parser.parse(queryString);

            long start = System.currentTimeMillis();
            TopDocs hits = is.search(query, MAX_SAERCH_COUNT);
            long mid = System.currentTimeMillis();

            result = hits.totalHits;

            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));

            contents.clear();

            for (int i = 0; i + startPage * pageCount < hits.totalHits && i < pageCount; i++) {
                Document doc = is.doc(hits.scoreDocs[i + startPage * pageCount].doc);
                Content content = new Content();
                content.setId(Integer.parseInt(doc.get(ID)));

                TokenStream tokenStream = analyzer.tokenStream(CONTENT, new StringReader(doc.get(CONTENT)));
                String s = highlighter.getBestFragment(tokenStream, doc.get(CONTENT));
                if (s == null) s = doc.get(CONTENT);
                content.setContent(s);

                tokenStream = analyzer.tokenStream(CONTENT, new StringReader(doc.get(TITLE)));
                s = highlighter.getBestFragment(tokenStream, doc.get(TITLE));
                if (s == null) s = doc.get(TITLE);
                content.setTitle(s);
                contents.add(content);
            }
            indexReader.close();
            dir.close();

            long end = System.currentTimeMillis();
            System.out.println("匹配 " + queryString + " ，总共花费" + (mid - start) + "," +
                    (end - mid) + "毫秒" + "查询到" + hits.totalHits + "个记录");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据索引查询结果，索引目录位置为默认位置
     * @param queryString 查询的语句/关键字
     * @param startPage 开始项的编号(分页使用)
     * @param pageCount 一页的结果个数
     * @return 查询结果id数组
     */
    public int getSeachResult(String queryString, int startPage, int pageCount) {
        return getSeachResult(DIR_PATH, queryString, startPage, pageCount);
    }


    /**
     * 删除所有索引
     * @param dirPath 索引目录位置
     * @return 删除是否成功
     */
    public boolean deleteAllIndex(String dirPath) {
        try {
            IndexWriter indexWriter = getIndexWriter(dirPath);
            indexWriter.deleteAll();
            indexWriter.commit();
            indexWriter.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除所有索引
     * @return 删除是否成功
     */
    public boolean deleteAllIndex() {
        return deleteAllIndex(DIR_PATH);
    }

    /**
     * 删除一个编号为id索引
     * @param dirPath 索引目录位置
     * @param id 数据的id
     * @return 删除是否成功
     */
    public boolean deleteIndex(String dirPath, int id) {
        try {
            IndexWriter indexWriter = getIndexWriter(dirPath);
            Term term = new Term("id", String.valueOf(id));
            indexWriter.deleteDocuments(term);
            indexWriter.commit();
            indexWriter.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除一个编号为id索引
     * @param id 数据的id
     * @return 删除是否成功
     */
    public boolean deleteIndex(int id) {
        return deleteIndex(DIR_PATH, id);
    }
}