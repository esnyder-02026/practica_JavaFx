/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica.productos.app;

import com.practica.productos.modelo.Producto;
import com.practica.productos.servicio.ProductoService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ProductoService servicio = new ProductoService();

    @Override
    public void start(Stage stage) {
       
        TextField campo = new TextField();
        campo.setPromptText("Nombre del producto...");
        
        Button boton = new Button("Agregar");
        Label label = new Label("Aún no hay productos.");

        boton.setOnAction(e -> {
            try {
            
                servicio.agregar(new Producto(campo.getText()));
                
                String texto = "Lista de Productos:\n";
                for (Producto p : servicio.listar()) {
                    texto += "• " + p.getNombre() + "\n";
                }
                
                label.setText(texto);
                campo.clear();
                
            } catch (Exception ex) {

                label.setText("Error: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10, campo, boton, label);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 350, 400);
        stage.setTitle("Mi Proyecto JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}