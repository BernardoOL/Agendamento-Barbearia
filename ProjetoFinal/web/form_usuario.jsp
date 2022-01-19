<%-- 
    Document   : index
    Created on : 22/08/2019, 21:13:35
    Author     : mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1,
              user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="estilo/body.css" type="text/css"/>
        <link rel="stylesheet" href="estilo/estilo.css" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"/>
        <title>Cadastrar Usuário</title>
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
                    <p class="fs-1 bi bi-scissors"> Cadastrar Usuário
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
                    <form action="gerenciar_usuario.do" method="POST">
                        <input type="hidden" name="idUsuario" id="idUsuario" value="${usuario.idUsuario}"/>
                    
                    <div class="row">
                        <div class="form-group col-sm-6">
                            <label for="nome" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="textos">Nome</label>
                            </br>
                            <input type="text" style="border-radius: 30px" class="form-control fs-4" 
                                   id="nome" name="nome" required="" 
                                   maxlength="45" value="${usuario.nome}"/>
                        </div>  

                        <div class="form-group col-sm-6">
                            <label for="login" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="textos">Login</label>
                            </br>
                            <input type="text" style="border-radius: 30px" class="form-control fs-4" 
                                   id="login" name="login" required="" 
                                   maxlength="45" value="${usuario.login}"/>
                        </div>
                    </div>
                        </br>
                    <div class="row">
                        <div class="form-group col-sm-6">
                            <label for="senha" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="textos">Senha</label>
                            </br>
                            <input type="password" style="border-radius: 30px" class="form-control fs-4" 
                                   id="senha" name="senha" required=""
                                   maxlength="45" value="${usuario.senha}"/>
                        </div>  
                        
                        <div class="form-group col-sm-6">
                            <label for="status" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="textos">Status</label>
                            </br>
                            <select name="status" style="border-radius: 30px" required="" class="form-control fs-4">
                                <c:if test="${usuario.status==null}">
                                    <option value="">Selecione uma opção</option>
                                    <option value="1">Ativo</option>
                                    <option value="2">Inativo</option>
                                </c:if> 
                                <c:if test="${usuario.status==1}">
                                    <option value="1" selected="">Ativo</option>
                                    <option value="2">Inativo</option>
                                </c:if> 
                                <c:if test="${usuario.status==2}">
                                    <option value="1">Ativo</option>
                                    <option value="2" selected="">Inativo</option>
                                </c:if>     
                            </select>    
                        </div>    
                    </div>
                        </br>
                    <div class="row">
                        <div class="form-group col-sm-4 ">
                            <label for="perfil" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="textos">Perfil</label>
                            </br>
                            <select name="idPerfil" style="border-radius: 30px" required="" class="form-control fs-4">
                                <option value="">Selecione um Perfil</option> 
                                <jsp:useBean class="model.PerfilDAO" id="pDAO"/>
                                <c:forEach var="p" items="${pDAO.lista}">
                                    <option value="${p.idPerfil}"
                                            <c:if test="${p.idPerfil==usuario.perfil.idPerfil}">
                                                selected=""
                                            </c:if>
                                            >
                                        ${p.nome}
                                    </option>
                                </c:forEach>
                            </select>    
                        </div>    
                    </div>
                    <div>
                        <button class="btn btn-success fs-4">Gravar</button>
                        <a href="listar_usuario.jsp" class="btn btn-warning fs-4">
                            Voltar
                        </a>    
                    </div>    
                </form>    
            </div>    
        </section>
    </body>
</html>
