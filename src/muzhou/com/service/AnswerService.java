package muzhou.com.service;

import muzhou.com.bean.AnswerBean;


public interface AnswerService {
    public void addAnswer(AnswerBean answer);
    public AnswerBean getAnswerByKeywords(String keywords);
    public AnswerBean getAnswerById(int uid);
    public void deleteAnswerById(int Aid);
}
