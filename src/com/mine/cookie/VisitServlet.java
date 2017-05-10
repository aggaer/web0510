package com.mine.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.beans.Encoder;
import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(LAST_VISIT)) {
                    response.getWriter().print("<h2>your last visit time is " + URLDecoder.decode(cookie.getValue(), "utf8") + "</h2>");
                    break;
                }
            }
        } else {
            response.getWriter().println("<h1>hello! welcome to my site</h1>");
        }
//        每次访问都在cookie中存入这次访问的时间，作为下次访问时回显的时间。
        String time = SimpleDateFormat.getDateTimeInstance().format(new Date());
        String visitTime = URLEncoder.encode(time, "UTF8");
        Cookie cookie = new Cookie(LAST_VISIT, visitTime);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
}
