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
        Button botonBuscar = new Button("Buscar");

        area.setEditable(false);
        area.setPromptText("Resultados e inventario...");

      
        botonAgregar.setOnAction(e -> {
            try {
                servicio.agregar(new Producto(campo.getText()));
                actualizarLista();
                campo.clear();
            } catch (Exception ex) {
                area.setText("Error: " + ex.getMessage());
            }
        });

       
        botonEliminar.setOnAction(e -> {
            servicio.eliminar(campo.getText());
            actualizarLista();
            campo.clear();
        });

       
        botonBuscar.setOnAction(e -> {
            Producto encontrado = servicio.buscar(campo.getText());
            if (encontrado != null) {
                area.setText(" Producto encontrado:\n" + encontrado.getNombre());
            } else {
                area.setText(" No se encontró el producto: " + campo.getText());
            }
        });

      
        HBox filaBotones = new HBox(10, botonAgregar, botonEliminar, botonBuscar);
        
        VBox layout = new VBox(10, campo, filaBotones, area);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 450, 450);
        stage.setTitle("CRUD Completo - Gestión de Productos");
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