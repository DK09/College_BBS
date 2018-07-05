package muzhou.com.servlet;

import model.Content;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Data;
import util.Index;

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
            int startPage = Integer.parseInt(request.getParameter("startPage")) - 1;
            JSONObject json = new JSONObject();
            if (keyword.equals("")) {
                json.accumulate("count", 0);
                json.accumulate("content", null);
            } else {
                Index index = new Index();
                int count = index.getSeachResult(keyword, startPage, Data.PAGE_ITEM_COUNT);
                List<Content> contentList = index.contents;


                JSONArray jsonArray = JSONArray.fromObject(contentList);
                json.accumulate("count", count);
                json.accumulate("questions", jsonArray);
            }
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(json);
            System.out.println(json);
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
