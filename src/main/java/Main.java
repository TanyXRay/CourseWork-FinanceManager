import finance.FinanceManager;
import server.Server;

public class Main {

    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();
        Server server = new Server(8989, financeManager);
        server.start();
    }
}
