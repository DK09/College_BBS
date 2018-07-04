package muzhou.com.service;

import muzhou.com.bean.UserBean;
import muzhou.com.dao.UserDao;

public interface UserService {

    public void addUser(UserBean user);
    public UserBean getUser(String userName);
    public UserBean getUserById(int userId);
}
