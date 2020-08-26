package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
	private int id;
	private String descricao;
	private boolean realizado;
	private boolean ativo;
	private LocalDate data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getData() {
		return data;
	}
	
	public String getDataFormatada() {
		return getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String toString() {
		return "Id: " + getId() + " | Descrição: " + getDescricao() +
				" | criado em: " + getDataFormatada();
	}
}
