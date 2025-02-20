package co.icesi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.icesi.config.ContextConteiner;
import co.icesi.model.Editorial;
import co.icesi.service.EditorialService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "application", value = "/editorials")
public class DispatcherServlet  extends HttpServlet{
    
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        List<Editorial> editorials = ContextConteiner.getContext().getBean("editorialService", EditorialService.class).getEditorials();
        ObjectMapper parser = ContextConteiner.getContext().getBean(ObjectMapper.class);
        String response = parser.writeValueAsString(editorials);
        PrintWriter writer = resp.getWriter();
        writer.write(response);
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        System.out.println(req.getQueryString());
        System.out.println(requestBody);
    }
}
