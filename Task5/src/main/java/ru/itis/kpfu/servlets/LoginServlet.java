package ru.itis.kpfu.servlets;

import ru.itis.kpfu.dao.JdbcTemp;
import ru.itis.kpfu.model.Credentials;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by artur on 11.10.16.
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private JdbcTemp jdbc = new JdbcTemp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext()
                .getRequestDispatcher("/login.ftl").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Credentials cred;
        try {
            cred = jdbc.getByLogin(username);
        } catch (Exception e){
            cred = null;
        }

        if(cred != null && cred.getPassword().equals(password)){
            req.getSession().setAttribute("session_uname", cred.getLogin());
            resp.sendRedirect("/profile");
        }
        else{
            req.setAttribute("error", true);
            getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.ftl").forward(req, resp);
        }
    }
}
