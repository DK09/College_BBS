package muzhou.com.service.serviceImpl;

import muzhou.com.bean.UserBean;
import muzhou.com.dao.UserDao;
import muzhou.com.dao.daoImpl.UserDaoImpl;
import muzhou.com.service.UserService;

public class UserServiceImpl extends BaseService implements UserService {

    public void addUser(UserBean user)
    {
        userDao.addUser(user);
    }
    public UserBean getUser(String userName)
    {
        return userDao.getUser(userName);
    }
    public UserBean getUserById(int userId)
    {
        return userDao.getUserById(userId);
    }
}
