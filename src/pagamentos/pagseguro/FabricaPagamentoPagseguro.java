package pagamentos.pagseguro;

import pagamentos.Cartao;
import pagamentos.Cliente;
import pagamentos.FabricaPagamento;
import pagamentos.Loja;
import pagamentos.Transacao;

public class FabricaPagamentoPagseguro implements FabricaPagamento {

	@Override
	public Cartao fabricarCartao() {
		return new CartaoPagseguro();
	}

	@Override
	public Cliente fabricarCliente() {
		return new ClientePagseguro();
	}

	@Override
	public Loja fabricarLoja() {
		return new LojaPagseguro();
	}

	@Override
	public Transacao fabricarTransacao() {
		return new TransacaoPagseguro();
	}

}
