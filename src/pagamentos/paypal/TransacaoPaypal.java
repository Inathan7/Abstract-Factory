package pagamentos.paypal;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import pagamentos.Cartao;
import pagamentos.Cliente;
import pagamentos.Loja;
import pagamentos.Transacao;

public class TransacaoPaypal implements Transacao {

	private CartaoPaypal cartao;
	private ClientePaypal cliente;
	private LojaPaypal loja;
	private float valor;
	private float setJuros;
	private short parcelas;
	private boolean cancelada;

	@Override
	public void setCartao(Cartao cartao) {
		if (cartao instanceof CartaoPaypal) {
			this.cartao = (CartaoPaypal) cartao;
		} else {
			System.out.println("Cartao incompativel para paypal.com");
		}
	}

	@Override
	public void setCliente(Cliente cliente) {
		if (cliente instanceof ClientePaypal) {
			this.cliente = (ClientePaypal) cliente;
		} else {
			System.out.println("Cliente incompativel para paypal.com");
		}
	}

	@Override
	public void setLoja(Loja loja) {
		if (loja instanceof LojaPaypal) {
			this.loja = (LojaPaypal) loja;
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
			System.out.println("Transacao com valor menor igual a zero nao pode sere enviada para paypal.com");
			return false;
		}
		System.out.println("Enviando transacao para paypal.com");
		System.out.println(serialize());
		System.out.println("chave SHA-256");
		System.out.println(encriptar(serialize()));
		return true;
	}

	@Override
	public boolean cancelar() {
		if (!this.cancelada) {
			this.cancelada = true;
			System.out.println("Enviando transasao para paypal.com");
			System.out.println(serialize());
			System.out.println("chave SHA-256");
			System.out.println(encriptar(serialize()));
		} else
			System.out.println("Transacao ja cancelada e nao pode ser enviada para paypal.com");
			
		return false;
	}
	
	private String serialize() {
		//retorna representando como um objeto JSON
		return "transacao: {"
				+ "cartao: " + this.cartao.getNumero() + this.cartao.getCVV()
				+ "cliente.senha: " + this.cartao.getSenha()
				+ "cliente.cpf: " + this.cliente.getCPF()
				+ "estabelecimento: " + this.loja.getCodigo()
				+ "compra.valor: " + this.valor
				+ "compra.parcelas " + this.parcelas
				+ "compra.juros" + this.setJuros
				+ "compra.data" + new Date().getTime()
				+ "modo: " + ((this.cancelada)? "cancel" : "new")
			+ "}";
	}
	
	private String encriptar(String valor) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(valor.getBytes(StandardCharsets.UTF_8));
			new StringBuffer().append(hash).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
