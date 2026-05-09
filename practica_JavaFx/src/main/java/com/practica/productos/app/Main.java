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
import javafx.scene.control.TextArea;
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

        TextArea area = new TextArea();
        area.setEditable(false); 
        area.setPromptText("Los productos aparecerán aquí...");

        boton.setOnAction(e -> {
            try {
     
                Producto nuevo = new Producto(campo.getText());
                servicio.agregar(nuevo);

            
                StringBuilder sb = new StringBuilder("Inventario Actual:\n");
                for (Producto p : servicio.listar()) {
                    sb.append("- ").append(p.getNombre()).append("\n");
                }

                area.setText(sb.toString());
                campo.clear();
                
            } catch (Exception ex) {
         
                area.setText("Error: " + ex.getMessage());
            }
        });

     
        VBox layout = new VBox(10, campo, boton, area);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 400);
        stage.setTitle("Sistema de Inventario JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}