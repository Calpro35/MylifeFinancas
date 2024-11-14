<!-- Aside -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/aside.css">


<div class="aside col-3 bg-light pt-5 mt-2 ">

    <div class="pt-2 mt-2  text-center">
    <h1 class="fs-5 font_aside">Menu</h1>
    </div>


    <c:url value="servico" var="linkDashboard">
        <c:param name="acao" value="abrir-dashboard"/>
        <c:param name="cdUsuario" value="${cdUsuario}"/>
    </c:url>
    <a href=${linkDashboard} class="text-decoration-none">
        <div class="aside-button pt-5 mt2">
            <div class="align d-flex gap-4">
                <i class="ico_aside bi bi-house-door-fill"></i>
                <p class="nav-link font_aside">Home</p>
            </div>
        </div>
    </a>



    <c:url value="usuario" var="linkDashboard">
        <c:param name="acao" value="abrir-perfil"/>

        <c:param name="cdUsuario" value="${cdUsuario}"/>
    </c:url>
    <a href=${linkDashboard} class="text-decoration-none">
        <div class="aside-button pt-3 ">
            <div class="align d-flex gap-4">
                <i class="ico_aside bi bi-person-circle"></i>
                <p class="nav-link font_aside">Perfil</p>
            </div>
        </div>
    </a>


    <c:url value="servico" var="linkCadastrarServico">
        <c:param name="acao" value="abrir-form-cadastro"/>

        <c:param name="cdUsuario" value="${cdUsuario}"/>
    </c:url>
    <a href=${linkCadastrarServico} class="text-decoration-none">
        <div class="aside-button pt-4">
            <div class="align d-flex gap-4">
                <i class="ico_aside bi bi-arrow-clockwise"></i>
                <p class="nav-link font_aside">Contas</p>
            </div>
        </div>
    </a>

    <c:url value="servico" var="linkInvestimentos">
        <c:param name="acao" value="listarInvestimentos"/>
        <c:param name="cdUsuario" value="${cdUsuario}"/>
    </c:url>
    <a href=${linkInvestimentos} class="text-decoration-none">
        <div class="aside-button pt-4">
            <div class="align d-flex gap-4">
                <i class="ico_aside bi bi-graph-up"></i>
                <p class="nav-link font_aside">Investimentos</p>
            </div>
        </div>
    </a>


    <c:url value="servico" var="linkRecebimentos">
        <c:param name="acao" value="listarRecebimentos"/>
        <c:param name="cdUsuario" value="${cdUsuario}"/>
    </c:url>
    <a href=${linkRecebimentos} class="text-decoration-none">
        <div class="aside-button pt-3">
            <div class="align d-flex gap-4">
                <i class="ico_aside bi bi-cash-stack" ></i>
                <p class="nav-link font_aside">Recebimentos</p>
            </div>
        </div>
    </a>

    <c:url value="servico" var="linkDespesas">
        <c:param name="acao" value="listarDespesas"/>
        <c:param name="cdUsuario" value="${cdUsuario}"/>
    </c:url>
    <a href=${linkDespesas} class="text-decoration-none">
        <div class="aside-button pt-3">
            <div class="align d-flex gap-4">
                <i class="ico_aside bi bi-arrow-down-circle" ></i>
                <p class="nav-link font_aside">Despesas</p>
            </div>
        </div>
    </a>


    </div>