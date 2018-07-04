package muzhou.com.service;

import muzhou.com.bean.UserBean;

public interface AnswerService {
    public void addUser(UserBean user);
    public UserBean getUser(String userName);
    public UserBean getUserById(int userId);
}
