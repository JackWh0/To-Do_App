package execucao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.ItemDAO;
import model.Item;

public class App {
	
	public static void main(String[] args) throws SQLException {
		ItemDAO itemDAO = new ItemDAO();
		Item item = new Item();
		Scanner read = new Scanner(System.in);
		int resposta = 9;
		String descricao;
		int id;
		String idString;
		
		while(resposta != 0) {
			System.out.println("\n--------- Menu To-Do App ---------");
			System.out.println("| 1 - Adicionar item a lista");
			System.out.println("| 2 - Editar um item da lista");
			System.out.println("| 3 - Marcar item como realizado");
			System.out.println("| 4 - Remover um item");
			System.out.println("| 5 - Ver lista dos itens realizados");
			System.out.println("| 6 - Ver lista dos itens ativos");
			System.out.println("| 7 - Buscar item pela descricao");
			System.out.println("| 0 - Encerrar o App");
			System.out.print("| Sua escolha: ");
			resposta = read.nextInt();
			read.nextLine(); 
			switch (resposta) {
			case 0: 
				System.out.println("\n\nAdeus!");
				break;
			case 1:
				System.out.println("\n------- Adição de item na lista:");
				System.out.println("Digite a descrição do que fazer:");
				descricao = read.nextLine();
				item.setDescricao(descricao);
				if(itemDAO.adicionarItem(item)) {
					System.out.println("Item adicionado com sucesso!");
				}else {
					System.out.println("Erro ao adicionar item.");
				}
				break;
			case 2:
				System.out.println("\n------- Edição de item da lista:");
				System.out.println("Digite o id do item que você deseja editar:");
				idString = read.nextLine();
				id = converterIdParaNumero(idString);
				
				if(id == 0) {
					break;
				}
				item = itemDAO.verificarSeExistePeloId(id);
			
				if(item.getId() != 0 && item.isAtivo() == true) {
					System.out.println("Digite a nova descricao do item:");
					descricao = read.nextLine();
					item.setDescricao(descricao);
					item.setId(id);
					if(itemDAO.editarItem(item)) {
						System.out.println("Item editado com sucesso.");
					}else {
						System.out.println("Erro ao tentar editar o item.");
					}
				}else {
					System.out.println("Item inexistente ou ja realizado (desativado).");
				}
				break;
			case 3:
				System.out.println("\n------- Marcar item da lista como realizado:");
				System.out.println("Digite o id do item:");
				idString = read.nextLine();
				id = converterIdParaNumero(idString);
				
				if(id == 0) {
					break;
				}
				
				item.setId(id);
				item = itemDAO.verificarSeExistePeloId(id);
				
				if(item.getId() != 0 && item.isRealizado() == false) {
					itemDAO.marcarItemComoRealizado(item);
					System.out.println("Item marcado como realizado!");
				}else {
					System.out.println("Item nao existe ou ja foi realizado.");
				}
				break;
			case 4:
				System.out.println("\n------- Deletar item da lista:");
				System.out.println("Digite o id do item:");
				idString = read.nextLine();
				id = converterIdParaNumero(idString);
				item = itemDAO.verificarSeExistePeloId(id);
				
				if(id == 0) {
					break;
				}

				if(item.getId() != 0) {
					item.setId(id);
					itemDAO.removerItem(item);
					System.out.println("Item removido com sucesso!");
				}else {
					System.out.println("Item nao encontrado.");
				}
				break;
			case 5:
				System.out.println("\n------- Itens realizados:");
				List<Item> itensRealizados = itemDAO.getListaItensRealizados();
				if(itensRealizados.isEmpty()) {
					System.out.println("Ainda nao ha nenhum item nesta lista.");
				}else {
					for(Item its: itensRealizados) {
						System.out.println(its.toString());
					}
				}
				break;
			case 6:
				System.out.println("\n------- Itens ativos:");
				List<Item> itensAtivos = itemDAO.getListaItensAtivos();
				if(itensAtivos.isEmpty()) {
					System.out.println("Ainda nao ha nenhum item nesta lista.");
				}else {
					for(Item its: itensAtivos) {
						System.out.println(its.toString());
					}
				}
				break;
			case 7: 
				System.out.println("\n------- Buscar item pela descricao:");
				System.out.println("Digite a descricao do item:");
				descricao = read.nextLine();
				item = itemDAO.buscarPelaDescricao(descricao);
				if(item.getId() != 0) {
					System.out.println(item.toString());
				}else {
					System.out.println("Item nao encontrado");
				}
				break;
			default:
				System.out.println("Opção invalida.");
				break;
			}
		}
		System.out.println("Volte sempre!");
		read.close();
	}
	
	static int converterIdParaNumero(String id) {
		try {
			 int idNumerico = Integer.parseInt(id);
			 return idNumerico;
		}catch(java.lang.NumberFormatException e) {
			System.out.println("Erro! Digite apenas números no campo do id!");
		}
		return 0;
	}
}
