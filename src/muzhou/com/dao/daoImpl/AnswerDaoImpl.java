package muzhou.com.dao.daoImpl;

import muzhou.com.bean.AnswerBean;
import muzhou.com.dao.AnswerDao;

public class AnswerDaoImpl extends DAO<AnswerBean> implements AnswerDao {
    public void addAnswer(AnswerBean answer){
        String sql = "INSERT INTO question(aid,userid,qid,content,time,likeCount,unlikeCount,visible) values(?,?,?,?,?,?,?,?)";
        update(sql,answer.getAid(),answer.getUserId(),answer.getQid(),answer.getContent(),answer.getTime(),answer.getLikeCount(),answer.getUnlikeCount(),answer.getVisible());
    }
    public AnswerBean getAnswer(String keywords){
        /**
         *
         *
         * 此处尚未添加查询代码！！！
         *
         *
         *
         */
        return null;
    }

    public AnswerBean getAnswerById(int uid){
        String sql = "SELECT * FROM answer WHERE userid = ?";
        return get(sql,uid);
    }; //用于查询用户所属的

    public void deleteAnswer(int Aid){
        String sql = "DELETE  FROM answer WHERE aid = ?";    //从answer表删除
        update(sql,Aid);
    }

}
