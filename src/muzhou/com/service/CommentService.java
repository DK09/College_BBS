package muzhou.com.service;

import muzhou.com.bean.CommentBean;

import javax.xml.stream.events.Comment;

public interface CommentService {
    public void addComment(CommentBean comment);
    public CommentBean getCommentByKeywords(String keywords);
    public CommentBean getCommentById(int uid);
    public void deleteCommentById(int Cid);
}
