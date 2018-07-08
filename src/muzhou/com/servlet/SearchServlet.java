package muzhou.com.servlet;

import muzhou.com.bean.Content;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import muzhou.com.bean.Index;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name = "SearchServlet",
        urlPatterns = {"/SearchServlet"}
)
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");

            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/json");

            String keyword = request.getParameter("keyword");
            String start = request.getParameter("startPage");
            System.out.println("字符串"+start);
            int startPage = Integer.parseInt(start) - 1;
            System.out.println("int"+startPage);
            JSONObject json = new JSONObject();



            if (keyword.equals("")) {
                json.accumulate("count", 0);
                json.accumulate("content", null);
            } else {
                Index index = new Index();
                int count = index.getSeachResult(keyword, startPage, 10);
                List<Content> contentList = index.contents;


                JSONArray jsonArray = JSONArray.fromObject(contentList);
                json.accumulate("count", count);
                json.accumulate("questions", jsonArray);
            }
            PrintWriter out = response.getWriter();
            System.out.println(json);
            out.print(json);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
