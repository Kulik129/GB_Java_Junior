package org.example.lesson_5;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            final Socket client = new Socket("localhost", Server.PORT);
            // чтение
            new Thread(() -> {
                try (Scanner input = new Scanner(client.getInputStream())) {
                    while (true) {
                        String command = input.nextLine();
                        if (command.equals("killClient")) {
                            client.close();
                            System.out.println("Клиент отключен");
                            System.exit(0);
                        }
                        System.out.println(command);
                    }
                } catch (Exception ex) {
                    System.out.println("Клиент отключен");
                    System.exit(0);
                }
            }).start();
            // запись
            new Thread(() -> {
                try (PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {
                    Scanner consoleScanner = new Scanner(System.in);
                    while (true) {
                        String consoleInput = consoleScanner.nextLine();
                        if (consoleInput.equals("")) continue;
                        output.println(consoleInput);
                        if (Objects.equals("q", consoleInput)) {
                            client.close();
                            break;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }).start();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
