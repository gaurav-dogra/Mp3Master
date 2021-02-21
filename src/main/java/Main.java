import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import webapp.AboutServlet;
import webapp.HomeServlet;
import webapp.SongServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.start(args);

        SongServlet servlet = new SongServlet();
        HomeServlet home = new HomeServlet();
        AboutServlet about = new AboutServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(servlet), "/getall");
        context.addServlet(new ServletHolder(home), "/");
        context.addServlet(new ServletHolder(about), "/about");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
