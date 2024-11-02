package org.example.Service;
import org.example.DAO.ClienteDAO;
import org.example.Model.Cliente;

import java.sql.SQLException;
import java.util.List;

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


}
