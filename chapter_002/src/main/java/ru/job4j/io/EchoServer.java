package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class EchoServer {
    final static String QUIT_MESSAGE = "BYE";
    final static String REQUEST_METHOD = "GET";

    static List<String> request = new ArrayList<>();

    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) throws IOException {
        boolean needToWork = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (needToWork) {
                Socket socket = server.accept();
                System.out.println("Query received");
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        request.add(str);
                    }
                    if (QUIT_MESSAGE.equals(parseCommand(request).toUpperCase())) {
                        System.out.println("Exiting server process...");
                        needToWork = false;
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }

    private static String parseCommand(List<String> request) {
        String buf = request.get(0);
        if (buf != null) {
            buf = buf.trim();
            if (REQUEST_METHOD.equals(buf.substring(0, buf.indexOf(" ")))) {
                buf = buf.substring(buf.indexOf("?") + 1, buf.lastIndexOf(" "));
                Map<String, String> query = Arrays.stream(buf.split("&"))
                        .collect(Collectors.toMap(
                                s -> s.substring(0, s.indexOf("=")).toUpperCase(),
                                s -> s.substring(s.indexOf("=") + 1))
                        );
                buf = query.get("MSG");
                System.out.println(buf);
            }
        }
        request.clear();
        return buf;
    }
}