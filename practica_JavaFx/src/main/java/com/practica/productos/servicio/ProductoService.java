/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica.productos.servicio;

import com.practica.productos.modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    
    private List<Producto> productos = new ArrayList<>();

    public void agregar(Producto p) {
        if (p != null) {
            productos.add(p);
        }
    }

    public List<Producto> listar() {
        return new ArrayList<>(productos);
    }
    
    public int contar() {
        return productos.size();
    }

    public void eliminar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
