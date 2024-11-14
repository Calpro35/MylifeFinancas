<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Life Finanças</title>
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

<%@include file="headerApp.jsp"  %>
<!-- Dashboard -->
<div class="container dashboard-container d-flex border pt-5 mt-2 w-75">
    <div class="row w-100">

        <!-- Aside -->
        <%@include  file="aside.jsp" %>
        <!-- Conteúdo principal -->

        <div class="dashboard-content col-9">
            <!-- Title -->

            <h4 class="text-secondary pt-3 mt-3">Resumo</h4>

            <!-- Balance antigo saldo
            <div class="d-flex justify-content-between py-4">
                <div class="d-flex gap-2">
                    <p class="color_saldo fw-bold" >Saldo</p>
                    <span class=" color_saldo fw-bold ">R$ 84.000,00</span>
                </div>
                <div class="d-flex gap-2 ">
                    Ocultar valores <i class="bi bi-eye-slash fw-bold"></i>
                </div>
            </div>
-->
          <!-- Aqui tem a funcao para esconder todos os valores -->
            <div class="container mt-4">
                <div class="d-flex justify-content-between py-4">
                    <div class="d-flex gap-2">
                        <p class="color_saldo fw-bold">Saldo</p>
                        <span  class="saldo color_saldo fw-bold">R$ <fmt:formatNumber
                                value="${saldo}"/></span>
                    </div>
                    <div class="d-flex justify-content-end py-4">
                        <span id="toggleText">Ocultar valores</span>
                        <i class="bi-eye fw-bold ms-2" id="toggleIcon" style="cursor: pointer;" onclick="toggleVisibility()"></i>
                    </div>
                </div>
            </div>

            <!-- Movimentações -->
            <h4 class="mt-3 text-secondary color_text">Movimentações</h4>

            <div class="d-flex flex-column pt-2">


                <div class="back_fundo d-flex justify-content-between p-3 align-items-center">
                    <p class="fw-medium">Total de Recebimentos</p>

                    <div class="d-flex ms-2 me-3">
                    <p class=" ms-2 me-3 fw-bold">valor(R$)</p>
                    <span class="saldo ms-2 me-3 d-flex text-success">
                                <i class= " ms-2 me-3 bi bi-plus-lg"></i>
                                ${totalRecebimentos}
                    </span>
                    </div>

                </div>

                <div class="back_fundo d-flex justify-content-between p-3 align-items-center">
                    <p class="fw-medium">Total de Despesas</p>

                    <div class="d-flex ms-2 me-3">
                        <p class=" ms-2 me-3 fw-bold" >valor(R$)</p>
                        <span class="saldo ms-2 me-3 d-flex text-success
">
                                <i class= " ms-2 me-3 bi bi-plus-lg"></i>
                                ${totalDespesas}
                    </span>
                    </div>

                </div>

                <div class="back_fundo d-flex justify-content-between p-3 align-items-center">
                    <p class="fw-medium">Total de Investimentos</p>

                    <div class="d-flex ms-2 me-3 " >
                        <p class=" ms-2 me-3 fw-bold">valor(R$)</p>
                        <span class="saldo ms-2 me-3 d-flex text-success">
                                <i class= " ms-2 me-3 bi bi-plus-lg"></i>
                                ${totalInvestimentos}
                    </span>
                    </div>

                </div>


 <!-- Recebimentos -->
  <h4 class="text-secondary mt-5">Recebimentos</h4>
            <div class="container mt-2">
                <div class="table_container">
                    <table class="table table-striped tab">
                        <thead class="thead-dark text-center">
                        <tr>
                            <th class="text_center">Data</th>
                            <th>Descrição</th>
                            <th>Valor</th>
                        </tr>
                        </thead>
                        <tbody class="border-bottom text-center">
                        <c:forEach items="${recebimentos}" var="recebimento">
                            <tr>
                                <td class="text-start">
                                    <fmt:parseDate
                                            value="${recebimento.dt_servico}"
                                            pattern="yyyy-MM-dd"
                                            var="dataServicoFmt"/>
                                    <fmt:formatDate
                                            value="${dataServicoFmt}"
                                            pattern="dd/MM/yyyy"/>
                                </td>
                                <td>${recebimento.nm_servico}</td>
                                <td>R$
                                    <fmt:formatNumber
                                            value="${recebimento.vl_servico}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>


   <!-- Investimentos -->
  <h4 class="text-secondary pt-4 mt-5 mb-3">Investimentos</h4>
      <div class="container_invest mt-2">
        <div class="table_invest">
            <div class="d-flex flex-column mt-2">
                <div class="border d-flex justify-content-between p-2 align-items-center"  >
                    <c:forEach items="${investimentos}" var="investimento">
                    <p>${investimento.nm_servico}</p>
                    <div class="d-flex ms-2 me-3">
                        <p class=" ms-2 me-3 fw-bold">valor(R$)</p>
                        <span class=" ms-2 me-3 d-flex text-success">
                                <i class= " ms-2 me-3 bi bi-plus-lg"></i>
                                <fmt:formatNumber
                                        value="${investimento.vl_servico}"/>
                        </span>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
      </div>


  <!-- Despesas -->

  <h4 class="text-secondary pt-3 mt-5 bg-light w-100 padding">Despesas</h4>


      <div class="container mt-3">
                <div class="table_container">
                    <table class="table table-bordered tab border-light">
                        <thead class="thead-dark text-center">
                        <tr>
                            <th class="text_center">Data</th>
                            <th>Descrição</th>
                            <th>Valor</th>
                        </tr>
                        </thead>
                        <tbody class="border-bottom text-center  ">
                        <c:forEach items="${despesas}" var="despesa">
                            <tr>
                                <td class="text-start">
                                    <fmt:parseDate
                                            value="${despesa.dt_servico}"
                                            pattern="yyyy-MM-dd"
                                            var="dataServicoFmt"/>
                                    <fmt:formatDate
                                            value="${dataServicoFmt}"
                                            pattern="dd/MM/yyyy"/>
                                </td>
                                <td>${despesa.nm_servico}</td>
                                <td>R$
                                    <fmt:formatNumber
                                            value="${despesa.vl_servico}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

  </div>
    </div>
</div>
   </div>
</div>


<%@include file="footerApp.jsp" %>
<script src="./resources/js/bootstrap.bundle.js"></script>
<script src="./resources/js/main.js"></script>


</body>

</html>

































