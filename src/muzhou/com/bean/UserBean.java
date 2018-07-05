package muzhou.com.bean;

public class UserBean {

    private int userId;
    private String userName;
    private String passWord;
    private String email;
    private String level;
    private int contribute;
    private String school;
    private String major;
    private String telephone;

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setContribute(int contribute) {
        this.contribute = contribute;
    }

    public int getContribute() {
        return contribute;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "UserBean [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", email="
                + email + ", level="+ level +", contribute=" + contribute + ", school=" + school + ", major="+ major +", telephone"+ telephone + "]";
    }
}
