<%-- 
    Document   : index
    Created on : 07/11/2021, 11:01:19
    Author     : 
--%>

<%@page import="model.ServicoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Servico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css">
        <link rel="stylesheet" href="estilo/body.css" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"/>

        <title>Lista de Serviços</title>
        <script type="text/javascript">
            function confirmaExclusao(id, servico) {
                if (confirm('Deseja realmente excluir o serviço ' + servico + ' do ID ' + id + '?')) {
                    location.href = 'gerenciar_servico.do?acao=deletar&idServico=' + id;
                }

            }
        </script>
    </head>

    <body class="div">

        <input type="checkbox" id="chec">

        <label for="chec">
            <div class="button"></div>
        </label>

        <nav>
            <%@include file="menu.jsp"%>
        </nav>
        <section>
            <div id="bar_top">
                <div id="titulo">
                    <p class="fs-1 bi bi-scissors"> Listar Serviços
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-scissors" viewBox="0 0 16 16">
                        <path d="M3.5 3.5c-.614-.884-.074-1.962.858-2.5L8 7.226 11.642 1c.932.538 1.472 1.616.858 2.5L8.81 8.61l1.556 
                              2.661a2.5 2.5 0 1 1-.794.637L8 9.73l-1.572 2.177a2.5 2.5 0 1 1-.794-.637L7.19 8.61 3.5 3.5zm2.5 10a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 
                              0zm7 0a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 0z">  
                        </path>
                        </svg>
                    </p>
                </div>
                <div id="nome">
                    <P id="nome" class="fs-2" text-align="right"> Bem Vindo, <c:if test="${ulogado!=null}">${ulogado.nome}</c:if></p>
                    </div>
                </div>

                <div id="tables">   
                    <a href="form_servico.jsp" class="btn btn-primary fs-4">Novo Cadastro</a>

                    <table class="table table-hover table-dark fs-4" id="listarServico">

                        <thead> 
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Valor</th>
                                <th>Opções</th>
                            </tr>
                        <thead>
                        <tfoot> 
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Valor</th>
                                <th>Opções</th>
                            </tr>
                        <tfoot> 
                        <tbody>   
                        <jsp:useBean class="model.ServicoDAO" id="sDAO"/>

                        <c:forEach var="s" items="${sDAO.lista}">
                            <tr>
                                <td>${s.idServico}</td>
                                <td>${s.nome}</td>
                                <td><fmt:formatNumber pattern="#,##0.00" value="${s.valor}"/></td>
                                <td>
                                    <a class="btn bg-primary" href="gerenciar_servico.do?acao=alterar&idServico=${s.idServico}">
                                        <i class="glyphicon glyphicon-pencil"></i>
                                    </a>
                                    <button class="btn btn-danger" onclick="confirmaExclusao(${s.idServico}, '${s.nome}')">
                                        <i class="glyphicon glyphicon-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <script type="text/javascript" src="datatables/jquery.js"></script>
                <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
                <script type="text/javascript">
                            $(document).ready(function () {
                                $("#listarServico").dataTable({
                                    "bJQueryUI": true,
                                    "oLanguage": {
                                        "sProcessing": "Procesando...",
                                        "sLengthMenu": "Mostrar _MENU_ registro",
                                        "sZeroRecords": "Não foram encontrados resultados",
                                        "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
                                        "sInforEmpty": "Mostrando de 0 até 0 de 0 registros",
                                        "sInfoFiltered": "",
                                        "sInfoPostFix": "",
                                        "sSearch": "Pesquisar",
                                        "sUrl": "",
                                        "oPaginate": {
                                            "sFirst": "Primeiro",
                                            "sPrevious": "Anterior",
                                            "sNext": "Próximo",
                                            "sLast": "Último"
                                        }
                                    }
                                })
                            })
                </script>    
            </div>
    </body>
</html>