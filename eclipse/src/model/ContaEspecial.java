package model;

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
		return ((valor > (super.getIdCliente() + this.limite))? false: super.saque(valor));
	}
}
