package main;

import pagamentos.Cartao;
import pagamentos.Cliente;
import pagamentos.FabricaPagamento;
import pagamentos.ProcessadorPagamentoToolkit;
import pagamentos.ProcessadorPagamentos;
import pagamentos.pagseguro.FabricaPagamentoPagseguro;
import pagamentos.paypal.CartaoPaypal;
import pagamentos.paypal.ClientePaypal;
import pagamentos.paypal.FabricaPagamentoPaypal;
import pagamentos.paypal.LojaPaypal;

public class Main {

	public static void main(String[] args) {
		usandoPagamentosPaypalSemFabrica();
		usandoFabricaPagamentosPaypal();
		usandoFabricaPagamentosPagseguro();
	}
	
	public static void usandoPagamentosPaypalSemFabrica() {
		ProcessadorPagamentos processador = new ProcessadorPagamentos();
		
		ClientePaypal cliente = new ClientePaypal();
		cliente.setCPF("000.000.000-50");
		
		CartaoPaypal cartao = new CartaoPaypal();
		cartao.setCVV("509");
		cartao.setNumero("0008989511122211112323");
		cartao.setSenha("7878");;
		
		LojaPaypal loja = new LojaPaypal();
		loja.setCodigo("2000BRAPAYP");;
		processador.autorizarPagto(1200f, 0.015f, (short) 10, cartao, cliente);
	}

	public static void usandoFabricaPagamentosPaypal() {
		/*
		 * TODO ABSTRACT.FACTORY.01
		 * 
		 * 0. Usaremos uma versao do exemplo da videoaula deste padrao, para fabricar familias de objetos para pagamentos.
		 * 1. Crie uma fabrica abstrata capaz de produzir os produtos correspondentes aos tipos Transacao, Cartao, Cliente e Loja, ja existentes
		 * no pacote pagamentos. A versao desses produtos para Paypal ja esta implementada, falta implementar a FabricaPagamentosPaypal via fabrica abstrata 
		 * (coloque essa fabrica concreta para Paypal no pacote "pagamentos.paypal").
		 * 2. Crie a classe de nome ProcessadorPagamentoToolkit que sera cliente da fabrica abstrata do padrao. Disponibilize os mesmos metodos de
		 * ProcessadorPagamentos nesta nova classe, adaptado-os para usarem produtos pelo supertipo e a propria fabrica abstrata, ja em seu construtor.
		 * 3. Instancie ProcessadorPagamentoToolkit aqui, passando em seu construtor uma FabricaPagamentosPaypal e chame o metodo autorizarPagto().
		 * 4. Crie produtos da familia aqui neste metodo usando a fabrica, sem new.
		 * 
		 * PERGUNTA ABSTRACT.FACOTRY.A: Mudar de fabrica concreta, assumindo que ja esteja codificada, geralmente
		 * impacta no Cliente somente na linha para instanciar tal fabrica concreta? Isso eh bom?
		 * 
		 * [Sim, ao precisar mudar o tipo concreto de fabrica, apenas precisarei mudar a linha que está instanciando
		 * a mesma, e isso é bom pois não trará nenhum impacto as operações feitas usando essa fabrica e os produtos, 
		 * pois o código se tornou fexível, e agora todas as fabricas concretas tratam os produtos da mesma forma.]
		 * 
		 * 
		 * 
		 */
		
		FabricaPagamento fabrica = new FabricaPagamentoPaypal();
		
		ProcessadorPagamentoToolkit processadorPagaamentoToolkit = new ProcessadorPagamentoToolkit(fabrica);
	
		Cartao cartao = fabrica.fabricarCartao();
		cartao.setCVV("509");
		cartao.setNumero("0008989511122211112323");
		cartao.setSenha("7878");
		
		Cliente cliente = fabrica.fabricarCliente();
		cliente.setCPF("000.000.000-50");
		
		processadorPagaamentoToolkit.autorizarPagto(1200f, 0.015f, (short) 10, cartao, cliente);
	}
	
	public static void usandoFabricaPagamentosPagseguro() {
		/*
		 * TODO ABSTRACT.FACTORY.02
		 * 
		 * 1. Crie uma fabrica abstrata capaz de produzir os produtos correspondentes aos tipos Transacao, Cartao, Cliente e Loja, ja existentes
		 * no pacote pagamentos. A versao desses produtos para Pagseguro nao esta implementada e falta implementar a FabricaPagamentosPagaseguro
		 * via fabrica abstrata (coloque produtos e fabrica concreta para Pagseguro no pacote "pagamentos.pagseguro", que ainda esta vazio). 
		 * Inspire-se nas linhas de implementacao de cada um dos produtos concretos da fabrica Paypal se quiser. Para o produto TransacaoPagseguro
		 * represente-o em string como XML (o do Paypal usou JSON) e mude os System.out para mencionarem "pagseguro.com.br" (estamos simulando uma implementacao
		 * diferente). Onde houver mencao a "SHA-256" em produtos substitua or "MD5" e mencao a "paypal" substitua por "pagseguro", 
		 * considerando que as linhas dos produtos Pagseguro sao inspiradas nas do paypal (para esta simulacao).
		 * 2. Use a classe de nome ProcessadorPagamentoToolkit que sera cliente da fabrica abstrata do padrao.
		 * 3. Copie as linhas de codigo do metodo desta classe anterior, de nome usandoFabricaPagamentosPaypal(). Agora modifique aqui para
		 * que utilize a fabrica de produtos FabricaPagamentosPagaseguro.
		 * 
		 * PERGUNTA ABSTRACT.FACOTRY.B: O que voce teve que fazer para disponibilizar a fabrica de produtos-objeto do Pagseguro?
		 * 
		 * []
		 * 
		 * PERGUNTA ABSTRACT.FACOTRY.C: O que voce teve que fazer para usar a fabrica do Pagseguro? ProcessadorPagamentoToolkit teve que ser mudado
		 * para lidar com essa nova fabrica de produtos? 
		 * 
		 * [Para usar a fabrica do Pagseguro foi necessário apenas fazer um "new FabricaPagamentoPagseguro();"
		 *  e todos os objetos fabricados através dessa fabrica já se tornaram objetos pagseguro automaticamente e flexivelmente,
		 *  pois ProcessadorPagamentoToolkit não precisou ser mudado para lhe dar com esse tipo concreto de fabrica, pois ele ver
		 *  as fabricas pelo seu supertipo.]
		 * 
		 * PERGUNTA ABSTRACT.FACOTRY.D: Nosso codigo simulou produtos concretos para familias Paypal e Pagseguro. 
		 * Nesse caso, devemos entender que tais produtos numa implementacao real utilizariam outros objetos de APIs (.jar) providas
		 * por essas empresas de pagamento? Explique isso considerando uma nova familia de pagamenos denominada Cielo.
		 * 
		 * [COLOQUE SUA RESPOSTA]
		 * 
		 */
		
		FabricaPagamento fabrica = new FabricaPagamentoPagseguro();
		
		ProcessadorPagamentoToolkit processadorPagaamentoToolkit = new ProcessadorPagamentoToolkit(fabrica);
	
		Cartao cartao = fabrica.fabricarCartao();
		cartao.setCVV("509");
		cartao.setNumero("0008989511122211112323");
		cartao.setSenha("7878");
		
		Cliente cliente = fabrica.fabricarCliente();
		cliente.setCPF("000.000.000-50");
		
		processadorPagaamentoToolkit.autorizarPagto(1200f, 0.015f, (short) 10, cartao, cliente);
	}
}
