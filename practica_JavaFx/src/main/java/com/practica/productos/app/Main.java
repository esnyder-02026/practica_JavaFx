/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica.productos.app;

import com.practica.productos.modelo.Producto;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        
        TextField campo = new TextField();
        campo.setPromptText("Escribe algo aquí..."); 
        
        Button boton = new Button("Mostrar");
        Label label = new Label();

        

boton.setOnAction(e -> {
    try {
        
        Producto p = new Producto(campo.getText());
        
        
        label.setText("Producto creado: " + p.getNombre());
        label.setStyle("-fx-text-fill: green;"); 
        
    } catch (IllegalArgumentException ex) {
        
        label.setText("Error: " + ex.getMessage());
        label.setStyle("-fx-text-fill: red;"); 
    } catch (Exception ex) {
        
        label.setText("Ocurrió un problema.");
    }
});



        VBox layout = new VBox(10, campo, boton, label);
        
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("Interacción Básica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}