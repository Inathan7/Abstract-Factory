package pagamentos;

public interface Transacao {

	public void setCartao(Cartao cartao);

	public void setCliente(Cliente cliente);

	public void setLoja(Loja loja);

	public void setValor(float valor);

	public void setJuros(float juros);

	public void setParcelas(short parcelas);

	public Cartao getCartao();

	public Cliente getCliente();

	public Loja getLoja();

	public float getValor();

	public float getJuros();

	public short getParcelas();

	public boolean autorizar();

	public boolean cancelar();

	public String toString();
}
