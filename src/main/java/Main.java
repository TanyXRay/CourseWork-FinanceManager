import server.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(8989);
        server.start();
    }
}
