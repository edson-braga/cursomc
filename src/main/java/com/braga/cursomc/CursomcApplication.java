package com.braga.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.braga.cursomc.domain.Categoria;
import com.braga.cursomc.domain.Cidade;
import com.braga.cursomc.domain.Cliente;
import com.braga.cursomc.domain.Endereco;
import com.braga.cursomc.domain.Estado;
import com.braga.cursomc.domain.Pagamento;
import com.braga.cursomc.domain.PagamentoComBoleto;
import com.braga.cursomc.domain.PagamentoComCartao;
import com.braga.cursomc.domain.Pedido;
import com.braga.cursomc.domain.Produto;
import com.braga.cursomc.domain.enums.EstadoPagamento;
import com.braga.cursomc.domain.enums.TipoCliente;
import com.braga.cursomc.repositories.CategoriaRepository;
import com.braga.cursomc.repositories.CidadeRepository;
import com.braga.cursomc.repositories.ClienteRepository;
import com.braga.cursomc.repositories.EnderecoRepository;
import com.braga.cursomc.repositories.EstadoRepository;
import com.braga.cursomc.repositories.PagamentoRepository;
import com.braga.cursomc.repositories.PedidoRepository;
import com.braga.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		
		categoriaRepository.save(Arrays.asList(categoria1, categoria2));
		produtoRepository.save(Arrays.asList(produto1, produto2, produto3));

		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.save(Arrays.asList(estado1, estado2));
		cidadeRepository.save(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "04413098755", TipoCliente.PESSOAFISICA);
		cliente.getTelefones().addAll(Arrays.asList("45367655", "78669088"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "98767465", cliente, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "64775647", cliente, cidade2);
		
		cliente.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.save(Arrays.asList(cliente));
		enderecoRepository.save(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido pedido1 = new Pedido(null, simpleDateFormat.parse("30/09/2017 10:32"), cliente, endereco1);
		Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("10/10/2017 19:35"), cliente, endereco2);
		
		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, simpleDateFormat.parse("20/10/2017 00:00"), null);
		
		pedido2.setPagamento(pagamento2);
		
		cliente.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pagamentoRepository.save(Arrays.asList(pagamento1, pagamento2));
		pedidoRepository.save(Arrays.asList(pedido1, pedido2));
	}
}
