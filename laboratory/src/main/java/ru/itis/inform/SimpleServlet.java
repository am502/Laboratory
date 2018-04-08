package ru.itis.inform;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class SimpleServlet extends HttpServlet {
    private HashMap<String, String> map = new HashMap<String, String>();

    @Override
    public void init() throws ServletException {
        map.put("admin", "qwerty");
        map.put("ozon", "com12");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        boolean flag = map.containsKey(name) && map.get(name).equals(password);
        req.setAttribute("message", "Hey, " + name);
        req.setAttribute("flag", flag);
        getServletConfig().getServletContext().getRequestDispatcher("/views/hello.jsp").forward(req, resp);
    }
}