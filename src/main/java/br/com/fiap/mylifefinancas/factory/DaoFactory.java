package br.com.fiap.mylifefinancas.factory;

import br.com.fiap.mylifefinancas.dao.*;
import br.com.fiap.mylifefinancas.dao.impl.*;

public class DaoFactory {
    public static UsuarioDao getUsuarioDAO() {
        return new OracleUsuarioDao();
    }
    public static ServicoDao getServicoDAO(){
        return new OracleServicoDao();
    }
    public static TipoServicoDao getTipoServicoDAO(){
        return new OracleTipoServicoDao();
    }
}
