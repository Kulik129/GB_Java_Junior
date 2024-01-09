package org.example.lesson_5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class SocketWrapper implements AutoCloseable{
    private final long id;
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter output;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    private boolean admin;

    @Override
    public void close() throws Exception {
    }

    public long getId() {
        return id;
    }

    public Scanner getInput() {
        return input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return socket.toString();
    }

    public SocketWrapper(long id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.admin = false;
    }
}
