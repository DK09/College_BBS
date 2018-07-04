package muzhou.com.dao;

import muzhou.com.bean.UserBean;

public interface UserDao {

    public void addUser(UserBean user);
    public UserBean getUser(String userName);
    public UserBean getUserById(int userId);
}
