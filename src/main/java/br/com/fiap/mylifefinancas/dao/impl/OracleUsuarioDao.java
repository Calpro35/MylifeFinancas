package br.com.fiap.mylifefinancas.dao.impl;

import br.com.fiap.mylifefinancas.dao.ConnectionManager;
import br.com.fiap.mylifefinancas.dao.UsuarioDao;
import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleUsuarioDao implements UsuarioDao {

    public Connection conexao;

    @Override
    public boolean validar(Usuario usuario) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionManager
                    .getInstance()
                    .getConnection();

            String sql = "SELECT * FROM T_USUARIO " +
                    "WHERE email_usuario = ? AND senha_usuario = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail_usuario());
            stmt.setString(2, usuario.getSenha_usuario());
            rs = stmt.executeQuery();

            if (rs.next()){
                return true;
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
        return false;
    }

    @Override
    public int cadastrar(Usuario usuario) throws DBException {
        PreparedStatement stmt = null;
        int i;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO T_USUARIO" +
                    " (cd_usuario, nm_usuario, sobrenome_usuario, dt_nasc_usuario, email_usuario, senha_usuario)" +
                    " VALUES (SQ_T_USUARIO.NEXTVAL, ?, ?, ?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNm_usuario());
            stmt.setString(2, usuario.getSobrenome_usuario());
            stmt.setDate(3, Date.valueOf(usuario.getDt_nasc_usuario()));
            stmt.setString(4, usuario.getEmail_usuario());
            stmt.setString(5, usuario.getSenha_usuario());
            i = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastradar usuário.");
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
    public int atualizar(Usuario usuario) throws DBException {
        PreparedStatement stmt = null;
        int i;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE T_USUARIO " +
                    "SET nm_usuario = ?, sobrenome_usuario = ?, dt_nasc_usuario = ?," +
                    " email_usuario = ?, senha_usuario = ? WHERE cd_usuario = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNm_usuario());
            stmt.setString(2, usuario.getSobrenome_usuario());
            stmt.setDate(3, Date.valueOf(usuario.getDt_nasc_usuario()));
            stmt.setString(4, usuario.getEmail_usuario());
            stmt.setString(5, usuario.getSenha_usuario());
            stmt.setInt(6, usuario.getCd_usuario());

            i = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar usuário.");
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
            String sql = "DELETE FROM T_USUARIO WHERE cd_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover usuário.");
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
    public Usuario buscar(int id) {
        Usuario usuario = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM T_USUARIO WHERE cd_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("cd_usuario");
                String nome = rs.getString("nm_usuario");
                String sobrenome = rs.getString("sobrenome_usuario");
                LocalDate dataNasc = rs.getDate("dt_nasc_usuario").toLocalDate();
                String email = rs.getString("email_usuario");
                String senha = rs.getString("senha_usuario");

                usuario = new Usuario(codigo, nome, sobrenome, dataNasc, email, senha);
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
        return usuario;
    }

    @Override
    public Usuario buscarCodigo(String email) {
        Usuario usuario = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM T_USUARIO WHERE email_usuario = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("cd_usuario");
                String nome = rs.getString("nm_usuario");
                String sobrenome = rs.getString("sobrenome_usuario");
                LocalDate dataNasc = rs.getDate("dt_nasc_usuario").toLocalDate();
                email = rs.getString("email_usuario");
                String senha = rs.getString("senha_usuario");

                usuario = new Usuario(codigo, nome, sobrenome, dataNasc, email, senha);
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
        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<Usuario>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM T_USUARIO";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("cd_usuario");
                String nome = rs.getString("nm_usuario");
                String sobrenome = rs.getString("sobrenome_usuario");
                LocalDate dataNasc = rs.getDate("dt_nasc_usuario").toLocalDate();
                String email = rs.getString("email_usuario");
                String senha = rs.getString("senha_usuario");

                Usuario usuario =
                        new Usuario(codigo, nome, sobrenome, dataNasc, email, senha);
                lista.add(usuario);

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
