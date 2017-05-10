package com.mine.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.text.DateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.function.Consumer;

import static com.mine.utils.Constant_Fields.LAST_VISIT;

/**
 * Created by Administrator on 2017/5/10.
 */
@WebServlet(name = "VisitServlet", urlPatterns = "/VisitServlet")
public class VisitServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF8");
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        response.getWriter().println("<h1>hello! welcome to my site</h1>");
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals(LAST_VISIT)) {
                response.getWriter().print("<h2>your last visit time is " + cookie.getValue() + "</h2>");
                break;
            }
        }
//        每次访问都在cookie中存入这次访问的时间，作为下次访问时回显的时间。
        String visitTime = DateFormat.getDateTimeInstance().format(new Date());
        System.out.println(visitTime);
        Cookie cookie = new Cookie(LAST_VISIT, visitTime);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
}
