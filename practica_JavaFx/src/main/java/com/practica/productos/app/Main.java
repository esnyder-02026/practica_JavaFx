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
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ProductoService servicio = new ProductoService();
    private TextArea area = new TextArea(); 

    @Override
    public void start(Stage stage) {
        TextField campo = new TextField();
        campo.setPromptText("Nombre del producto...");
        
        Button botonAgregar = new Button("Agregar");
        Button botonEliminar = new Button("Eliminar");
        
        
        botonEliminar.setStyle("-fx-base: #ff6666;");

        area.setEditable(false);
        area.setPromptText("Lista de inventario...");

       
        botonAgregar.setOnAction(e -> {
            try {
                servicio.agregar(new Producto(campo.getText()));
                actualizarLista();
                campo.clear();
            } catch (Exception ex) {
                area.setText("Error al agregar: " + ex.getMessage());
            }
        });

        
        botonEliminar.setOnAction(e -> {
            String nombre = campo.getText();
            if (!nombre.trim().isEmpty()) {
                servicio.eliminar(nombre);
                actualizarLista();
                campo.clear();
            } else {
                area.setText("Error: Escribe el nombre del producto a eliminar.");
            }
        });

        HBox botones = new HBox(10, botonAgregar, botonEliminar);
        
        VBox layout = new VBox(10, campo, botones, area);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 400, 450);
        stage.setTitle("CRUD de Productos");
        stage.setScene(scene);
        stage.show();
    }

    private void actualizarLista() {
        StringBuilder sb = new StringBuilder("Inventario Actual:\n");
        for (Producto p : servicio.listar()) {
            sb.append("- ").append(p.getNombre()).append("\n");
        }
        area.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}