package br.com.fiap.mylifefinancas.controller;

import br.com.fiap.mylifefinancas.dao.*;
import br.com.fiap.mylifefinancas.factory.DaoFactory;
import br.com.fiap.mylifefinancas.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private ServicoDao servicoDao;
    private TipoServicoDao tipoServicoDao;

    private UsuarioDao dao;

    public LoginServlet() {
        dao = DaoFactory.getUsuarioDAO();
        servicoDao = DaoFactory.getServicoDAO();
        tipoServicoDao = DaoFactory.getTipoServicoDAO();
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario(email, senha);
        if (dao.validar(usuario)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", email);
            String mensagem =
                    "Um login foi realizado na plataforma em " + LocalDate.now();
            usuario = dao.buscarCodigo(email);
            request.setAttribute("cdUsuario", usuario.getCd_usuario());
            request.setAttribute("nome", usuario.getSobrenome_usuario());
            acessarDashboard(request, response);
            //request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }else {
            request.setAttribute("erro", "Usuário e/ou senha inválidos");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        //Exclui a sessão do usuáiro
        session.invalidate();
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    public void acessarDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getAttribute("cdUsuario").toString());
        String nome = (String) req.getAttribute("nome");
        List<Servico> listaServicos = servicoDao.listar(idUsuario);
        List<Servico> listaDespesas = servicoDao.listarDespesas(idUsuario);
        List<Servico> listaRecebimentos = servicoDao.listarRecebimentos(idUsuario);
        List<Servico> listaInvestimentos = servicoDao.listarInvestimentos(idUsuario);
        double totalInvestimentos = 0.00 + servicoDao.buscarTotalInvestimentos(idUsuario);
        double totalDespesas = 0.00 + servicoDao.buscarTotalDespesas(idUsuario);
        double totalRecebimentos = 0.00 + servicoDao.buscarTotalRecebimentos(idUsuario);
        double saldo = 0.00 + totalInvestimentos - totalDespesas + totalRecebimentos;
        req.setAttribute("servicos", listaServicos);
        req.setAttribute("investimentos", listaInvestimentos);
        req.setAttribute("despesas", listaDespesas);
        req.setAttribute("recebimentos", listaRecebimentos);
        req.setAttribute("totalInvestimentos", totalInvestimentos);
        req.setAttribute("totalDespesas", totalDespesas);
        req.setAttribute("totalRecebimentos", totalRecebimentos);
        req.setAttribute("saldo", saldo);
        req.setAttribute("cdUsuario", idUsuario);
        req.setAttribute("nome", nome);
        //TODO - DIRECIONAR O USUÁRIO PARA A TELA DE DASHBOARD
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}