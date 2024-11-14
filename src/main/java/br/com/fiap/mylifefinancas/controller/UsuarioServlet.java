package br.com.fiap.mylifefinancas.controller;

import br.com.fiap.mylifefinancas.dao.UsuarioDao;
import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.factory.DaoFactory;
import br.com.fiap.mylifefinancas.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    private UsuarioDao dao;

    public UsuarioServlet() {
        dao = DaoFactory.getUsuarioDAO();
    }

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
        try {
            String nome = req.getParameter("nome");
            String sobrenome = req.getParameter("sobrenome");
            LocalDate dataNascimento = LocalDate
                    .parse(req.getParameter("data-nasc"));
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            Usuario usuario = new Usuario(
                    0,
                    nome,
                    sobrenome,
                    dataNascimento,
                    email,
                    senha
            );
            dao.cadastrar(usuario);
            req.setAttribute("mensagem", "Usuário cadastrado com sucesso!");

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar usuário");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados do usuário");
        }
        //req.getRequestDispatcher("cadastro-produto.jsp").forward(req, resp);
        //O abrirFormCadastro é chamado para direcionar o usuário para o login após o cadastro
        abrirFormLogin(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            String nome = req
                    .getParameter("nome");
            String sobrenome = req.getParameter("sobrenome");
            LocalDate dataNascimento = LocalDate
                    .parse(req.getParameter("data_nasc"));
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            Usuario usuario = new Usuario(
                    codigo,
                    nome,
                    sobrenome,
                    dataNascimento,
                    email,
                    senha
            );

            dao.atualizar(usuario);

            req.setAttribute("msg", "Dados de Usuário atualizados!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar dados de usuário");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados do usuário");
        }
        abrirPerfil(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.remover(codigo);
            req.setAttribute("msg", "Usuário removido!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao remover usuário");
        }
        abrirFormLogin(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        switch (acao){
            case "abrir-form-login":
                abrirFormLogin(req, resp);
                break;
            case "abrir-perfil":
                abrirPerfil(req, resp);
                break;
            case "abrir-form-cadastro-u":
                abrirFormCadastro(req, resp);
                break;
            case "abrir-form-edicao":
                abrirForm(req, resp);
        }
    }
    private void abrirFormLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
    private void abrirPerfil(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("cdUsuario"));
        req.setAttribute("cdUsuario", idUsuario);
        Usuario usuario = dao.buscar(idUsuario);
        req.setAttribute("nome", usuario.getSobrenome_usuario());
        req.setAttribute("usuario", usuario);
        req.getRequestDispatcher("usuario.jsp").forward(req, resp);
    }
    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("cadastro-usuario.jsp").forward(req, resp);
    }
    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));
        Usuario usuario = dao.buscar(id);
        req.setAttribute("usuario", usuario);
        //TODO - DIRECIONAR O USUÁRIO PARA A TELA DE EDIÇÃO DE PERFIL
        //req.getRequestDispatcher("editar-perfil.jsp").forward(req, resp);
    }
}
