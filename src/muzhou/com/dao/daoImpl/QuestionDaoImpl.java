package muzhou.com.dao.daoImpl;

import muzhou.com.bean.PageBean;
import muzhou.com.bean.QuestionBean;
import muzhou.com.dao.QuestionDao;
import java.util.List;

public class QuestionDaoImpl extends DAO<QuestionBean> implements QuestionDao {
    public void addQuestion(QuestionBean question) {
        System.out.println("插入数据成功");
        String sql = "INSERT INTO question(userid,username,category,keyword,title,content,integral,likeCount, visitCount, bestAid, visible,solved) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        update(sql,question.getUserId(),question.getUsername(),question.getCategory(),question.getKeyword(),question.getTitle(),question.getContent(),question.getIntegral(),question.getLikeCount(),question.getVisitCount(),question.getBestAid(),question.getVisible(),question.getSolved());
    }

    public List<QuestionBean> getAllQuestion()
    {

        return getForList("select * from question");
    }

    public QuestionBean getQuestionBySearch(String keyword,String title,String content) {

        String sql = "select * from question where keyword = " + keyword + " and title = " + title + " and content = " + content;
        return get(sql);
    }
    public PageBean<QuestionBean> getQuestionByPage(int page)
    {
        //创建pageBean
        PageBean<QuestionBean> pageBean = new PageBean<>();

        //设置totalCount
        String sql = "select count(*) from question";
        Long totalCount =   getForValue(sql);
        pageBean.setTotalCount(totalCount);

        //设置总页数
       int totalPage = (int)(totalCount % pageBean.getPageCount() == 0 ? totalCount / pageBean.getPageCount() : totalCount / pageBean.getPageCount() + 1);
       pageBean.setTotalPage(totalPage);

       //设置当前页
       pageBean.setCurrentPage(page);

       //设置pageBean里的list数据【按积分从高到低排序】
        String sql1 = "select * from question order by integral DESC limit ?,?";
        int start = (page - 1) * pageBean.getPageCount();
        List<QuestionBean>questionBeans =   getForList(sql1,start,pageBean.getPageCount());
        pageBean.setList(questionBeans);
        return pageBean;
    }

    public PageBean<QuestionBean> getQuestionByPageAndSolved(int page,int solved)
    {
        //创建pageBean
        PageBean<QuestionBean> pageBean = new PageBean<>();

        //设置totalCount
        String sql = "select count(*) from question where solved = "+solved;
        Long totalCount = getForValue(sql);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        int totalPage = (int)(totalCount % pageBean.getPageCount() == 0 ? totalCount / pageBean.getPageCount() : totalCount / pageBean.getPageCount() + 1);
        pageBean.setTotalPage(totalPage);

        //设置当前页
        pageBean.setCurrentPage(page);

        //设置pageBean里的list数据【按积分从高到低排序】
        String sql1 = "select * from question where solved = "+solved+" order by integral DESC limit ?,?" ;
        int start = (page - 1) * pageBean.getPageCount();
        List<QuestionBean>questionBeans =   getForList(sql1,start,pageBean.getPageCount());
        pageBean.setList(questionBeans);
        return pageBean;
    }

    public PageBean<QuestionBean> getQuestionByPageAndSolvedAndMajor(int page,int solved,String major)
    {
        //创建pageBean
        PageBean<QuestionBean> pageBean = new PageBean<>();
        System.out.println("我被调用了");
        //设置totalCount
        String sql = "select count(*) from question where solved = "+solved+" and category = "+"'"+major+"'";
        Long totalCount = getForValue(sql);
        pageBean.setTotalCount(totalCount);
        System.out.println(totalCount);
        //设置总页数
        int totalPage = (int)(totalCount % pageBean.getPageCount() == 0 ? totalCount / pageBean.getPageCount() : totalCount / pageBean.getPageCount() + 1);
        pageBean.setTotalPage(totalPage);

        //设置当前页
        pageBean.setCurrentPage(page);

        //设置pageBean里的list数据【按积分从高到低排序】
        String sql1 = "select * from question where solved = "+solved+" and category = "+"'"+major+"'"+" order by integral DESC limit ?,?" ;
        int start = (page - 1) * pageBean.getPageCount();
        List<QuestionBean>questionBeans =   getForList(sql1,start,pageBean.getPageCount());
        pageBean.setList(questionBeans);
        return pageBean;
    }
    public QuestionBean getQuestionById(int qid){
        String sql = "SELECT * FROM question WHERE qid = ?";
        return get(sql,qid);
    };//用于查询用户所属的

    public void deleteQuestion(int Qid){
        String sql = "DELETE  FROM question WHERE qid = ?";    //从question表删除
        update(sql,Qid);
    }


}
