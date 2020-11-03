package pagamentos;

public interface Cartao {

	public String getNumero();
	public String getSenha();
	public String getCVV();
	public void setNumero(String numero);
	public void setSenha(String senha);
	public void setCVV(String CVV);
}
