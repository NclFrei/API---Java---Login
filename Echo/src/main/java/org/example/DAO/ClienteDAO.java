package org.example.DAO;

import oracle.jdbc.proxy.annotation.Pre;
import org.example.Model.Cliente;
import org.example.Model.DTO.ClienteDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    Connection conn = CriaConexao.pegarConexao();



    public void adicionar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO tbl_clientes (nome, cpf, senha, email_cliente, idade, endereco_cliente, telefone_cliente, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        int idade = calcularIdade(cliente.getData_nascimento());
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getCpf());
        ps.setString(3, cliente.getSenha());
        ps.setString(4, cliente.getEmail());
        ps.setInt(5, idade);
        ps.setString(6, cliente.getEndereco());
        ps.setInt(7, cliente.getTelefone());
        ps.setDate(8, Date.valueOf(cliente.getData_nascimento()));
        ps.executeUpdate();
    }

    private int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public void deletar(int cod_cliente) throws SQLException {
        String sql = "DELETE FROM tbl_clientes WHERE cod_cliente = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, cod_cliente);
        ps.executeUpdate();
    }

    public List<Cliente> buscarTodosElementos() throws SQLException {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM tbl_clientes";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setCod_cliente(rs.getInt("cod_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setEmail(rs.getString("email_cliente"));
            cliente.setIdade(rs.getInt("idade"));
            cliente.setEndereco(rs.getString("endereco_cliente"));
            cliente.setTelefone(rs.getInt("telefone_cliente"));
            cliente.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
            listaClientes.add(cliente);
        }
        return listaClientes;
    }

    public Cliente buscarClientePorID(int cod_cliente) throws SQLException {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM tbl_clientes WHERE cod_cliente = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, cod_cliente);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            cliente.setCod_cliente(rs.getInt("cod_cliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setEmail(rs.getString("email_cliente"));
            cliente.setIdade(rs.getInt("idade"));
            cliente.setEndereco(rs.getString("endereco_cliente")); // Endereço como String
            cliente.setTelefone(rs.getInt("telefone_cliente"));
            cliente.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
        }
        return cliente;
    }

    public void atualizaDados(int cod_cliente, String nome) throws SQLException {
        String sql = "UPDATE tbl_clientes SET nome = ?, cpf = ?, senha = ?, email_cliente = ?, idade = ?, endereco_cliente = ?, telefone_cliente = ?, data_nascimento = ? WHERE cod_cliente = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setInt(9, cod_cliente);
    }


}
