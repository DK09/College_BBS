package muzhou.com.service;

import muzhou.com.bean.PageBean;
import muzhou.com.bean.QuestionBean;

import java.util.List;


public interface QuestionService {
    public void addQuestion(QuestionBean question);
    public List<QuestionBean> getAllQuestion();
    public QuestionBean getQuestionBySearch(String keyword,String title,String content);
    public QuestionBean getQuestionById(int qid);
    public void deleteQuestionById(int Qid);
    public PageBean<QuestionBean> getQuestionByPage(int page);
    public PageBean<QuestionBean> getQuestionByPageAndSolved(int page,int solved);
    public PageBean<QuestionBean> getQuestionByPageAndSolvedAndMajor(int page,int solved,String major);
}
