<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>

<html>

<head>
  <title>My Life Finanças</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="resources/css/cadastro.css">
  <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css">
  <link rel="stylesheet" href="resources/css/main.css" />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
            rel="stylesheet"
    />

</head>

<body>

<%@include file="header.jsp" %>

<div class="container mt-2 d-flex">


  <div class=" row w-50 mt-3     justify-content-center align-items-center  py-3">

    <div class="d-flex mt-3 text_color  row py-3 text-center"  >

      <h1 class="  mt-3  mb-5">Registre -se</h1>

      <h2 class="  py-3 mb-3">SUA VIDA FINANCEIRA </h2>
      <h2 >NA PALMA DA SUA MÃO!</h2>


      <div class="d-flex justify-content-center align-content-center">
        <img src="resources/img/emoji_home.png" alt="#">

      </div>




    </div>




  </div>



  <div class="row w-50  justify-content-center align-items-center   py-3">


  <form class="  mt-3  w-75 pt-2" action="usuario?acao=cadastrar" method="post" >


         <div class="form-group mt-5  ">
          <label class="mb-1 " for="id-nome">Nome completo</label>
          <input type="text" name="nome" id="id-nome" class="form-control p-2" placeholder="Digite seu nome ">
        </div>

        <div class="form-group  mt-2">
          <label class="mb-1" for="id-sobrenome">User name</label>
           <input type="text" name="sobrenome" id="id-sobrenome" class="form-control p-2" placeholder="Nome usuario">
        </div>

        <div class="form-group w-50  mt-2">
          <label class="mb-1" for="id-data-nasc">Data de Nascimento</label>
          <input type="date" name="data-nasc" id="id-data-nasc" class="form-control p-2" >
        </div>

        <div class="form-group mt-2 " >
          <label class="mb-1" for="id-email">Email*</label>
          <input  type="text" name="email" id="id-email" class="form-control p-2" placeholder="Email@email.com">

        </div>


        <div class="form-group mt-2 password-container">
          <label class="mb-1" for="id-senha">Digite sua Senha</label>
          <input type="password" name="senha" id="id-senha" class="form-control p-2" placeholder="Digite sua senha">
            <i id="togglePassword" class= " icon_senha_primary bi bi-eye-slash" ></i>
        </div>


        <div class="text-center mt-3 ">
           <input  type="submit" value="Cadastrar" class="btn btn_cadastro mt-3">
        </div>


      </form>



  </div>








 </div>


<footer class="card-footer mt-auto py-3 bg-light fixed-bottom text-center">
    <div class="container">
        <span class="text-muted">@Mylife Fincanças. Todos os direitos reservados. 2024</span>
    </div>
</footer>

<script src="./resources/js/bootstrap.bundle.js"></script>
<script src="./resources/js/cadastro-usuario.js"></script>
</body>
</html>



