package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import manager.FinanceManager;
import purchase.Purchase;
import purchase.service.PurchaseService;
import report.category.ReportMaxCategory;
import report.service.ReportService;
import request.product.ProductRequest;
import tcvreader.DetermineCategoryFromTsv;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс - сервер, который принимает запрос от клиента
 */
public class Server {
    private final int port;
    private final Purchase purchase = new Purchase();
    private final DetermineCategoryFromTsv categoryFromTsv = new DetermineCategoryFromTsv();
    private final ObjectMapper mapper = new ObjectMapper();
    private final PurchaseService purchaseService = new PurchaseService(purchase, categoryFromTsv);
    private final FinanceManager financeManager = new FinanceManager(purchase);
    private final ReportService reportService = new ReportService(financeManager, purchaseService);

    public Server(int port) {
        this.port = port;
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

                    String jsonString = in.readLine(); // читаем строку от клиента (покупка)
                    ProductRequest productRequest = mapper.readValue(jsonString, ProductRequest.class); // парсим строку в класс Purchase
                    ReportMaxCategory summaryReportData = reportService.buyItem(productRequest);//получаем макс.категорию
                    String report = mapper.writeValueAsString(summaryReportData);//сериализуем объект в JSON строку
                    out.println(report);//отправляем ответ в виде строки клиенту
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}