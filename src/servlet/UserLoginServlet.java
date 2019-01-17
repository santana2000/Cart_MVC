package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

public class UserLoginServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new UserDAO().getUser(name, password);

        //如果该用户存在，就表示用户名和密码正确
        if (null != user) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("./listProduct");
        } else
            response.sendRedirect("./pages/login.jsp");

    }
}