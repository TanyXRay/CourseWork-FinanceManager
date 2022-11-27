package server;

import com.google.gson.Gson;
import finance.FinanceManager;
import tcvreader.TcvReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Класс-сервер, который принимает запрос от клиента
 */
public class Server {
    private final int port;
    private final FinanceManager financeManager;
    private TcvReader tcvReader = new TcvReader();

    public Server(int port, FinanceManager financeManager) {
        this.port = port;
        this.financeManager = financeManager;
    }

    /**
     * Метод запуска сервера
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Starting server at " + port + "...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    Gson gson = new Gson();
                    Map<String, String> mapFromJson = gson.fromJson(in.readLine(), Map.class);
                    tcvReader.checkCategoriesInFile(mapFromJson);

                    // String allTasks = financeManager.getAllTasks();
                    //  out.println(allTasks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}