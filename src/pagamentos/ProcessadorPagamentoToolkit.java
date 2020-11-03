package pagamentos;

public class ProcessadorPagamentoToolkit {

	private static Loja loja;
	private FabricaPagamento fabrica;

	public ProcessadorPagamentoToolkit(FabricaPagamento fabrica) {
		this.fabrica = fabrica;
		loja = fabrica.fabricarLoja();
		loja.setCodigo("1000");
	}
	
	public Transacao autorizarPagto(float valor, float juros, short parcelas, Cartao cartao, Cliente cliente) {
		Transacao transacao = null;
		if (cartao != null && cliente != null) {
			transacao = fabrica.fabricarTransacao();
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
