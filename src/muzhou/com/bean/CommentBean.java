package muzhou.com.bean;

import java.sql.Timestamp;

public class CommentBean {

    private int cid;
    private int userId;
    private int aid;
    private int recId;
    private String content;
    private Timestamp time;//
    private int likeCount;
    private int unlikeCount;
    private String visible;

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getAid() {
        return aid;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public int getRecId() {
        return recId;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setUnlikeCount(int unlikeCount) {
        this.unlikeCount = unlikeCount;
    }

    public int getUnlikeCount() {
        return unlikeCount;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "cid=" + cid +
                ", userId=" + userId +
                ", aid=" + aid +
                ", recId=" + recId +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", likeCount=" + likeCount +
                ", unlikeCount=" + unlikeCount +
                ", visible='" + visible + '\'' +
                '}';
    }
}
