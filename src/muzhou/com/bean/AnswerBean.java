package muzhou.com.bean;

import java.sql.Timestamp;

public class AnswerBean {

    private int aid;
    private int userId;
    private int qid;
    private String content;
    private Timestamp time;//
    private int likeCount;
    private int unlikeCount;
    private String visible;

    public void setAid(int aid) {
        this.aid = aid;
    }
    public int getAid() {
        return aid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getUnlikeCount() {
        return unlikeCount;
    }

    public void setUnlikeCount(int unlikeCount) {
        this.unlikeCount = unlikeCount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "AnswerBean{" +
                "aid=" + aid +
                ", userId=" + userId +
                ", qid=" + qid +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", likeCount=" + likeCount +
                ", unlikeCount=" + unlikeCount +
                ", visible='" + visible + '\'' +
                '}';
    }
}
