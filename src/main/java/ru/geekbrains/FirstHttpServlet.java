package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


//  URL mapping patterns
//
//  "/something"        ->         "/something"
//  "/something/*"      ->         "/something/" or "/something/gfgdfgdfgdfg" or "/something/ddd" etc.
//  "/*.jpg"            ->         "/something/dfssdf/filename.jpg" or "/filename.jpg" etc.
//  ""                  ->         application root
//  "/"                 ->         all application URLs
//  "/*"                ->         !!! AntiPattern !!!
@WebServlet(name = "FirstHttpServlet", urlPatterns = "/httpservlet/*")
public class FirstHttpServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = (Connection) getServletContext().getAttribute("connection");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Hello from HTTP servlet</h1>");
        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");

        //req.getRequestDispatcher("/helloworld").include(req, resp);
        getServletContext().getRequestDispatcher("/WEB-INF/web.xml").include(req, resp);
    }
}
