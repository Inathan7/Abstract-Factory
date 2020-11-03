package pagamentos.paypal;

import pagamentos.Cliente;

public class ClientePaypal implements Cliente {

	private String CPF;

	public String getCPF() {
		return this.CPF+" paypal";
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF+" paypal";
	}

}
