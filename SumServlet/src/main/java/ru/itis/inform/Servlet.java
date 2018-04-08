package ru.itis.inform;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.valueOf;

public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num1 = valueOf(req.getParameter("int1"));
        int num2 = valueOf(req.getParameter("int2"));
        req.setAttribute("sum", num1 + num2);
        getServletConfig().getServletContext().getRequestDispatcher("/views/sum.jsp").forward(req, resp);
    }
}