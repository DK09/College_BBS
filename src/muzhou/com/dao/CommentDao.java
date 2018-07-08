package muzhou.com.dao;

import muzhou.com.bean.CommentBean;

public interface CommentDao {
    public void addComment(CommentBean comment);
    public CommentBean getComment(String keywords);//用于查询
    public CommentBean getCommentById(int uid); //用于查询用户所属的
    public void deleteComment(int Cid);
}
