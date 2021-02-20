import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import webapp.SongServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.start(args);
        SongServlet servlet = new SongServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(servlet), "/getall");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
