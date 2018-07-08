package muzhou.com.dao;

import muzhou.com.bean.AnswerBean;

public interface AnswerDao {
    public void addAnswer(AnswerBean answer);
    public AnswerBean getAnswer(String keywords);//用于查询
    public AnswerBean getAnswerById(int uid); //用于查询用户所属的
    public void deleteAnswer(int Aid);
}
