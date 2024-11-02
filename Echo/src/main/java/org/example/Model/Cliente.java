package org.example.Model;

import java.time.LocalDate;

public class Cliente {
    private int cod_cliente;
    private String nome;
    private String cpf ;
    private String senha;
    private String email;
    private int idade;
    private String endereco;
    private int telefone;
    private LocalDate data_nascimento;


   public Cliente(){ // construtor padrão
   }

    public Cliente(int cod_cliente, String nome, String cpf, String senha, String email, int idade, String endereco, int telefone, LocalDate data_nascimento) {
        this.cod_cliente = cod_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.idade = idade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
