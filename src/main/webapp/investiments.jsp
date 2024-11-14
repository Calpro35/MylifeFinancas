<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title> Investimentos |My Life Finanças </title>
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
            <!-- Movimentações -->
            <h4 class="text-secondary mt-4 pt-3 mb-5">Investimentos</h4>

 <p class="fs-6 container-fluid bg-light me-3 mt-3 ">Lista de todos os seus Investimentos.</p>
            <table class="table table-bordered border-opacity-20 ">
                <thead class="">
                <tr >
                    <th class="fw-medium fs-6">Data</th>
                    <th class="fw-medium fs-6">Nome</th>
                    <th class="fw-medium fs-6">Valor</th>
                    <th class="fw-medium text-center fs-6">Editar</th>
                    <th class="fw-medium text-center fs-6">Excluir</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${investimentos}" var="investimento">
                <tr class="table-row">
                    <td>
                        <fmt:parseDate
                                value="${investimento.dt_servico}"
                                pattern="yyyy-MM-dd"
                                var="dataServicoFmt"/>
                        <fmt:formatDate
                                value="${dataServicoFmt}"
                                pattern="dd/MM/yyyy"/>
                    </td>
                    <td>${investimento.nm_servico}</td>
                    <td>R$ <fmt:formatNumber
                            value="${investimento.vl_servico}"/></td>

                    <td class="edit-btn text-center">
                        <c:url value="servico" var="linkEditar">
                            <c:param name="acao" value="abrir-form-edicao"/>
                            <c:param name="cdUsuario" value="${cdUsuario}"/>
                            <c:param name="cdServico" value="${investimento.cd_servico}"/>
                        </c:url>
                        <a href=${linkEditar} class="text-decoration-none">
                            <i class="bi bi-pencil"></i>
                        </a>


                    </td>


                    <td class="text-danger text-center">
                        <button type="button"
                                class="btn text-danger btn-close-white"
                                data-bs-toggle="modal"
                                data-bs-target="#excluirModal"
                                onclick="codigoExcluir.value = ${investimento.cd_servico}"
                        >

                            <i class="bi bi-trash"></i>
                        </button>
                    </td>
                 </c:forEach>



            <!--Criar a logica para editar excluir modal abaixo-->

                </tr>

                </tbody>




            </table>
            <div class="d-flex gap-2 mt-5">
                <p>Total: R$</p>
                <span class="text-success">${totalInvestimentos}</span>
            </div>
        </div>

    </div>
</div>





<%-- modal--%>
<div class="modal" tabindex="-1" id="excluirModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title">Confirmar Exclusão</h6>
                <button type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close">

                </button>
            </div>
            <div class="modal-body">
                <h5>Tem certeza que deseja excluir o Investimento?</h5>
                <p><strong>Atencao!</strong> Esta ação é irreversível.</p>
            </div>

            <div class="modal-footer">
                <form action="servico" method="post">

                    <input
                            type="hidden"
                            name="acao"
                            value="excluir">

                    <input
                            type="hidden"
                            name="codigoExcluir"
                            id="codigoExcluir">
                    <input
                            type="hidden"
                            name="cdUsuario"
                            id="cdUser"
                            value="${cdUsuario}"
                    >

                    <button
                            type="submit"
                            class="btn btn-danger">
                        Sim
                    </button>
                    <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal" >
                        Não
                    </button>




                </form>
            </div>
        </div>
    </div>
</div>






<script src="./resources/js/bootstrap.bundle.js"></script>
</body>
</html>