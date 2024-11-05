package org.example.Service;
import org.example.DAO.ClienteDAO;
import org.example.Model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.DAO.CriaConexao.conn;

public class ClienteServico {
    ClienteDAO cd = new ClienteDAO();
    public void adicionar(Cliente cliente) throws SQLException {
     // validaCliente();
    cd.adicionar(cliente);
    }

    public void deletar(int cod_cliente) throws SQLException {
        cd.deletar(cod_cliente);
    }

    public List<Cliente> buscar () throws SQLException {
        return cd.buscarTodosElementos();
    }

    public Cliente buscarPorID (int cod_cliente) throws SQLException {
        return cd.buscarClientePorID(cod_cliente);
    }
    public void atualizaDados (int cod_cliente, String nome) throws SQLException {
        cd.atualizaDados(cod_cliente, nome);
    }

    public Cliente validarLogin(String cpf, String senha) throws SQLException {
        String sql = "SELECT * FROM tbl_clientes WHERE cpf = ? AND senha = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cpf);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
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
            return cliente;
        }
        return null;
    }

}
