package pagamentos;

public interface FabricaPagamento {
	
	public abstract Cartao fabricarCartao();
	
	public abstract Cliente fabricarCliente();
	
	public abstract Loja fabricarLoja();
	
	public abstract Transacao fabricarTransacao();

}
