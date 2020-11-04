package pagamentos.pagseguro;

import pagamentos.Cliente;

public class ClientePagseguro implements Cliente {
	
	private String CPF;

	public String getCPF() {
		return this.CPF+" pagseguro";
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF+" pagseguro";
	}
	
}
