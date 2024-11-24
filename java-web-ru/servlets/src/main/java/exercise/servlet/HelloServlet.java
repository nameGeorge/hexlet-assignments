package exercise.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = Optional.ofNullable(request.getParameter("name"))
                .orElseGet(() -> "Guest");
        String message = String.format("Hello, %s!", name);
        request.setAttribute("message", message);

        request.getRequestDispatcher("/WEB-INF/hello.jsp").forward(request, response);
    }
    // END
}
