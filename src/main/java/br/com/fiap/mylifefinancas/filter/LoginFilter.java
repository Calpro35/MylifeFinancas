package br.com.fiap.mylifefinancas.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//Determinando a url na qual o filtro vai atuar. O "*" é um caractere coringa, que vale para qualquer url após /"
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    //Filtra as requisições enviadas ao servidor
    public void doFilter(
            ServletRequest request,
            ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String url = req.getRequestURI();

        if (session.getAttribute("user") == null && !url.endsWith("login") && !url.contains("resources") &&
                !url.contains("login") && !url.contains("usuario")) {
            request.setAttribute("erro", "Entre com o usuário e senha!");
            //TODO - DIRECIONAR O USUÁRIO PARA A PÁGINA DE LOGIN
            request.getRequestDispatcher("/home.jsp").forward(request, resp);
        }else{
            chain.doFilter(request, resp);
        }

    }
}