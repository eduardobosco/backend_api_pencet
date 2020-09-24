package com.pencet.ecommerce.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pencet.ecommerce.enums.PedidoStatus;
import com.pencet.ecommerce.model.Carrinho;
import com.pencet.ecommerce.model.Categoria;
import com.pencet.ecommerce.model.Cliente;
import com.pencet.ecommerce.model.Endereco;
import com.pencet.ecommerce.model.Funcionario;
import com.pencet.ecommerce.model.Pedido;
import com.pencet.ecommerce.model.Produto;
import com.pencet.ecommerce.repository.CarrinhoRepository;
import com.pencet.ecommerce.repository.CategoriaRepository;
import com.pencet.ecommerce.repository.ClienteRepository;
import com.pencet.ecommerce.repository.FuncionarioRepository;
import com.pencet.ecommerce.repository.PedidoRepository;
import com.pencet.ecommerce.repository.ProdutoRepository;

@Configuration
@Profile("test")

public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Electronicos", "Secao de produtos Eletronicos");
		Categoria cat2 = new Categoria(null, "Livros", "Livros e Literatura");
		Categoria cat3 = new Categoria(null, "Computadores", "Todos os modelos");
		Categoria cat4 = new Categoria(null, "Moveis", "Secao para mobiliar sua casa");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		
		Funcionario f1 = new Funcionario(null, "Julio Borsato", "25687945123");
		Funcionario f2 = new Funcionario(null, "Evaldo Carvalho", "25687945165");
		Funcionario f3 = new Funcionario(null, "John Lennom", "25687978523");
		
		funcionarioRepository.saveAll(Arrays.asList(f1, f2, f3));
		
		Produto p1 = new Produto(null, "Radio MP3 Philips", "Radio dolby sourround", 20 , 799.9 , Instant.parse("2019-06-20T19:53:07Z"), cat1, f1);
		Produto p2 = new Produto(null, "Este mundo Tenebroso I", "Historias da Vida real", 5 ,29.9 , Instant.parse("2020-06-20T19:53:07Z"), cat2, f2);
		Produto p3 = new Produto(null, "Sofa 2 lugares", "Sofa chenile dois lugares compacto", 5 , 399.9 , Instant.parse("2019-06-20T19:53:07Z"), cat4, f3);
		Produto p4 = new Produto(null, "Mindset", "O poder do pensamento Positivo", 20 , 49.9 , Instant.parse("2018-06-20T19:53:07Z"), cat2, f2);
		Produto p5 = new Produto(null, "TV LG 47 Pol", "Televisao Ultra HD", 5 , 2800.90 , Instant.parse("2019-06-20T19:53:07Z"), cat1, f1);
		Produto p6 = new Produto(null, "Notebook Acer", "Notebook Core i5 4GB", 2, 1800.0, Instant.parse("2015-06-20T19:53:07Z"), cat3, f3);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		

		Cliente c1 = new Cliente(null, "Eduardo Bosco", "ebosco", "09303628705", "eduardobosco@gmail.com", Instant.parse("1982-06-20T19:53:07Z"));
		Cliente c2 = new Cliente(null, "Paulo Barreto", "pbarreto", "64321185734", "pbarreto@gmail.com",Instant.parse("1985-06-20T19:53:07Z"));
		
		
		Pedido o1 = new Pedido(null, Instant.parse("2020-06-20T19:53:07Z"), PedidoStatus.PAGO, c1);
		Pedido o2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), PedidoStatus.ENTREGUE, c2);
		Pedido o3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), PedidoStatus.PAGO, c1);

		clienteRepository.saveAll(Arrays.asList(c1, c2));
		
		pedidoRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		Carrinho cart1 = new Carrinho(o1, p1, p1.getValor(), 2);
		Carrinho cart2 = new Carrinho(o1, p2, p2.getValor(), 5);
		Carrinho cart3 = new Carrinho(o3, p4, p4.getValor(), 6);
		Carrinho cart4 = new Carrinho(o2, p6, p6.getValor(), 2);

		carrinhoRepository.saveAll(Arrays.asList(cart1, cart2, cart3, cart4));
		
		Endereco e1 = new Endereco(null,"Luiz Biazzi", 21 , "NA", "Cascatinha", "Petropolis", "RJ", "25710-200", c1);
		c1.setEndereco(e1);

		Endereco e2 = new Endereco(null,"Bernardo Tosta", 103 , "NA", "Cascatinha", "Petropolis", "RJ", "25710-200", c2);
		c2.setEndereco(e2);
		
		clienteRepository.save(c1);
		clienteRepository.save(c2);
	}

}
