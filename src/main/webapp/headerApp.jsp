<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<link rel="stylesheet" href="resources/css/bootstrap/header.css">
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/js/main.js">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark color p-0  fixed-top "  >
  <div class="container">
    <a class="navbar-brand" href="#">
      <img class="Header_logo" src="resources/img/logo_Header.png">
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

      <ul class="navbar-nav  ms-auto">


        <li class=" align-content-center">
          <p class=" display_contents text-white px-3 ">Olá ${nome}, Bem-Vindo(a)! </p>
        <li>


     <li class="align-content-center px-3">
        <a href="#" class=" notification-icon" id="notificationBell">
         <span class=" notification-badge" id="notificationBadge"></span>
          <i class="bi bi-bell"></i>
        </a>
     </li>

        <li>
          <a class=" nav-link text-white"  href="home.jsp">| Sair </a>
        <li>

       </ul>

     </div>

  </div>
</nav>





<!--
<!DOCTYPE html>
<html lang="pt">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Notificações com Ícone de Sino</title>

 Bootstrap Icons
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <style>
    /* Container do ícone */





    .notification-icon {
      position: relative;
      display: inline-block;
      cursor: pointer;
      font-size: 24px;
    }

    /* Bolinha de notificação */
    .notification-badge {
      position: absolute;
      top: 0;
      right: 0;
      width: 12px;
      height: 12px;
      background-color: red;
      color: white;
      border-radius: 50%;
      display: none;
      font-size: 10px;
      text-align: center;
    }

    /* Exibe a notificação */
    .notification-icon.has-notification .notification-badge {
      display: inline-block;
    }
  </style>
</head>
<body>


<script>
  // Função para simular o recebimento de uma nova notificação
  function addNotification() {
    const bellIcon = document.getElementById('notificationBell');
    const badge = document.getElementById('notificationBadge');

    // Adiciona a classe que exibe a notificação
    bellIcon.classList.add('has-notification');
  }

  // Simulação: Receber uma notificação após 3 segundos
  setTimeout(addNotification, 3000);
</script>
</body>
</html>





-->







