<%@ page import="co.icesi.config.ContextConteiner" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="co.icesi.model.Editorial" %>
<%@ page import="co.icesi.service.EditorialService" %>
<%@ page import="java.util.List" %>

<%
    ApplicationContext context = ContextConteiner.getContext();
    EditorialService editorialService = context.getBean(EditorialService.class);
    List<Editorial> editoriales = editorialService.getEditorials();
%>
<!DOCTYPE html>
<html>
<body>
    <h2>Hello World!</h2>
    <a href="hello-servlet">Hello Servlet Link</a>
    <h2>Editoriales</h2>
    <ul>
        <%
            for (Editorial editorial : editoriales) {
        %>
            <li><%= editorial.getName() %></li>
        <%
            }
        %>
    </ul>
    <form action="/demo-vscode/editorials" method="post">
        <input type="text" name="name" placeholder="Nombre de la editorial">
        <input type="submit" value="Crear editorial">
    </form>
</body>
</html>
