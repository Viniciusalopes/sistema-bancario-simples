/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vovostudio
 */
public class Conta {

    private int id;
    private int idCliente;
    private double saldo;

    public enum TipoDeConta {
        Comum, Especial
    }

    public Conta(int id, int idCliente) {
        this.id = id;
        this.idCliente = idCliente;
        this.saldo = 0;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void deposito(double valor) {
        saldo += valor;
    }

    public boolean saque(double valor) {
        saldo -= valor;
        return true;
    }
}
