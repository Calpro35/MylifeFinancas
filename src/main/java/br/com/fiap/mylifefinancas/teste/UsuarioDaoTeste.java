package br.com.fiap.mylifefinancas.teste;

import br.com.fiap.mylifefinancas.dao.UsuarioDao;
import br.com.fiap.mylifefinancas.exception.DBException;
import br.com.fiap.mylifefinancas.factory.DaoFactory;
import br.com.fiap.mylifefinancas.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class UsuarioDaoTeste {
    public static void main(String[] args) {

        UsuarioDao dao = DaoFactory.getUsuarioDAO();
/*
        //Cadastrar um usuário
        Usuario usuario = new Usuario(
                "Douglas",
                "Pardal Aquino",
                LocalDate.of(2000, 10, 21),
                "douglas.aquino@gmail.com",
                "Aquino987");
        try {
            dao.cadastrar(usuario);
            System.out.println("Usuário cadastrado.");
        } catch (DBException e) {
            e.printStackTrace();
        }*/

        Usuario usuario = new Usuario("douglas.aquino@gmail.com", "Aquino987");
        System.out.println(dao.validar(usuario));
/*
        try {
            Usuario usuario = new Usuario("Douglas",
                    "Pardal Aquino",
                    LocalDate.of(2000, 10, 21),
                    "douglas.aquino@gmail.com",
                    "Aquino987");
            if(dao.validar(usuario)){
                System.out.println("Usuário Validado.");
            }
        } catch (DBException e){
            e.printStackTrace();
        }*/
/*
        //Buscar um usuário pelo código e atualizar
        usuario = dao.buscar(1);
        usuario.setSobrenome_usuario("P. Aquino");
        usuario.setDt_nasc_usuario(LocalDate.of(2000, 8, 16));
        try {
            dao.atualizar(usuario);
            System.out.println("Usuário atualizado.");
        } catch (DBException e) {
            e.printStackTrace();
        }
*/
        //Listar os usuários
        List<Usuario> lista = dao.listar();
        for (Usuario user : lista) {
            System.out.println(
                    user.getNm_usuario() + " " +
                    user.getSobrenome_usuario() + " " +
                    user.getDt_nasc_usuario() + " " +
                    user.getEmail_usuario() + " " +
                    user.getSenha_usuario());
        }
/*
        //Remover um usuário
        try {
            dao.remover(1);
            System.out.println("Usuário removido.");
        } catch (DBException e) {
            e.printStackTrace();
        }
*/
    }
}
