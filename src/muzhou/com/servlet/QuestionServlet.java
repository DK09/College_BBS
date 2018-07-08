package muzhou.com.servlet;

import muzhou.com.bean.Index;
import muzhou.com.bean.QuestionBean;
import muzhou.com.bean.UserBean;
import muzhou.com.service.QuestionService;
import muzhou.com.service.serviceImpl.QuestionServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QuestionServlet", urlPatterns = {"/QuestionServlet"})
public class QuestionServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");

        //JSON对象
        JSONObject jsonObject = new JSONObject();

        PrintWriter out = response.getWriter();

        String qTitle = request.getParameter("questionTitle");
        String qContent = request.getParameter("questionContent");
        String qCategory = request.getParameter("questionCategory");
        int qIntegral = Integer.parseInt(request.getParameter("questionIntegral"));
        String[] qKeyword = null;
        String questionKeyword = "";
        HttpSession session = request.getSession();
        UserBean userBean = null;
        if (null!=session.getAttribute("user"))
        {
            if (!"".equals(qTitle))
            {
                if (!"".equals(qContent))
                {
                    userBean = (UserBean)session.getAttribute("user");
                    int userId = userBean.getUserId();
                    String username = userBean.getUserName();
                    QuestionBean questionBean = new QuestionBean();
                    questionBean.setCategory(qCategory);
                    questionBean.setTitle(qTitle);
                    questionBean.setContent(qContent);
                    questionBean.setUserId(userId);
                    questionBean.setUsername(username);
                    questionBean.setIntegral(qIntegral);
                    if (null != request.getParameterValues("questionKeyword"))
                    {
                        qKeyword = request.getParameterValues("questionKeyword");
                        for (String keyword: qKeyword)
                        {
                            questionKeyword = questionKeyword + keyword+" ";
                        }
                    }
                    else
                    {
                        questionKeyword = " ";
                    }

                    questionBean.setKeyword(questionKeyword);
                    QuestionService questionService = new QuestionServiceImpl();
                    questionService.addQuestion(questionBean);
                    Index index = new Index();
                    index.writeToIndex(questionBean);
                    jsonObject.accumulate("jump", "1")
                            .accumulate("ifEmpty","1");
                    out.print(jsonObject);
                }
                else
                {
                    jsonObject.accumulate("jump", "1")
                    .accumulate("ifEmpty","0");
                    out.print(jsonObject);
                }
            }
            else
            {
                System.out.println("不能为空");
                jsonObject.accumulate("jump", "1")
                .accumulate("ifEmpty","0");
                out.print(jsonObject);
            }

        }
        else
        {
            System.out.println("用户未登录");
            jsonObject.accumulate("jump","0")
            .accumulate("ifEmpty","0");
            out.print(jsonObject);

        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
