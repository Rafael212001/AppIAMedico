package org.acme;

import java.util.List;

public class Pessoa {
	private String nome;
	private float peso;
	private String cpf;
	private List<Sintoma> sintomas;
	
	public String toString() {
		String str = "{nome:" + nome + ", peso:" + peso + ", cpf:" + cpf
			+ " sintomas: [";
		for (int i = 0; sintomas != null && i < sintomas.size(); i++) {
			str += "{nome:" + sintomas.get(i).getNome() + ", peso:" + sintomas.get(i).getValor() + "}, "; 
		}
		str += "]}";
		return str;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<Sintoma> getSintomas() {
		return sintomas;
	}
	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}
	
}
