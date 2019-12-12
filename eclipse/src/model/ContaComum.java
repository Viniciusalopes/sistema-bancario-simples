package model;

public class ContaComum extends Conta {

	public ContaComum(int id, int idCliente) {
		super(id, idCliente);
	}

	public boolean saque(double valor) {
		return ((valor > super.getSaldo()) ? false : super.saque(valor));
	}

}
