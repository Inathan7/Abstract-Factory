package pagamentos;

import pagamentos.paypal.CartaoPaypal;
import pagamentos.paypal.ClientePaypal;
import pagamentos.paypal.LojaPaypal;
import pagamentos.paypal.TransacaoPaypal;

public class ProcessadorPagamentos {
	
	private static LojaPaypal loja = new LojaPaypal();

	public ProcessadorPagamentos() {
		loja.setCodigo("1000");
	}
	
	public TransacaoPaypal autorizarPagto(float valor, float juros, short parcelas, CartaoPaypal cartao, ClientePaypal cliente) {
		TransacaoPaypal transacao = null;
		if (cartao != null && cliente != null) {
			transacao = new TransacaoPaypal();
			transacao.setCartao(cartao);
			transacao.setCliente(cliente);
			transacao.setJuros(juros);
			transacao.setLoja(loja);
			transacao.setParcelas(parcelas);
			transacao.setValor(valor);
			transacao.autorizar();
		} else {
			System.out.println("erro: transacao ou cliente ou cartao ou loja nao foram corretamente repassados");
		}
		return transacao;
	}
}
