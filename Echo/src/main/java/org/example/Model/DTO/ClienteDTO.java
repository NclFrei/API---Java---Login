package org.example.Model.DTO;

import java.time.LocalDate;

public class ClienteDTO { //entidade
    private int cod_cliente;
    private String nome;
    private String cpf ;
    private String email;
    private int idade;
    private String endereco;
    private int telefone;
    private LocalDate data_nascimento;


   public ClienteDTO(){

   }

    public ClienteDTO(LocalDate data_nascimento, int telefone, String endereco, int idade, String email, String cpf, String nome, int cod_cliente) {
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idade = idade;
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
        this.cod_cliente = cod_cliente;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
