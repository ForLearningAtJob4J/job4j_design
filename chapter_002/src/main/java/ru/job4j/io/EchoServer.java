package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    final static String QUIT_MESSAGE = "BYE";
    final static String HELLO_MESSAGE = "HELLO";

    final static String REQUEST_METHOD = "GET";

    static List<String> request = new ArrayList<>();
    static final int PORT = 9000;

    public static void main(String[] args) {
        boolean needToWork = true;
        System.out.println("Server started. Listening on port " + PORT + "...");
        try {
            ServerSocket server = new ServerSocket(PORT);
            while (needToWork) {
                Socket socket = server.accept();
                System.out.println("Query received");
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (str != null && !str.isEmpty()) {
                        request.add(str);
                        str = in.readLine();
                    }
                    String command = parseCommand(request);
                    if (QUIT_MESSAGE.equals(command.toUpperCase())) {
                        needToWork = false;
                    }

                    String content;

                    if (HELLO_MESSAGE.equals(command.toUpperCase())) {
                        content = "Hello, my dear friend!";
                    } else if (QUIT_MESSAGE.equals(command.toUpperCase())) {
                        content = "Exiting server process...";
                    } else {
                        content = command;
                    }

                    System.out.println("Sending: " + content);
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());

                    out.write(("Content-Length: " + content.length() + "\r\n").getBytes());
                    out.write("Content-Type: text/html\r\n".getBytes());
                    out.write("Connection: Close\r\n\r\n".getBytes());

                    out.write(content.getBytes());
                } catch (IOException e) {
                    LOG.error("Input/output stream error", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Socket error occurred", e);
        }
        System.out.println("Server process finished!");
    }

    private static String parseCommand(List<String> request) {
        String buf = request.get(0);
        if (buf != null) {
            buf = buf.trim();
            System.out.println(buf);
            try {
                if (REQUEST_METHOD.equals(buf.substring(0, buf.indexOf(" ")))) {
                    buf = buf.substring(buf.indexOf("?") + 1, buf.lastIndexOf(" "));
                    Map<String, String> query = Arrays.stream(buf.split("&"))
                            .collect(Collectors.toMap(
                                    s -> s.substring(0, s.indexOf("=")).toUpperCase(),
                                    s -> s.substring(s.indexOf("=") + 1))
                            );
                    buf = query.get("MSG");
                }
            } catch (Exception e) {
                buf = "It would be better to send 400 Bad Request ))";
                LOG.error("Error during parsing command", e);
            }
        }
        request.clear();
        return buf;
    }
}