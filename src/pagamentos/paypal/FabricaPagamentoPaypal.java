package pagamentos.paypal;

import pagamentos.Cartao;
import pagamentos.Cliente;
import pagamentos.FabricaPagamento;
import pagamentos.Loja;
import pagamentos.Transacao;

public class FabricaPagamentoPaypal implements FabricaPagamento {

	@Override
	public Cartao fabricarCartao() {
		return new CartaoPaypal();
	}

	@Override
	public Cliente fabricarCliente() {
		return new ClientePaypal();
	}

	@Override
	public Loja fabricarLoja() {
		return new LojaPaypal();
	}

	@Override
	public Transacao fabricarTransacao() {
		return new TransacaoPaypal();
	}

}
