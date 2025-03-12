package com.example.berkleysocketclient;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) {
        try{
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar o cliente");
            fecharTudo(socket, bufferedReader, bufferedWriter);
        }
    }

    public void enviarMensagemServidor(String mensagemServidor) {
        try {
            bufferedWriter.write(mensagemServidor);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar mensagem para o servidor");
            fecharTudo(socket, bufferedReader, bufferedWriter);
        }
    }

    public void fecharTudo(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao fechar o servidor");
            e.printStackTrace();
        }
    }
}
