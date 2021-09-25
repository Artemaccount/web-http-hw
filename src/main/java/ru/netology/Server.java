package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class Server {

    private int port;
    private int poolSize;

    public Server(int port, int poolSize) {
        this.port = port;
        this.poolSize = poolSize;
    }

    public void startServer() {
        final var thPool = Executors.newFixedThreadPool(poolSize);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                thPool.submit(new ServerConnection(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
