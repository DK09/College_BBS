package muzhou.com.dao.daoImpl;

import muzhou.com.bean.QuestionBean;
import muzhou.com.dao.QuestionDao;

public class QuestionDaoImpl extends DAO<QuestionBean> implements QuestionDao {

    public void addQuestion(QuestionBean question)
    {
        String sql = "INSERT INTO question(qid,userid,category,keyword,title,content,time,integral,likeCount,lastAnswerTime,answerCount,visitCount,bestAid,visible) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }
}
