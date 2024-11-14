package br.com.fiap.mylifefinancas.dao;

import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.model.Servico;

import java.util.List;

public interface ServicoDao {
    int cadastrar(Servico servico) throws DBException;
    int atualizar(Servico servico) throws DBException;
    void remover(int codigo) throws DBException;
    Servico buscar(int cdServico);
    List<Servico> listar(int cdUsuario);
    List<Servico> listarRecebimentos(int cdUsuario);
    List<Servico> listarDespesas(int cdUsuario);
    List<Servico> listarInvestimentos(int cdUsuario);
    double buscarTotalDespesas(int cdUsuario);
    double buscarTotalRecebimentos(int cdUsuario);
    double buscarTotalInvestimentos(int cdusuario);
    double buscarTotalEntradaInvestimentos(int cdUsuario);
    double buscarTotalSaidaInvestimentos(int cdUsuario);
}
