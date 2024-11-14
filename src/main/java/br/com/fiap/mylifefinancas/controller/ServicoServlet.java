package br.com.fiap.mylifefinancas.controller;

import br.com.fiap.mylifefinancas.dao.TipoServicoDao;
import br.com.fiap.mylifefinancas.dao.ServicoDao;
import br.com.fiap.mylifefinancas.dao.UsuarioDao;
import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.factory.DaoFactory;
import br.com.fiap.mylifefinancas.model.Servico;
import br.com.fiap.mylifefinancas.model.TipoServico;
import br.com.fiap.mylifefinancas.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/servico")
public class ServicoServlet extends HttpServlet {
    private TipoServicoDao tipoServicoDao;
    private ServicoDao dao;
    private UsuarioDao usuarioDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getServicoDAO();
        tipoServicoDao = DaoFactory.getTipoServicoDAO();
        usuarioDao = DaoFactory.getUsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cdTipo = 0;
        try {
            int cdUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
            int cdTipoServico = Integer.parseInt(req.getParameter("tipoServico"));
            String nome = req.getParameter("nomeServico");
            Double valor = Double.parseDouble(req.getParameter("valorEntrada"));
            LocalDate data = LocalDate.parse(req.getParameter("dataEntrada"));
            Double valorS = 0.0;
            LocalDate dataS = null;
            if(!req.getParameter("valorSaida").equals("")){
                valorS = Double.parseDouble(req.getParameter("valorSaida"));
            }
            if(!req.getParameter("dataSaida").equals("")){
                dataS = LocalDate.parse(req.getParameter("dataSaida"));
            }
            String descricao = req.getParameter("descricao");

            Servico servico = new Servico(
                    0,
                    cdUsuario,
                    cdTipoServico,
                    nome,
                    valor,
                    data,
                    valorS,
                    dataS,
                    descricao
            );
            cdTipo = cdTipoServico;
            dao.cadastrar(servico);
            req.setAttribute("mensagem", "Serviço cadastrado com sucesso!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar Serviço");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados da Serviço");
        }
        switch (cdTipo){
            case 1:
                abrirListaRecebimentos(req, resp);
                break;
            case 2:
                abrirListaDespesas(req, resp);
                break;
            case 3:
                abrirListaInvestimentos(req, resp);
                break;
            default:
                acessarDashboard(req, resp);
        }
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cdTipo = 0;
        try {
            int cdServico = Integer.parseInt(req.getParameter("cdServico"));
            int cdUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
            int cdTipoServico = Integer.parseInt(req.getParameter("tipoServico"));
            String nome = req.getParameter("nomeServico");
            Double valor = Double.parseDouble(req.getParameter("valorEntrada"));
            LocalDate data = LocalDate.parse(req.getParameter("dataEntrada"));
            Double valorS = 0.0;
            LocalDate dataS = null;
            if(!req.getParameter("valorSaida").equals("")){
                valorS = Double.parseDouble(req.getParameter("valorSaida"));
            }
            if(!req.getParameter("dataSaida").equals("")){
                dataS = LocalDate.parse(req.getParameter("dataSaida"));
            }
            String descricao = req.getParameter("descricao");
            Servico servico = new Servico(
                    cdServico,
                    cdUsuario,
                    cdTipoServico,
                    nome,
                    valor,
                    data,
                    valorS,
                    dataS,
                    descricao
            );
            dao.atualizar(servico);

            cdTipo = cdTipoServico;
            req.setAttribute("mensagem", "Serviço atualizado com sucesso!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar dados do Serviço");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados do Serviço");
        }
        switch (cdTipo){
            case 1:
                abrirListaRecebimentos(req, resp);
                break;
            case 2:
                abrirListaDespesas(req, resp);
                break;
            case 3:
                abrirListaInvestimentos(req, resp);
                break;
            default:
                acessarDashboard(req, resp);
        }
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        req.setAttribute("cdUsuario", idUsuario);
        Servico servico = dao.buscar(codigo);
        int tipo = servico.getTipoServico().getCd_tipo_servico();
        try {
            dao.remover(codigo);
            req.setAttribute("msg", "Serviço removido!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao remover Serviço");
        }
        switch (tipo){
            case 1:
                abrirListaRecebimentos(req, resp);
                break;
            case 2:
                abrirListaDespesas(req, resp);
                break;
            case 3:
                abrirListaInvestimentos(req, resp);
                break;
            default:
                acessarDashboard(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        switch (acao) {
            case "abrir-dashboard":
                acessarDashboard(req, resp);
                break;
            case "acessar-servico":
                acessarServico(req, resp);
                break;
            case "abrir-form-cadastro":
                abrirFormCadastro(req, resp);
                break;
            case "abrir-form-edicao":
                abrirFormEdicao(req, resp);
                break;
            case "listarTodos":
                abrirListaServicos(req, resp);
                break;
            case "listarRecebimentos":
                abrirListaRecebimentos(req, resp);
                break;
            case "listarDespesas":
                abrirListaDespesas(req, resp);
                break;
            case "listarInvestimentos":
                abrirListaInvestimentos(req, resp);
                break;
        }
    }

    public void acessarDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        Usuario usuario = usuarioDao.buscar(idUsuario);
        req.setAttribute("nome", usuario.getSobrenome_usuario());
        List<Servico> listaServicos = dao.listar(idUsuario);
        List<Servico> listaDespesas = dao.listarDespesas(idUsuario);
        List<Servico> listaRecebimentos = dao.listarRecebimentos(idUsuario);
        List<Servico> listaInvestimentos = dao.listarInvestimentos(idUsuario);
        double totalInvestimentos = 0.00 + dao.buscarTotalInvestimentos(idUsuario);
        double totalDespesas = 0.00 + dao.buscarTotalDespesas(idUsuario);
        double totalRecebimentos = 0.00 + dao.buscarTotalRecebimentos(idUsuario);
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
        //TODO - DIRECIONAR O USUÁRIO PARA A TELA DE DASHBOARD
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }

    private void acessarServico(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idServico = Integer.parseInt(req.getParameter("codigoServico"));
        Servico servico = dao.buscar(idServico);
        req.setAttribute("servico", servico);
        //TODO - DIRECIONAR O USUÁRIO PARA A TELA DE DETALHES
        //req.getRequestDispatcher("detalhe-servico.jsp").forward(req, resp);
    }

    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        req.setAttribute("cdUsuario", idUsuario);
        Usuario usuario = usuarioDao.buscar(idUsuario);
        req.setAttribute("nome", usuario.getSobrenome_usuario());
        List<TipoServico> tipos = tipoServicoDao.listar();
        req.setAttribute("tiposServicos", tipos);
        //TODO - DIRECIONAR O USUÁRIO PARA A PÁGINA DE CADASTRO
        req.getRequestDispatcher("cadastrar-conta.jsp").forward(req, resp);
    }

    private void abrirFormEdicao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        Usuario usuario = usuarioDao.buscar(idUsuario);
        req.setAttribute("cdUsuario", idUsuario);
        req.setAttribute("nome", usuario.getSobrenome_usuario());
        List<TipoServico> tipos = tipoServicoDao.listar();
        req.setAttribute("tiposServicos", tipos);
        int idServico = Integer.parseInt(req.getParameter("cdServico"));
        req.setAttribute("cdServico", idServico);
        Servico servico = dao.buscar(idServico);
        req.setAttribute("servico", servico);
        //TODO - DIRECIONAR O USUÁRIO PARA A PÁGINA DE EDIÇÃO
        req.getRequestDispatcher("editar-conta.jsp").forward(req, resp);
    }

    private void abrirListaServicos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        List<Servico> lista = dao.listar(idUsuario);
        req.setAttribute("servicos", lista);
        //TODO - DIRECIONAR O USUÁRIO PARA A TELA DE LISTAGEM
        //req.getRequestDispatcher("lista-servico.jsp").forward(req, resp);
    }

    private void abrirListaRecebimentos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        req.setAttribute("cdUsuario", idUsuario);
        Usuario usuario = usuarioDao.buscar(idUsuario);
        req.setAttribute("nome", usuario.getSobrenome_usuario());
        List<Servico> lista = dao.listarRecebimentos(idUsuario);
        double totalRecebimentos = 0.00 + dao.buscarTotalRecebimentos(idUsuario);
        req.setAttribute("totalRecebimentos", totalRecebimentos);
        req.setAttribute("recebimentos", lista);
        req.getRequestDispatcher("recebimentos.jsp").forward(req, resp);
    }

    private void abrirListaDespesas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        req.setAttribute("cdUsuario", idUsuario);
        Usuario usuario = usuarioDao.buscar(idUsuario);
        req.setAttribute("nome", usuario.getSobrenome_usuario());
        List<Servico> lista = dao.listarDespesas(idUsuario);
        double totalDespesas = 0.00 + dao.buscarTotalDespesas(idUsuario);
        req.setAttribute("totalDespesas", totalDespesas);
        req.setAttribute("despesas", lista);
        req.getRequestDispatcher("despesas.jsp").forward(req, resp);
    }

    private void abrirListaInvestimentos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        req.setAttribute("cdUsuario", idUsuario);
        Usuario usuario = usuarioDao.buscar(idUsuario);
        req.setAttribute("nome", usuario.getSobrenome_usuario());
        List<Servico> lista = dao.listarInvestimentos(idUsuario);
        double totalInvestimentos = 0.00 + dao.buscarTotalInvestimentos(idUsuario);
        req.setAttribute("totalInvestimentos", totalInvestimentos);
        req.setAttribute("investimentos", lista);
        req.getRequestDispatcher("investiments.jsp").forward(req, resp);
    }
}
