package br.com.fiap.mylifefinancas.teste;
import br.com.fiap.mylifefinancas.dao.TipoServicoDao;
import br.com.fiap.mylifefinancas.factory.DaoFactory;
import br.com.fiap.mylifefinancas.model.TipoServico;

import java.util.List;

public class TipoServicoDaoTeste {
    public static void main(String[] args) {

        TipoServicoDao dao = DaoFactory.getTipoServicoDAO();

        //Listar os Tipos de Servico
        List<TipoServico> lista = dao.listar();
        for (TipoServico tipoServico : lista) {
            System.out.println(
                    tipoServico.getCd_tipo_servico() + " - " +
                    tipoServico.getTipo_servico());
        }

    }
}

