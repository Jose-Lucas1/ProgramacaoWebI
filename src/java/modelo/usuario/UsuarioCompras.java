/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

/**
 *
 * @author josep
 */
public class UsuarioCompras {
    private int id;
    private String nome;
    private int total_compras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotal_compras() {
        return total_compras;
    }

    public void setTotal_compras(int total_compras) {
        this.total_compras = total_compras;
    }
    

    public UsuarioCompras(int id, String nome, int total_compras) {
        this.id = id;
        this.nome = nome;
        this.total_compras = total_compras;
    }
    
}
