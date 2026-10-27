import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.file.*;

public class App {

    // 1. The calculator: plain Java, nothing Docker-specific
    static double calculate(double a, double b, String op) {
        switch (op) {
            case "add": return a + b;
            case "sub": return a - b;
            case "mul": return a * b;
            case "div": return a / b;
            default: throw new IllegalArgumentException("Unknown operation: " + op);
        }
    }

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        // 2. Handle URLs like /calc?a=10&b=5&op=add
        server.createContext("/calc", exchange -> {
            String[] params = exchange.getRequestURI().getQuery().split("&");
            double a  = Double.parseDouble(params[0].split("=")[1]);
            double b  = Double.parseDouble(params[1].split("=")[1]);
            String op = params[2].split("=")[1];

            String result = a + " " + op + " " + b + " = " + calculate(a, b, op);

            // 3. Save the result to the history file (on the volume)
            Files.writeString(Path.of("/data/history.txt"), result + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            // 4. Send the result back to the browser
            exchange.sendResponseHeaders(200, result.length());
            exchange.getResponseBody().write(result.getBytes());
            exchange.close();
        });

        server.start();
        System.out.println("Calculator running on port 5000");
    }
}
