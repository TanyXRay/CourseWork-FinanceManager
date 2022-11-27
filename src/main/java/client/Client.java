package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Класс-клиент, который соединяется с сервером и отправляет
 * ему строку-запрос в формате JSON,
 */
public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8989);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("{ \"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200 }");

            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}