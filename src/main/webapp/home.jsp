<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html>
<head>
  <title>My Life Finanças</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css">
  <link rel="stylesheet" href="resources/css/home.css">
  <link rel="stylesheet" href="resources/css/main.css" />

</head>
<body>

<!-- header -->
<nav class="navbar navbar-expand-lg navbar-dark color" >
  <div class="container-fluid">
    <a class="navbar-brand" href="home.jsp">
      <img class="Header_logo" src="resources/img/logo_Header.png" alt="#">
    </a>
    <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link active font_size" href="cadastro-usuario.jsp">Cadastrar - se</a>
        </li>
        <li class="nav-item">
          <a class="nav-link font_size" href="login.jsp">Entrar</a>
        </li>

      </ul>
    </div>
  </div>
</nav>


  <div class="container  mt-2 ">

    <div class="text-center   mt-5 ">

      <div class="container text-white">
        <h1 class="display-6 ">
          Bem-vindo ao <span class="text_display"> MyLifeFinanças</span>
        </h1>
       </div>

    <!-- Sobre a Aplicação -->

        <div class=" d-flex row justify-content-center align-items-center ">
        <img class="logo_login" src="resources/img/logo_Login.png" alt="#">
          <p class="text_ display-5 text-center">SUA VIDA FINANCEIRA NA PALMA DA SUA MÃO!</p>

        </div>
    </div>
  </div>
<script src="./resources/js/bootstrap.bundle.js"></script>

  </body>
  </html>
