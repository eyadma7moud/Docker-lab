import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        server.createContext("/", exchange -> {
            String name = System.getenv().getOrDefault("STUDENT_NAME", "student");
            String containerId = InetAddress.getLocalHost().getHostName();
            String html = "<h1>Hello " + name + "!</h1><p>Container ID: " + containerId + "</p>";

            byte[] body = html.getBytes();
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(body);
            }
        });

        server.start();
        System.out.println("Server running on port 5000");
    }
}
