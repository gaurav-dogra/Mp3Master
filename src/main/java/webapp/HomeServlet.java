package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class HomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String message = new Scanner(Objects.requireNonNull(HomeServlet.class.getClassLoader()
                .getResourceAsStream("home.html")), StandardCharsets.UTF_8)
                .useDelimiter("\\Z")
                .next();

        response.getWriter().println(message);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
