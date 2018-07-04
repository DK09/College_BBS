package muzhou.com.service.serviceImpl;
import muzhou.com.dao.AnswerDao;
import muzhou.com.dao.CommentDao;
import muzhou.com.dao.QuestionDao;
import muzhou.com.dao.UserDao;

import muzhou.com.dao.daoImpl.AnswerDaoImpl;
import muzhou.com.dao.daoImpl.CommentDaoImpl;
import muzhou.com.dao.daoImpl.QuestionDaoImpl;
import muzhou.com.dao.daoImpl.UserDaoImpl;


public class BaseService {

    protected UserDao userDao = new UserDaoImpl();

    protected QuestionDao questionDao = new QuestionDaoImpl();

    protected AnswerDao answerDao = new AnswerDaoImpl();

    protected CommentDao commentDao = new CommentDaoImpl();


}
