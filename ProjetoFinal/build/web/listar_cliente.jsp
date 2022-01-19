<%-- 
    Document   : index
    Created on : 07/11/2021, 11:01:19
    Author     : 
--%>

<%@page import="model.ClienteDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="estilo/body.css" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"/>

        <title>Lista de Clientes</title>
        <script type="text/javascript">
            function confirmaExclusao(id, nome) {
                if (confirm('Deseja realmente excluir o Cliente ' + nome + '?')) {
                    location.href = 'gerenciar_cliente.do?acao=deletar&idCliente=' + id;
                }

            }
        </script>
    </head>

    <body class="div" >

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
                    <p class="fs-1 bi bi-scissors"> Listar Clientes
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
                    <div class="table-responsive">
                    <a href="form_cliente.jsp" class="btn btn-primary fs-4">Novo Cadastro</a>
                    </br>
                    </br>
                    <table class="table table-hover table-dark fs-4" id="listarCliente">

                        <thead> 
                            <tr>
                                <th>ID</th>
                                <th>Nome Cliente</th>
                                <th>Telefone</th>
                                <th>CPF</th>
                                <th>Rua</th>
                                <th>Quadra</th>
                                <th>Número</th>
                                <th>CEP</th>
                                <th>Opções</th>
                            </tr>
                        <thead>
                        <tfoot> 
                            <tr>
                                <th>ID</th>
                                <th>Nome Cliente</th>
                                <th>Telefone</th>
                                <th>CPF</th>
                                <th>Rua</th>
                                <th>Quadra</th>
                                <th>Número</th>
                                <th>CEP</th>
                                <th>Opções</th>
                            </tr>
                        <tfoot> 
                        <jsp:useBean class="model.ClienteDAO" id="cDAO"/>    
                    <tbody>   
                        <c:forEach var="c" items="${cDAO.lista}">
                            <tr>
                                <td>${c.idCliente}</td>
                                <td>${c.nome}</td>
                                <td>${c.telefone}</td>
                                <td>${c.cpf}</td>
                                <td>${c.rua}</td>
                                <td>${c.quadra}</td>
                                <td>${c.numero}</td>
                                <td>${c.cep}</td>
                                <td>
                                    <a class="btn btn-primary" href="gerenciar_cliente.do?acao=alterar&idCliente=${c.idCliente}">
                                        <i class="glyphicon glyphicon-pencil" ></i>
                                    </a>

                                    <button class="btn btn-danger" onclick="confirmaExclusao(${c.idCliente}, '${c.nome}')">
                                        <i class="glyphicon glyphicon-trash"></i>
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <script type="text/javascript" src="datatables/jquery.js"></script>
                <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
                <script id="script" type="text/javascript">
                                        $(document).ready(function () {
                                            $("#listarCliente").dataTable({
                                                "bJQueryUI": true,
                                                "oLanguage": {
                                                    "sProcessing": "Procesando...",
                                                    "sLengthMenu": "Mostrar _MENU_ registro",
                                                    "sZeroRecords": "Não foram encontrados resultados",
                                                    "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
                                                    "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
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