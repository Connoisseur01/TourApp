package jp;

import jp.Utils.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Office {


    public static void main(String[] args) throws IOException {
        while(true){
            Socket socket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(socket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
    }

    private static ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
