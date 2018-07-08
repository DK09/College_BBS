package muzhou.com.dao;

import muzhou.com.bean.PageBean;
import muzhou.com.bean.QuestionBean;

import java.util.List;


public interface QuestionDao {
    public void addQuestion(QuestionBean question);
    public QuestionBean getQuestionBySearch(String keyword,String title,String content);//用于查询ALL
    public List<QuestionBean> getAllQuestion();
    public QuestionBean getQuestionById(int qid);
    public void deleteQuestion(int Qid);
    public PageBean<QuestionBean> getQuestionByPage(int page);
    public PageBean<QuestionBean> getQuestionByPageAndSolved(int page,int solved);
    public PageBean<QuestionBean> getQuestionByPageAndSolvedAndMajor(int page,int solved,String major);
}
