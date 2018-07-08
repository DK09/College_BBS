package muzhou.com.dao.daoImpl;

import muzhou.com.bean.CommentBean;
import muzhou.com.dao.CommentDao;

public class CommentDaoImpl extends DAO<CommentBean> implements CommentDao {
    public void addComment(CommentBean comment){
        String sql = "INSERT INTO question(cid,userid,aid,recid,content,time,likeCount,unlikeCount,visible) values(?,?,?,?,?,?,?,?,?)";
        update(sql,comment.getCid(),comment.getUserId(),comment.getAid(),comment.getRecId(),comment.getContent(),comment.getTime(),comment.getLikeCount(),comment.getUnlikeCount(),comment.getVisible());
    }
    public CommentBean getComment(String keywords){
        /**
         *
         *
         * 此处尚未添加查询代码！！！
         *
         *
         */
        return null;
    }//用于查询

    public CommentBean getCommentById(int uid){
        String sql = "SELECT * FROM comment WHERE userid = ?";
        return get(sql,uid);
    }; //用于查询用户所属的

    public void deleteComment(int Cid){
        String sql = "DELETE  FROM comment WHERE cid = ?";    //从answer表删除
        update(sql,Cid);
    }
}
