/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.compra;

import java.time.LocalDate;

/**
 *
 * @author josep
 */
public class Compra {
    private int id;
    private int id_usuario;
    private int quantidade_total_de_produtos_comprados;
    private double valortotal;
    private LocalDate data;

    public Compra(int id_usuario, int quantidade_total_de_produtos_comprados, double valortotal, LocalDate data) {
        this.id_usuario = id_usuario;
        this.quantidade_total_de_produtos_comprados = quantidade_total_de_produtos_comprados;
        this.valortotal = valortotal;
        this.data = data;
    }

    public Compra(int id, int id_usuario, int quantidade_total_de_produtos_comprados, double valortotal, LocalDate data) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.quantidade_total_de_produtos_comprados = quantidade_total_de_produtos_comprados;
        this.valortotal = valortotal;
        this.data = data;
    }

    Compra(LocalDate data, double valortotal) {
        this.data = data;
        this.valortotal = valortotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getQuantidade_total_de_produtos_comprados() {
        return quantidade_total_de_produtos_comprados;
    }

    public void setQuantidade_total_de_produtos_comprados(int quantidade_total_de_produtos_comprados) {
        this.quantidade_total_de_produtos_comprados = quantidade_total_de_produtos_comprados;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
}