<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title> Contas |My Life Finanças </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="resources/css/dashboard.css">
     <link rel="stylesheet" href="resources/css/main.css" />
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


                <h1 class="fs-4 mt-5 ms-5 leter">Cadastrar contas </h1>


                <div class="text_center row container-fluid  mt-3 d-flex align-content-center justify-content-center" >

                    <form class=" mt-3 w-75" action="servico?acao=cadastrar" method="post" >

                     <input type="text" name="cdUsuario" value="${cdUsuario}" class="d-none">
                     <div class="d-flex  mt-4 justify-content-center me-5" >

                        <div class="form-group d-flex pt-3 mb-4 container-fluid gap-2">
                            <label class="fw-medium  align-content-center "
                                    for="id-categoria">Tipo de conta
                            </label>
                            <select
                                    name="tipoServico"
                                    id="id-categoria"
                                    class="form-control w-25"
                                    onchange="toggleSaidaFields()"
                            >
                                <option class=" " value="0" >Selecione</option>
                                <c:forEach items="${tiposServicos}" var="c">
                                    <option  class="" class="ms-2 me-2" value="${c.cd_tipo_servico}" >${c.tipo_servico}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>




                  <div class="d-flex mb-3 mt-4 justify-content-center gap-4">
                        <div class="form-group w-100">
                            <label for="id-nome">Nome</label>
                            <input type="text" name="nomeServico" id="id-nome" class="form-control" required placeholder="Informe o nome">
                        </div>

                        <div class="form-group w-100">
                            <label for="id-descricao">Descrição</label>
                            <input type="text" name="descricao" id="id-descricao" class="form-control" placeholder="Descrição">
                        </div>
                    </div>

                        <div class="d-flex mb-3 mt-4 justify-content-center gap-4">
                            <div class="form-group w-100">
                                <label for="id-valorE">Valor</label>
                                <input type="number" name="valorEntrada" id="id-valorE" class="form-control" placeholder="R$ Valor">
                            </div>

                            <div class="form-group w-100">
                                <label for="id-dataE">Data</label>
                                <input type="date" name="dataEntrada" id="id-dataE" class="form-control">
                            </div>
                        </div>

                        <div id="saidaFields" class="d-flex mb-3 mt-4 justify-content-center gap-4 d-none">
                            <div class="form-group w-100">
                                <label for="id-valorS">Valor de Saída</label>
                                <input type="number" name="valorSaida" id="id-valorS" class="form-control" placeholder="R$ Valor">
                            </div>

                            <div class="form-group w-100">
                                <label for="id-dataS">Data de Saída</label>
                                <input type="date" name="dataSaida" id="id-dataS" class="form-control">
                            </div>
                        </div>

                        <div class="text-center mt-5 gap-2 mb-3 d-flex justify-content-center">
                            <input  type="submit" value="Cadastrar" class="btn btn-primary  mt-3 w-25">

                        </div>


                    </form>


                </div>



            </div>





        </div>
    </div>
</div>
<script src="./resources/js/bootstrap.bundle.js"></script>
<script>
    function toggleSaidaFields() {
        const tipoServico = document.getElementById("id-categoria").value;
        const saidaFields = document.getElementById("saidaFields");
        const campoValorS = document.getElementById("id-valorS");
        const campoDataS = document.getElementById("id-dataS");

        console.log(tipoServico);
        // Exibe os campos se a opção selecionada for "3", oculta caso contrário
        if (tipoServico === "3") {
            saidaFields.classList.remove("d-none");
            saidaFields.classList.add("flex");
        } else {
            saidaFields.classList.remove("flex");
            saidaFields.classList.add("d-none");
        }
        campoValorS.value = "";
        campoDataS.value = "";
    }
</script>
</body>
</html>





