package pagamentos.pagseguro;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import pagamentos.Cartao;
import pagamentos.Cliente;
import pagamentos.Loja;
import pagamentos.Transacao;

public class TransacaoPagseguro implements Transacao{
	
	private CartaoPagseguro cartao;
	private ClientePagseguro cliente;
	private LojaPagseguro loja;
	private float valor;
	private float setJuros;
	private short parcelas;
	private boolean cancelada;

	@Override
	public void setCartao(Cartao cartao) {
		if (cartao instanceof CartaoPagseguro) {
			this.cartao = (CartaoPagseguro) cartao;
		} else {
			System.out.println("Cartao incompativel para pagseguro.com.br");
		}
	}

	@Override
	public void setCliente(Cliente cliente) {
		if (cliente instanceof ClientePagseguro) {
			this.cliente = (ClientePagseguro) cliente;
		} else {
			System.out.println("Cliente incompativel para pagseguro.com.br");
		}
	}

	@Override
	public void setLoja(Loja loja) {
		if (loja instanceof LojaPagseguro) {
			this.loja = (LojaPagseguro) loja;
		}
	}

	@Override
	public void setValor(float valor) {
		if (valor > 0) {
			this.valor = valor;
		}
	}

	@Override
	public void setJuros(float juros) {
		if (juros > 0) {
			this.setJuros = juros;
		}
	}

	@Override
	public void setParcelas(short parcelas) {
		if (parcelas > 0) {
			this.parcelas = parcelas;
		}
	}
	
	@Override
	public Cartao getCartao() {
		return this.cartao;
	}

	@Override
	public Cliente getCliente() {
		return this.cliente;
	}

	@Override
	public Loja getLoja() {
		return this.loja;
	}

	@Override
	public float getValor() {
		return this.valor;
	}

	@Override
	public float getJuros() {
		return this.setJuros;
	}

	@Override
	public short getParcelas() {
		return this.parcelas;
	}

	@Override
	public boolean autorizar() {
		if (this.valor <= 0) {
			System.out.println("Transacao com valor menor igual a zero nao pode sere enviada para pagseguro.com.br");
			return false;
		}
		System.out.println("Enviando transacao para pagseguro.com.br");
		System.out.println(serialize());
		System.out.println("chave MD5");
		System.out.println(encriptar(serialize()));
		return true;
	}

	@Override
	public boolean cancelar() {
		if (!this.cancelada) {
			this.cancelada = true;
			System.out.println("Enviando transasao para pagseguro.com.br");
			System.out.println(serialize());
			System.out.println("chave MD5");
			System.out.println(encriptar(serialize()));
		} else
			System.out.println("Transacao ja cancelada e nao pode ser enviada para pagseguro.com.br");
			
		return false;
	}
	
	private String serialize() {
		//retorna representando como um objeto XML
		return "<transacao>"
				+ "\n<cartao> " + this.cartao.getNumero() + this.cartao.getCVV() + " </cartao>"
				+ "\n<cliente.senha> " + this.cartao.getSenha() + " </cliente.senha>"
				+ "\n<cliente.cpf> " + this.cliente.getCPF() + " </cliente.cpf>"
				+ "\n<estabelecimento> " + this.loja.getCodigo() + " </estabelecimento>"
				+ "\n<compra.valor> " + this.valor + " </compra.valor>"
				+ "\n<compra.parcelas> " + this.parcelas + " </compra.parcelas"
				+ "\n<compra.juros> " + this.setJuros + "</compra.juros>"
				+ "\n<compra.data> " + new Date().getTime() + "</compra.data>"
				+ "\n<modo> " + ((this.cancelada)? "cancel" : "new") + "</modo>"
			+ "\n<transacao>";
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
