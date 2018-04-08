package ru.itis.kpfu.servlets;

import ru.itis.kpfu.dao.JdbcTemp;
import ru.itis.kpfu.model.Credentials;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profileChange")
public class ProfileChangerServlet extends HttpServlet {
    private JdbcTemp jdbc = new JdbcTemp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext()
                .getRequestDispatcher("/profileChange.ftl").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String curUsername = (String) req.getSession().getAttribute("session_uname");
        Credentials cred = jdbc.getByLogin(curUsername);

        jdbc.update(cred.getId(), username, password);
        req.getSession().setAttribute("session_uname", jdbc.findById(cred.getId()).getLogin());
        resp.sendRedirect("/profile");
    }
}
