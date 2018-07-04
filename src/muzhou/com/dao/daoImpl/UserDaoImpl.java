package muzhou.com.dao.daoImpl;

import muzhou.com.bean.UserBean;
import muzhou.com.dao.UserDao;

public class UserDaoImpl extends DAO<UserBean> implements UserDao {
    public void addUser(UserBean user)
    {


            String sql = "INSERT INTO user(userid,password,username,email,level,contribute,school,major,telephone) values(?,?,?,?,?,?,?,?,?)";
            update(sql,user.getUserId(),user.getPassWord(),user.getUserName(),user.getEmail(),user.getLevel(),user.getContribute(),user.getSchool(),user.getMajor(),user.getTelephone());

    }
    public UserBean getUser(String userName)
    {

        String sql = "SELECT * FROM user WHERE username = ?";

        return get(sql,userName);
    }
    public UserBean getUserById(int userId)
    {
        String sql = "SELECT * FROM user WHERE userid = ?";
        return get(sql,userId);
    }
}
