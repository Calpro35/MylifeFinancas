<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title> Usuario |My Life Finanças </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="resources/css/dashboard.css">
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="stylesheet" href="resources/css/aside.css">
    <link rel="stylesheet" href="resources/css/index.css" />



    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
            rel="stylesheet"
    />
</head>
<body>
<%@include file="headerApp.jsp" %>
<!-- Dashboard -->
<div class="container dashboard-container border pt-5 mt-2">
    <div class="row w-100">

        <!-- Aside -->
        <%@include file="aside.jsp" %>
        <!-- Conteúdo principal -->

        <div class="dashboard-content col-9">
        <div class="Container d-flex row ">


            <h1 class="fs-4 mt-5 ms-5">Perfil</h1>


            <div class="text_center row container-md  mt-4 d-flex align-content-center justify-content-center" >

                <h2 class="fs-5 text-center mb-2 "  >Sessão do usuário </h2>

                <form class=" w-50  mt-3" action="usuario?acao=editar" method="post" >
                    <input type="text" name="cdUsuario" class="d-none" value="${usuario.cd_usuario}">
                    <input type="text" name="codigo" class="d-none" value="${usuario.cd_usuario}">
                    <div class="form-group">
                        <label class="mb-1 " for="id-nome">Nome completo</label>
                        <input type="text" name="nome" id="id-nome" class="form-control p-2"
                               placeholder="Nome" value="${usuario.nm_usuario}">
                    </div>

                    <div class="form-group  mt-2">
                        <label class="mb-1" for="id-sobrenome">User name</label>
                        <input type="text" name="sobrenome" id="id-sobrenome" class="form-control p-2"
                               placeholder="Nome usuario" value="${usuario.sobrenome_usuario}">
                    </div>

                    <div class="form-group w-50  mt-2">
                        <label class="mb-1" for="id-data-nasc">Data de Nascimento</label>
                        <input type="date" name="data_nasc" id="id-data-nasc"
                               class="form-control p-2"
                               value="${usuario.dt_nasc_usuario}">
                    </div>

                    <div class="form-group mt-2 " >
                        <label class="mb-1" for="id-email">Email*</label>
                        <input  type="text" name="email" id="id-email" class="form-control p-2"
                                placeholder="email@email.com" value="${usuario.email_usuario}">

                    </div>


                    <div class="form-group mt-2 usuario-container">
                        <label class="mb-1" for="id-senha">Senha</label>
                        <input type="password" name="senha" id="id-senha" class="form-control p-2"
                               placeholder=" senha" value="${usuario.senha_usuario}">
                        <i id="toggleUser" class= " icon_senha_primary bi bi-eye-slash" ></i>
                    </div>


                    <div class="text-center mt-3 gap-2 mb-3 d-flex justify-content-center">
                        <input  type="submit" value="Salvar" class="btn btn-primary mt-3">
                    </div>


                </form>


            </div>



        </div>


        </div>
    </div>
</div>

<%@include file="footerApp.jsp" %>
<script src="./resources/js/bootstrap.bundle.js"></script>
<script src="./resources/js/usuario.js"></script>
</body>
</html>
