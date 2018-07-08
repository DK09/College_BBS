package muzhou.com.servlet;

import muzhou.com.bean.QuestionBean;
import muzhou.com.service.QuestionService;
import muzhou.com.service.serviceImpl.QuestionServiceImpl;
import net.sf.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet(name = "AnswerServlet", urlPatterns = {"/AnswerServlet"})
public class AnswerServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");

        //JSON对象
        JSONObject jsonObject = new JSONObject();

        PrintWriter out = response.getWriter();

        int qid = Integer.parseInt(request.getParameter("qid"));
        QuestionBean questionBean = null;
        QuestionService questionService = new QuestionServiceImpl();
        questionBean = questionService.getQuestionById(qid);

        String qusername = questionBean.getUsername();
        String qtime = questionBean.getTime().toString().replace(".0","");
        String qcontent = questionBean.getContent();
        String qtitle = questionBean.getTitle();
        int qintegral = questionBean.getIntegral();
        String keyword = questionBean.getKeyword();

        System.out.println(qtime);
        jsonObject.accumulate("quserName",qusername)
        .accumulate("qtime",qtime)
        .accumulate("qcontent",qcontent)
        .accumulate("qtitle",qtitle)
        .accumulate("qintegral",qintegral);


        out.print(jsonObject);



    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
