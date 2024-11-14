package br.com.fiap.mylifefinancas.dao;

import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.model.Usuario;

import java.util.List;

public interface UsuarioDao {

    boolean validar(Usuario usuario) throws DBException;
    int cadastrar(Usuario usuario) throws DBException;
    int atualizar(Usuario usuario) throws DBException;
    void remover(int codigo) throws DBException;
    Usuario buscar(int id);
    Usuario buscarCodigo(String email);
    List<Usuario> listar();

}