<%-- 
    Document   : index
    Created on : 07/11/2021, 11:01:19
    Author     : 
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Servico"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="estilo/body.css" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"/>

        <title>Cadastrar Agendamento</title>
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
                    <p class="fs-1 bi bi-scissors"> Cadastrar Agendamento
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
                    <form action="gerenciar_agendamento.do" method="POST" >
                        <input type="hidden" name="idAgendamento" value="${agendamento.idAgendamento}"/>
                    
                    

                <div class="row">
                        <div class="form-group col-sm-6">
                            <label for="cliente" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Cliente</label>
                            </br></br></br></br>
                            <select name="idCliente" style="border-radius: 30px;" required="" class="form-control fs-5">
                                <option disabled="" selected="" value="">Selecione o Cliente</option> 
                                <jsp:useBean class="model.ClienteDAO" id="clienteDAO"/>
                                <c:forEach var="cliente" items="${clienteDAO.lista}">
                                    <option value="${cliente.idCliente}"
                                            <c:if test="${cliente.idCliente==agendamento.cliente.idCliente}">
                                                selected=""
                                            </c:if>      
                                            >
                                        ${cliente.nome}
                                    </option>
                                </c:forEach>
                            </select>    
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="usuario" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Barbeiro</label>
                            </br></br></br></br>
                            <select name="idUsuario" style="border-radius: 30px;" required="" class="form-control fs-5">
                                <option disabled="" selected="" value="">Selecione o Barbeiro</option> 
                                <jsp:useBean class="model.UsuarioDAO" id="usuarioDAO"/>
                                <c:forEach var="usuario" items="${usuarioDAO.lista}">
                                    <option value="${usuario.idUsuario}"
                                            <c:if test="${usuario.idUsuario==agendamento.vendedor.idUsuario}">
                                                 selected=""
                                            </c:if>      
                                            >
                                        ${usuario.nome}
                                    </option>
                                </c:forEach>
                            </select>    
                        </div>
                    </div>

                   
                                
                    <div class="row">
                        <div class="form-group col-sm-6">
                            <label for="servico" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Serviço</label>
                            </br></br></br></br>
                            <select name="idServico" style="border-radius: 30px;" required="" class="form-control fs-5">
                                <option disabled="" selected="" value="">Selecione o Serviço</option> 
                                <jsp:useBean class="model.ServicoDAO" id="servicoDAO"/>
                                <c:forEach var="servico" items="${servicoDAO.lista}">
                                    <option value="${servico.idServico}"
                                            <c:if test="${servico.idServico==agendamento.servico.idServico}">
                                                 selected=""
                                            </c:if>      
                                            >
                                        ${servico.nome}
                                    </option>
                                </c:forEach>
                            </select>    
                            </div>
                                
                                <c:if test="${agendamento.idAgendamento>0}">
                                    <div class="form-group col-sm-6">
                                        <label for="status" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Status</label>
                                        </br></br></br></br>
                                        <select name="idStatus" style="border-radius: 30px;" required="" class="form-control fs-5">
                                            <option disabled="" selected="" value="">Selecione o Status</option> 
                                            <jsp:useBean class="model.StatusDAO" id="statusDAO"/>
                                            <c:forEach var="status" items="${statusDAO.lista}">
                                                <option value="${status.idStatus}"
                                                        <c:if test="${status.idStatus==agendamento.status.idStatus}">
                                                            selected=""
                                                        </c:if>      
                                                        >
                                                    ${status.descricao}
                                                </option>
                                            </c:forEach>
                                        </select>    
                                    </div>
                                </c:if>        
                    </div>  
                    
                    


                    <div class="form-group col-sm-6 "> 
                        <label for="data" style="border-radius: 30px; background-color:#251631; width: 250px; text-align: center; left: 0px;" class="control-label text-light fs-4">Data Agendamento</label>
                        </br></br></br></br>
                        <input type="date" style="border-radius: 15px; padding:3px;" class="fs-4 text-dark" id="data" name="data" value="${agendamento.dataHora}"/>
                    </div>
                    
                    <br/>
                    
                    <div class="form-group col-sm-6"> 
                        <label for="hora" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center; left: 0px; padding: " class="control-label text-light fs-4">Hora</label>
                        </br></br></br></br>
                        <input type="time" style="border-radius: 15px; padding:3px;" class="fs-4 text-dark" id="hora" name="hora" min="07:00" max="18:00" required value="${agendamento.horaAgendamento}" >
                    </div>      


                    <div>
                        <button class="btn btn-success fs-3">Gravar</button>
                        <a href="listar_agendamento.jsp" class="btn btn-warning fs-3">Voltar</a>
                    </div>
                </form>
            </div>  
        </section>
    </body>
</html>