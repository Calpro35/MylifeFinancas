package br.com.fiap.mylifefinancas.dao.impl;

import br.com.fiap.mylifefinancas.dao.ConnectionManager;
import br.com.fiap.mylifefinancas.dao.TipoServicoDao;
import br.com.fiap.mylifefinancas.model.TipoServico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleTipoServicoDao implements TipoServicoDao {

    public Connection conexao;

    @Override
    public List<TipoServico> listar() {
        List<TipoServico> lista = new ArrayList<TipoServico>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM T_TIPO_SERVICO";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("cd_tipo_servico");
                String tipo = rs.getString("tipo_servico");

                TipoServico tipoServico = new TipoServico(codigo, tipo);
                lista.add(tipoServico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
