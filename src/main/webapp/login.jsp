<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>

<html>

<head>
  <title>My Life Finanças</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="resources/css/login.css">
    <link rel="stylesheet" href="resources/css/main.css" />
  <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css">
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
          rel="stylesheet"
  />

</head>

<body>

<%@include file="header.jsp" %>

<div class="container mt-5 d-flex justify-content-center ">


<div class="container row mt-5 justify-content-center" >


   <h1 class="text_ display-6 text-center mt-5">SUA VIDA FINANCEIRA !</h1>

    <form class="mt-5" action="login" method="post" >


      <div class="d-flex justify-content-center  column-gap-4 mb-3" >

        <div class="form-group mt-2 w-25 " >
        <label class="mb-1" for="id-email">Email*</label>
        <input type="text" name="email" id="id-email" class="form-control p-2" placeholder="Email@email.com">
        </div>

       <div class="password-container form-group mt-2 w-25">
        <label class="mb-1" for="id-senha">Digite sua Senha</label>
        <input type="password" name="senha" id="id-senha" class="form-control p-2" placeholder="Digite sua senha">
         <i class="bi-eye-slash" id="togglePass"></i>
      </div>

      </div>

      <div class="text-center mt-5 ">
        <input  type="submit" value="acessar" class="btn btn_login mt-3">
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
<script src="./resources/js/login.js"></script>

</body>
</html>
