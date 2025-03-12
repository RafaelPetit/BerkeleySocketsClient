package com.example.berkleysocketclient;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button botaoEnviar;
    @FXML
    private TextField campoMensagem;
    @FXML
    private VBox vboxMensagens;
    @FXML
    private ScrollPane scrollMensagens;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            client = new Client("localhost", 1234);
            System.out.println("Conectado ao servidor");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar o cliente");
        }

        vboxMensagens.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number valorAntigo, Number valorNovo) {
                scrollMensagens.setVvalue((Double) valorNovo);
            }
        });

        client.receberMensagensServidor(vboxMensagens);


    }
}