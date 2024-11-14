package br.com.fiap.mylifefinancas.teste;

import br.com.fiap.mylifefinancas.dao.ServicoDao;
import br.com.fiap.mylifefinancas.dao.TipoServicoDao;
import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.factory.DaoFactory;
import br.com.fiap.mylifefinancas.model.Servico;
import br.com.fiap.mylifefinancas.model.TipoServico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicoDaoTeste {
    public static void main(String[] args) {

        List<Servico> servicos = new ArrayList<>();
        ServicoDao dao = DaoFactory.getServicoDAO();
        TipoServicoDao tipoServicoDao = DaoFactory.getTipoServicoDAO();
        TipoServico tipoRecebimento = tipoServicoDao.listar().get(0);
        TipoServico tipoDespesa = tipoServicoDao.listar().get(1);
        TipoServico tipoInvestimento = tipoServicoDao.listar().get(2);

        //Cadastrar servicos
        Servico servico1 = new Servico(
                1,
                tipoRecebimento,
                "Freelance",
                500,
                LocalDate.of(2024, 10, 25),
                "Freelance de BackEnd");
        servicos.add(servico1);

        Servico servico2 = new Servico(
                1,
                tipoDespesa,
                "Compras",
                300,
                LocalDate.of(2024, 10, 25),
                "Compras do Mês");
        servicos.add(servico2);

        Servico servico3 = new Servico(
                1,
                tipoInvestimento,
                "CDI",
                100,
                LocalDate.of(2024, 10, 25),
                200,
                LocalDate.of(2024, 11, 10),
                "Investimendo em CDI");
        servicos.add(servico3);

        cadastrarServicos(dao, servicos);
/*
        //Buscar um serviço pelo código e atualizar
        servico = dao.buscar(1);
        servico.setDsc_servico("Compra mensal");
        try {
            dao.atualizar(servico);
            System.out.println("Serviço atualizado.");
        } catch (DBException e) {
            e.printStackTrace();
        }

        //Listar os Serviços
        List<Servico> lista = dao.listar(1);
        for (Servico service : lista) {
            System.out.println(
                    service.getNm_servico() + " " + servico.getVl_servico() + " " + service.getDt_servico());
        }*/
        /*
        //Remover um Serviço
        try {
            dao.remover(1);
            System.out.println("Serviço removido.");
        } catch (DBException e) {
            e.printStackTrace();
        }*/

    }

    private static void cadastrarServicos(ServicoDao dao, List<Servico> servicos){
        for (int i = 0; i < servicos.size(); i++) {
            try {
                if(dao.cadastrar(servicos.get(i)) == 1){
                    System.out.println("Servico cadastrado.");
                }
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }
}
