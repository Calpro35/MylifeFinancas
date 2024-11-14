package br.com.fiap.mylifefinancas.dao.impl;

import br.com.fiap.mylifefinancas.dao.ConnectionManager;
import br.com.fiap.mylifefinancas.dao.ServicoDao;
import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleServicoDao implements ServicoDao {

    public Connection conexao;

    @Override
    public int cadastrar(Servico servico) throws DBException {
        PreparedStatement stmt = null;
        int i;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO T_SERVICO" +
                    " (cd_servico, cd_usuario, cd_tipo_servico, nm_servico, vl_servico, dt_servico, vl_saida_servico, dt_saida_servico, dsc_servico)" +
                    " VALUES (SQ_T_SERVICO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, servico.getCd_usuario());
            stmt.setInt(2, servico.getTipoServico().getCd_tipo_servico());
            stmt.setString(3, servico.getNm_servico());
            stmt.setDouble(4, servico.getVl_servico());
            stmt.setDate(5, Date.valueOf(servico.getDt_servico()));
            if(servico.getVl_saida_servico() <= 0){
                stmt.setNull(6, Types.DOUBLE);
            }
            else {
                stmt.setDouble(6, servico.getVl_saida_servico());
            }
            if(servico.getDt_saida_servico() == null){
                stmt.setNull(7, Types.DATE);
            }
            else {
                stmt.setDate(7, Date.valueOf(servico.getDt_saida_servico()));
            }
            stmt.setString(8, servico.getDsc_servico());

            i = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastradar serviço.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public int atualizar(Servico servico) throws DBException {
        PreparedStatement stmt = null;
        int i;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE T_SERVICO " +
                    "SET cd_usuario = ?, cd_tipo_servico = ?, nm_servico = ?, vl_servico = ?," +
                    " dt_servico = ?, vl_saida_servico = ?, dt_saida_servico = ?, dsc_servico = ? WHERE cd_servico = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, servico.getCd_usuario());
            stmt.setInt(2, servico.getTipoServico().getCd_tipo_servico());
            stmt.setString(3, servico.getNm_servico());
            stmt.setDouble(4, servico.getVl_servico());
            stmt.setDate(5, Date.valueOf(servico.getDt_servico()));
            if(servico.getVl_saida_servico() <= 0){
                stmt.setNull(6, Types.DOUBLE);
            }
            else {
                stmt.setDouble(6, servico.getVl_saida_servico());
            }
            if(servico.getDt_saida_servico() == null){
                stmt.setNull(7, Types.DATE);
            }
            else {
                stmt.setDate(7, Date.valueOf(servico.getDt_saida_servico()));
            }
            stmt.setString(8, servico.getDsc_servico());
            stmt.setInt(9, servico.getCd_servico());

            i = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar serviço.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public void remover(int codigo) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM T_SERVICO WHERE cd_servico = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover serviço.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Servico buscar(int id) {
        Servico servico = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT T_SERVICO.cd_servico, T_SERVICO.cd_usuario, T_TIPO_SERVICO.cd_tipo_servico, " +
                    "T_TIPO_SERVICO.tipo_servico, T_SERVICO.nm_servico, T_SERVICO.vl_servico, T_SERVICO.dt_servico, " +
                    "T_SERVICO.vl_saida_servico, T_SERVICO.dt_saida_servico, T_SERVICO.dsc_servico FROM T_SERVICO " +
                    "INNER JOIN T_TIPO_SERVICO ON T_SERVICO.cd_tipo_servico = T_TIPO_SERVICO.cd_tipo_servico AND " +
                    "T_SERVICO.cd_servico = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("cd_servico");
                int cdUsuario = rs.getInt("cd_usuario");
                int cdTipoServico = rs.getInt("cd_tipo_servico");
                String tipoServico = rs.getString("tipo_servico");
                String nome = rs.getString("nm_servico");
                Double valor = rs.getDouble("vl_servico");
                LocalDate data = rs.getDate("dt_servico").toLocalDate();
                Double valorS = rs.getDouble("vl_saida_servico");
                if (rs.wasNull()) {
                    valorS = 0.0;
                }
                LocalDate dataSaida = null;
                Date dataS = rs.getDate("dt_saida_servico");
                if (!rs.wasNull()) {
                     dataSaida = dataS.toLocalDate();
                }
                String descricao = rs.getString("dsc_servico");

                TipoServico tipo = new TipoServico(cdTipoServico, tipoServico);
                servico = new Servico(codigo, cdUsuario, tipo, nome, valor, data, valorS, dataSaida, descricao);
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
        return servico;
    }

    @Override
    public List<Servico> listar(int cdUsuario) {
        List<Servico> servicos = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT T_SERVICO.cd_servico, T_SERVICO.cd_usuario, T_TIPO_SERVICO.cd_tipo_servico, " +
                    "T_TIPO_SERVICO.tipo_servico, T_SERVICO.nm_servico, T_SERVICO.vl_servico, T_SERVICO.dt_servico, " +
                    "T_SERVICO.vl_saida_servico, T_SERVICO.dt_saida_servico, T_SERVICO.dsc_servico FROM T_SERVICO " +
                    " INNER JOIN T_TIPO_SERVICO ON T_SERVICO.cd_tipo_servico = T_TIPO_SERVICO.cd_tipo_servico AND" +
                    " T_SERVICO.cd_usuario = ? " +
                    "ORDER BY dt_servico DESC";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("cd_servico");
                cdUsuario = rs.getInt("cd_usuario");
                int cdTipoServico = rs.getInt("cd_tipo_servico");
                String tipoServico = rs.getString("tipo_servico");
                String nome = rs.getString("nm_servico");
                Double valor = rs.getDouble("vl_servico");
                LocalDate data = rs.getDate("dt_servico").toLocalDate();
                Double valorS = rs.getDouble("vl_saida_servico");
                if (rs.wasNull()) {
                    valorS = 0.0;
                }
                LocalDate dataSaida = null;
                Date dataS = rs.getDate("dt_saida_servico");
                if (!rs.wasNull()) {
                    dataSaida = dataS.toLocalDate();
                }
                String descricao = rs.getString("dsc_servico");

                TipoServico tipo = new TipoServico(cdTipoServico, tipoServico);
                servicos.add(new Servico(codigo, cdUsuario, tipo, nome, valor, data, valorS, dataSaida, descricao));
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
        return servicos;
    }

    @Override
    public List<Servico> listarRecebimentos(int cdUsuario) {
        List<Servico> servicos = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT T_SERVICO.cd_servico, T_SERVICO.cd_usuario, T_TIPO_SERVICO.cd_tipo_servico, " +
                    "T_TIPO_SERVICO.tipo_servico, T_SERVICO.nm_servico, T_SERVICO.vl_servico, T_SERVICO.dt_servico, " +
                    "T_SERVICO.vl_saida_servico, T_SERVICO.dt_saida_servico, T_SERVICO.dsc_servico FROM T_SERVICO " +
                    " INNER JOIN T_TIPO_SERVICO ON T_SERVICO.cd_tipo_servico = T_TIPO_SERVICO.cd_tipo_servico AND" +
                    " T_SERVICO.cd_usuario = ? AND T_SERVICO.cd_tipo_servico = 1 " +
                    "ORDER BY dt_servico DESC";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("cd_servico");
                cdUsuario = rs.getInt("cd_usuario");
                int cdTipoServico = rs.getInt("cd_tipo_servico");
                String tipoServico = rs.getString("tipo_servico");
                String nome = rs.getString("nm_servico");
                Double valor = rs.getDouble("vl_servico");
                LocalDate data = rs.getDate("dt_servico").toLocalDate();
                Double valorS = rs.getDouble("vl_saida_servico");
                if (rs.wasNull()) {
                    valorS = 0.0;
                }
                LocalDate dataSaida = null;
                Date dataS = rs.getDate("dt_saida_servico");
                if (!rs.wasNull()) {
                    dataSaida = dataS.toLocalDate();
                }
                String descricao = rs.getString("dsc_servico");

                TipoServico tipo = new TipoServico(cdTipoServico, tipoServico);
                servicos.add(new Servico(codigo, cdUsuario, tipo, nome, valor, data, valorS, dataSaida, descricao));
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
        return servicos;
    }

    @Override
    public List<Servico> listarDespesas(int cdUsuario) {
        List<Servico> servicos = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT T_SERVICO.cd_servico, T_SERVICO.cd_usuario, T_TIPO_SERVICO.cd_tipo_servico, " +
                    "T_TIPO_SERVICO.tipo_servico, T_SERVICO.nm_servico, T_SERVICO.vl_servico, T_SERVICO.dt_servico, " +
                    "T_SERVICO.vl_saida_servico, T_SERVICO.dt_saida_servico, T_SERVICO.dsc_servico FROM T_SERVICO " +
                    " INNER JOIN T_TIPO_SERVICO ON T_SERVICO.cd_tipo_servico = T_TIPO_SERVICO.cd_tipo_servico AND" +
                    " T_SERVICO.cd_usuario = ? AND T_SERVICO.cd_tipo_servico = 2 " +
                    "ORDER BY dt_servico DESC";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("cd_servico");
                cdUsuario = rs.getInt("cd_usuario");
                int cdTipoServico = rs.getInt("cd_tipo_servico");
                String tipoServico = rs.getString("tipo_servico");
                String nome = rs.getString("nm_servico");
                Double valor = rs.getDouble("vl_servico");
                LocalDate data = rs.getDate("dt_servico").toLocalDate();
                Double valorS = rs.getDouble("vl_saida_servico");
                if (rs.wasNull()) {
                    valorS = 0.0;
                }
                LocalDate dataSaida = null;
                Date dataS = rs.getDate("dt_saida_servico");
                if (!rs.wasNull()) {
                    dataSaida = dataS.toLocalDate();
                }
                String descricao = rs.getString("dsc_servico");

                TipoServico tipo = new TipoServico(cdTipoServico, tipoServico);
                servicos.add(new Servico(codigo, cdUsuario, tipo, nome, valor, data, valorS, dataSaida, descricao));
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
        return servicos;
    }

    @Override
    public List<Servico> listarInvestimentos(int cdUsuario) {
        List<Servico> servicos = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT T_SERVICO.cd_servico, T_SERVICO.cd_usuario, T_TIPO_SERVICO.cd_tipo_servico, " +
                    "T_TIPO_SERVICO.tipo_servico, T_SERVICO.nm_servico, T_SERVICO.vl_servico, T_SERVICO.dt_servico, " +
                    "T_SERVICO.vl_saida_servico, T_SERVICO.dt_saida_servico, T_SERVICO.dsc_servico FROM T_SERVICO " +
                    " INNER JOIN T_TIPO_SERVICO ON T_SERVICO.cd_tipo_servico = T_TIPO_SERVICO.cd_tipo_servico AND" +
                    " T_SERVICO.cd_usuario = ? AND T_SERVICO.cd_tipo_servico = 3 " +
                    "ORDER BY dt_servico DESC";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("cd_servico");
                cdUsuario = rs.getInt("cd_usuario");
                int cdTipoServico = rs.getInt("cd_tipo_servico");
                String tipoServico = rs.getString("tipo_servico");
                String nome = rs.getString("nm_servico");
                Double valor = rs.getDouble("vl_servico");
                LocalDate data = rs.getDate("dt_servico").toLocalDate();
                Double valorS = rs.getDouble("vl_saida_servico");
                if (rs.wasNull()) {
                    valorS = 0.0;
                }
                LocalDate dataSaida = null;
                Date dataS = rs.getDate("dt_saida_servico");
                if (!rs.wasNull()) {
                    dataSaida = dataS.toLocalDate();
                }
                String descricao = rs.getString("dsc_servico");

                TipoServico tipo = new TipoServico(cdTipoServico, tipoServico);
                servicos.add(new Servico(codigo, cdUsuario, tipo, nome, valor, data, valorS, dataSaida, descricao));
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
        return servicos;
    }

    @Override
    public double buscarTotalDespesas(int cdUsuario) {
        double totalDespesas = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT SUM(T_SERVICO.vl_servico) AS total_despesas " +
                    "FROM T_SERVICO WHERE T_SERVICO.cd_tipo_servico = 2 AND T_SERVICO.cd_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalDespesas = rs.getDouble("total_despesas");
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
        return totalDespesas;
    }

    @Override
    public double buscarTotalRecebimentos(int cdUsuario) {
        double totalRecebimentos = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT SUM(T_SERVICO.vl_servico) AS total_recebimentos " +
                    "FROM T_SERVICO WHERE T_SERVICO.cd_tipo_servico = 1 AND T_SERVICO.cd_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalRecebimentos = rs.getDouble("total_recebimentos");
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
        return totalRecebimentos;
    }

    @Override
    public double buscarTotalInvestimentos(int cdusuario) {
        double totalInvestimentos = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT SUM(T_SERVICO.vl_saida_servico - T_SERVICO.vl_servico) AS total_investimentos " +
                    "FROM T_SERVICO WHERE T_SERVICO.cd_tipo_servico = 3 AND T_SERVICO.cd_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdusuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalInvestimentos = rs.getDouble("total_investimentos");
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
        return totalInvestimentos;
    }

    @Override
    public double buscarTotalEntradaInvestimentos(int cdUsuario) {
        double totalEntrada = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT SUM(T_SERVICO.vl_servico) AS total_entrada " +
                    "FROM T_SERVICO WHERE T_SERVICO.cd_tipo_servico = 3 AND T_SERVICO.cd_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalEntrada = rs.getDouble("total_entrada");
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
        return totalEntrada;
    }

    @Override
    public double buscarTotalSaidaInvestimentos(int cdUsuario) {
        double totalSaida = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT SUM(T_SERVICO.vl_servico) AS total_saida " +
                    "FROM T_SERVICO WHERE T_SERVICO.cd_tipo_servico = 3 AND T_SERVICO.cd_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cdUsuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalSaida = rs.getDouble("total_saida");
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
        return totalSaida;
    }

}
