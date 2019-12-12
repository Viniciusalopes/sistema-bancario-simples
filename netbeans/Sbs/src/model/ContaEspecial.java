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
public class ContaEspecial extends Conta {

    private double limite;

    public ContaEspecial(int id, int idCliente, double limite) {
        super(id, idCliente);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public boolean saque(double valor) {
        return ((valor > super.getSaldo() + this.limite))
                ? false : super.saque(valor);
    }
}
