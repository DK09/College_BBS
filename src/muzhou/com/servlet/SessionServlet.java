package muzhou.com.servlet;

import muzhou.com.bean.UserBean;
import muzhou.com.service.UserService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");

        //JSON对象
        JSONObject jsonObject = new JSONObject();

        PrintWriter out = response.getWriter();
        System.out.println("我被调用了");
      //  UserService userService = null;
        UserBean userBean = null;
        if (null!=request.getSession().getAttribute("user"))
        {
            userBean = (UserBean)request.getSession().getAttribute("user");
            System.out.println(userBean.getUserName());
            jsonObject.accumulate("userName", userBean.getUserName());
            out.print(jsonObject);
        }
     /*   else
        {
            Cookie[] cookies = request.getCookies();

            for(int i = 0 ; i < cookies.length ; i++){
                String name = cookies[i].getName() ;
                String value = cookies[i].getValue() ;
                if (name.equals("user"))
                {
                    HttpSession session = request.getSession();
                    userBean =  userService.getUserById(Integer.parseInt(value));
                    session.setAttribute("user",userBean);
                    session.setMaxInactiveInterval(30*60);
                    System.out.println(userBean.getUserName());
                    jsonObject.accumulate("userName", userBean.getUserName());
                    out.print(jsonObject);
                }
                System.out.println("cookie、session都不存在");
            }
        }*/



    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
