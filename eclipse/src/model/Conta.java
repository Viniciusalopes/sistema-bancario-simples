package model;

public abstract class Conta {
	private int id;
	private int idCliente;
	private double saldo;

	public enum TipoDeConta {
		Comum, Especial
	}

	public Conta(int id, int idCliente) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		saldo = 0;
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
		saldo -=valor;
		return true;
	}
}
