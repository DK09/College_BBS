package muzhou.com.service.serviceImpl;


import muzhou.com.bean.AnswerBean;
import muzhou.com.service.AnswerService;

public class AnswerServiceImpl extends BaseService implements AnswerService {
    public void addAnswer(AnswerBean answer){
        answerDao.addAnswer(answer);
    }
    public AnswerBean getAnswerByKeywords(String keywords){
        return answerDao.getAnswer(keywords);
    }
    public AnswerBean getAnswerById(int uid){
        return answerDao.getAnswerById(uid);
    }
    public void deleteAnswerById(int Aid){
        answerDao.deleteAnswer(Aid);
    }
}
