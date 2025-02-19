package co.icesi;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "restServlet", value = "/rest-servlet")
public class RestServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "{\"message\":\"Hello World!\"}";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        response.getWriter().println(message);

    }

    public void destroy() {
    }
    
}
