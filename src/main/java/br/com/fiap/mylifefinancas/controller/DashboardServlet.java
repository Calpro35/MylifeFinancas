package br.com.fiap.mylifefinancas.controller;

import br.com.fiap.mylifefinancas.dao.*;
import br.com.fiap.mylifefinancas.factory.DaoFactory;
import br.com.fiap.mylifefinancas.model.Servico;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private UsuarioDao usuarioDao;
    private ServicoDao servicoDao;
    private TipoServicoDao tipoServicoDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usuarioDao = DaoFactory.getUsuarioDAO();
        servicoDao = DaoFactory.getServicoDAO();
        tipoServicoDao = DaoFactory.getTipoServicoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //acessarDashboard(req, resp);
    }

    private void acessarDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        List<Servico> listaServicos = servicoDao.listar(idUsuario);
        List<Servico> listaDespesas = servicoDao.listarDespesas(idUsuario);
        List<Servico> listaRecebimentos = servicoDao.listarRecebimentos(idUsuario);
        List<Servico> listaInvestimentos = servicoDao.listarInvestimentos(idUsuario);
        double totalInvestimentos = servicoDao.buscarTotalInvestimentos(idUsuario);
        double totalDespesas = servicoDao.buscarTotalDespesas(idUsuario);
        double totalRecebimentos = servicoDao.buscarTotalRecebimentos(idUsuario);
        double saldo = totalInvestimentos + totalDespesas + totalRecebimentos;
        req.setAttribute("investimentos", listaInvestimentos);
        req.setAttribute("despesas", listaDespesas);
        req.setAttribute("recebimentos", listaRecebimentos);
        req.setAttribute("totalInvestimentos", totalInvestimentos);
        req.setAttribute("totalDespesas", totalDespesas);
        req.setAttribute("totalRecebimentos", totalRecebimentos);
        req.setAttribute("saldo", saldo);
        //TODO - DIRECIONAR O USUÁRIO PARA A TELA DE DASHBOARD
        //req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
