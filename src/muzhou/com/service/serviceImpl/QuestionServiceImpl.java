package muzhou.com.service.serviceImpl;

import muzhou.com.bean.PageBean;
import muzhou.com.bean.QuestionBean;
import muzhou.com.service.QuestionService;

import java.util.List;


public class QuestionServiceImpl extends BaseService implements QuestionService {
    public void addQuestion(QuestionBean question)
    {
        questionDao.addQuestion(question);
    }
    public List<QuestionBean> getAllQuestion(){return questionDao.getAllQuestion();}
    public QuestionBean getQuestionBySearch(String keyword,String title,String content){return questionDao.getQuestionBySearch(keyword,title,content);}
    public QuestionBean getQuestionById(int qid){
        return questionDao.getQuestionById(qid);
    }
    public void deleteQuestionById(int Qid)
    {
        questionDao.deleteQuestion(Qid);
    }
    public PageBean<QuestionBean> getQuestionByPage(int page){return  questionDao.getQuestionByPage(page);}
    public PageBean<QuestionBean> getQuestionByPageAndSolved(int page,int solved){return questionDao.getQuestionByPageAndSolved(page,solved);}
    public PageBean<QuestionBean> getQuestionByPageAndSolvedAndMajor(int page,int solved,String major){return questionDao.getQuestionByPageAndSolvedAndMajor(page,solved,major);}
}
