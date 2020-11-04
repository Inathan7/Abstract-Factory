package pagamentos.pagseguro;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import pagamentos.Cartao;

public class CartaoPagseguro implements Cartao {
	
	private String numero, senha, CVV;
	
	
	public String getNumero() {
		return this.numero;
	}

	public String getSenha() {
		return this.senha;
	}
	
	public String getCVV() {
		return this.CVV;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public void setSenha(String senha) {
		this.senha = encriptar(senha);
	}
	
	public void setCVV(String CVV) {
		this.CVV = CVV;
	}
	
	private String encriptar(String valor) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			byte[] hash = digest.digest(valor.getBytes(StandardCharsets.UTF_8));
			new StringBuffer().append(hash).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
