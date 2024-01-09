package org.example.lesson_5;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Server {
    public static final int PORT = 8787;
    private static long clientIdCounter = 1L;
    private static Map<Long, SocketWrapper> clients = new HashMap<>();
    private static void broadcastMessage(String message){
        broadcastMessage(-1, message);
    }
    private static void broadcastMessage(long id, String message) {
        for (Long key : clients.keySet()) {
            SocketWrapper currentClient = clients.get(key);
            if (id > 0) currentClient.getOutput().println(id + " -> " + message);
            else currentClient.getOutput().println(message);
        }
    }
    private static void getClients(long id) {
        SocketWrapper currentClient = clients.get(id);
        if (currentClient != null) {
            currentClient.getOutput().println("Список клиентов" + clients);
        }
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                final Socket client = server.accept();
                final long clientId = clientIdCounter++;
                SocketWrapper wrapper = new SocketWrapper(clientId, client);
                System.out.println("Подключился новый клиент[" + wrapper + "]");
                clients.put(clientId, wrapper);
                new Thread(() -> {
                    try (Scanner input = wrapper.getInput(); PrintWriter output = wrapper.getOutput()) {
                        output.println("Подключение успешно. Список всех клиентов: " + clients);
                        while (true) {
                            String clientInput = "";
                            try {
                                clientInput = input.nextLine();
                            }
                            catch (Exception ex){
                            }
                            if (!clientInput.equals("")) {


                                if (clientInput.length() > 0 && String.valueOf(clientInput.charAt(0)).equals("@")) {
                                    String message = clientInput.substring(1);
                                    String[] address = message.split(" ");
                                    try {
                                        long destinationId = Long.parseLong(address[0]);
                                        SocketWrapper destinationClient = clients.get(destinationId);
                                        if (destinationClient != null) {
                                            destinationClient.getOutput().println(wrapper.getId() + " -> " + clientInput);
                                        }
                                    } catch (Exception ex) {
                                    }
                                } else {
                                    if (Objects.equals("q", clientInput)) {
                                        clients.remove(clientId);
                                        broadcastMessage("Клиент[" + clientId + "] отключился");
                                        break;
                                    } else if (Objects.equals("list", clientInput)) {
                                        getClients(wrapper.getId());
                                    } else if (clientInput.length() > 4 && clientInput.substring(0, 4).equals("kill")) {
                                        if (wrapper.isAdmin()) {
                                            String[] command = clientInput.split(" ");
                                            try {
                                                if (command.length == 1 || command.length > 2) throw new Exception();
                                                long destinationId = Long.parseLong(command[1]);
                                                SocketWrapper destination = clients.get(destinationId);
                                                destination.getOutput().println("killClient");
                                                clients.remove(destinationId);
                                                broadcastMessage("Клиент[" + destinationId + "] отключен");
                                            } catch (Exception ex) {
                                                wrapper.getOutput().println("Ошибка в команде");
                                            }
                                        } else {
                                            wrapper.getOutput().println("Недостаточно прав");
                                        }

                                    } else if (Objects.equals("admin", clientInput)) {
                                        wrapper.setAdmin(true);
                                        wrapper.getOutput().println("Вы администратор");
                                    } else {
                                        broadcastMessage(wrapper.getId(), clientInput);
                                    }
                                }
                                if (clients.get(wrapper.getId()) == null) break;
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
