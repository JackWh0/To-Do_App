package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Item;

public class ItemDAO {

	private Connection connection;

	public ItemDAO() {
		connection = new ConnectionFactory().getConnection();
	}

	public void adicionarItem(Item item) {
		String querry = "insert into item(descricao, data)" + 
					    "values(?,?)";

		try {
			PreparedStatement stm = connection.prepareStatement(querry);

			stm.setString(1, item.getDescricao());
			stm.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stm.execute();
			stm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void editarItem(Item item) {
		try {
			String querry = "update item set descricao = ?" + "where id = ?";
			PreparedStatement stm = connection.prepareStatement(querry);
			stm.setString(1, item.getDescricao());
			stm.setInt(2, item.getId());
			stm.execute();
			stm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void marcarItemComoRealizado(Item item) {
		try {
			String querry = "update item set realizado = ?, ativo = ? " + 
						    "where id = ?";
			PreparedStatement stm = connection.prepareStatement(querry);
			stm.setBoolean(1, true);
			stm.setBoolean(2, false);
			stm.setInt(3, item.getId());
			stm.execute();
			stm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void removerItem(Item item) {
		try {
			String querry = "delete from item where id = ?";
			PreparedStatement stm = connection.prepareStatement(querry);
			stm.setInt(1, item.getId());
			stm.execute();
			stm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Item> getListaItensRealizados() {
		try {
			List<Item> listaItens = new ArrayList<Item>();
			String querry = "select * from Item where realizado = 1";
			PreparedStatement stm = connection.prepareStatement(querry);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setDescricao(rs.getString("descricao"));
				item.setData(rs.getObject(5, LocalDate.class));
				listaItens.add(item);
			}

			rs.close();
			stm.close();
			return listaItens;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Item> getListaItensAtivos() {
		try {
			List<Item> listaItens = new ArrayList<Item>();
			String querry = "select * from Item where ativo = 1";
			PreparedStatement stm = connection.prepareStatement(querry);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setDescricao(rs.getString("descricao"));
				item.setData(rs.getObject(5, LocalDate.class));
				listaItens.add(item);
			}

			rs.close();
			stm.close();
			return listaItens;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Item buscarPelaDescricao(String descricao) {
		String querry = "select * from Item where descricao like '%" + descricao + "%'";
		Item item = new Item();

		try {
			PreparedStatement stm = connection.prepareStatement(querry);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setDescricao(rs.getString("descricao"));
				item.setData(rs.getObject(5, LocalDate.class));
			}
			
			rs.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return item;
	}
}
