<%-- 
    Document   : index
    Created on : 22/08/2019, 21:13:35
    Author     : Administrador
--%>

<%@page import="model.MenuDAO"%>
<%@page import="model.Menu"%>
<%@page import="model.PerfilDAO"%>
<%@page import="model.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <title>Gerenciar Perfil</title>
        <script type="text/javascript">
            function confirmarExclusao(idMenu, nome, idPerfil) {
                if (confirm('Deseja realmente desvincular o menu ' + nome + 'do perfil ' + idPerfil + ' ?')) {
                    location.href = 'gerenciar_menu_perfil.do?acao=desvincular&idMenu=' + idMenu + '&idPerfil=' + idPerfil;
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
                    <p class="fs-1 bi bi-scissors"> Lista de Menus Vinculados
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

                    <div class="row">
                        <form action="gerenciar_menu_perfil.do" method="POST">
                            <input type="hidden" name="idPerfil" value="${perfilv.idPerfil}"/>
                        <div class="form-group col-sm-6">
                            <label for="menu" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Menus</label>
                            </br></br></br></br>
                            <select name="idMenu" style="border-radius: 30px;" required="" id="idMenu" class="form-control fs-4">
                                <option value="">Selecione o menu</option>
                                <c:forEach var="m" items="${perfilv.naoMenus}">
                                    <option value="${m.idMenu}">${m.nome}</option>
                                </c:forEach>
                            </select>
                        </div>    
                </div>    

                <div>
                    <button class="btn btn-success fs-4">Vincular</button>
                    <a href="listar_perfil.jsp" class="btn btn-warning fs-4">
                        Voltar
                    </a>    
                </div>    
                </form>    
                </br>


                <table class="table table-hover table-dark fs-4" id="listaMenu">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Menu</th>
                            <th>Link</th>
                            <th>Exibir</th>
                            <th>Desvincular</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>Menu</th>
                            <th>Link</th>
                            <th>Exibir</th>
                            <th>Desvinvular</th>
                        </tr>
                    </tfoot>


                    <tbody>
                        <c:forEach var="m" items="${perfilv.menus}">

                            <tr>
                                <td>${m.idMenu}</td>
                                <td>${m.nome}</td>
                                <td>${m.link}</td>
                                <td>
                                    <c:if test="${m.exibir==1}">Sim</c:if>
                                    <c:if test="${m.exibir==2}">Não</c:if>
                                    </td>
                                    <td>

                                        <button class="btn btn-danger" onclick="confirmarExclusao(${m.idMenu}, '${m.nome}',${perfilv.idPerfil})">
                                        <i class="glyphicon glyphicon-trash"></i>
                                    </button>
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>    
            </div>    
            <script type="text/javascript" src="datatables/jquery.js"></script>
            <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
            <script type="text/javascript">
                                            $(document).ready(function () {
                                                $("#formMenuPerfil").dataTable({
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
    </section>
</body>
</html>