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
import javafx.scene.layout.HBox; // Import necesario para Paso 26
import javafx.scene.layout.VBox; // Import necesario para Paso 26
import javafx.stage.Stage;

public class Main extends Application {

    private ProductoService servicio = new ProductoService();
    private TextArea area = new TextArea();

    @Override
    public void start(Stage stage) {
        TextField campo = new TextField();
        campo.setPromptText("Nombre del producto...");
        
        Button agregar = new Button("Agregar");
        Button eliminar = new Button("Eliminar");
        Button buscar = new Button("Buscar");

        area.setEditable(false);
        area.setPromptText("Inventario y resultados...");

        // Lógica de botones
        agregar.setOnAction(e -> {
            try {
                servicio.agregar(new Producto(campo.getText()));
                actualizarLista();
                campo.clear();
            } catch (Exception ex) {
                area.setText("Error: " + ex.getMessage());
            }
        });

        eliminar.setOnAction(e -> {
            servicio.eliminar(campo.getText());
            actualizarLista();
            campo.clear();
        });

        buscar.setOnAction(e -> {
            Producto p = servicio.buscar(campo.getText());
            if (p != null) {
                area.setText("🔍 Encontrado: " + p.getNombre());
            } else {
                area.setText("❌ No existe: " + campo.getText());
            }
        });

        // --- PASO 26: ORGANIZACIÓN DE UI ---
        // Agrupamos los botones horizontalmente con espacio de 10
        HBox botones = new HBox(10, agregar, eliminar, buscar);
        
        // Layout principal vertical (campo arriba, botones en medio, area abajo)
        VBox layout = new VBox(10, campo, botones, area);
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 450, 400);
        stage.setTitle("Gestión de Productos - Etapa 8");
        stage.setScene(scene);
        stage.show();
    }

    private void actualizarLista() {
        StringBuilder sb = new StringBuilder("Lista de Productos:\n");
        for (Producto p : servicio.listar()) {
            sb.append("• ").append(p.getNombre()).append("\n");
        }
        area.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}