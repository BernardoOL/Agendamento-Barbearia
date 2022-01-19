<%-- 
    Document   : index
    Created on : 07/11/2021, 11:01:19
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" href="estilo/estilo.css" type="text/css"/>

        <title>Cadastrar Cliente</title>
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
                    <p class="fs-1 bi bi-scissors"> Cadastrar Cliente
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
                <br/>
                <div id="tables">
                    
                    <form action="gerenciar_cliente.do" method="POST" >
                        <input type="hidden" name="idCliente" value="${cliente.idCliente}" />
                    <div class="row">
                        <div class="form-group col-sm-6"> 
                            <label for="nomec" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Nome Cliente</label>
                            </br></br></br></br>
                            <input type="text" style="border-radius: 30px" class="form-control fs-4" id="nomec" name="nome" required="" value="${cliente.nome}" maxlength="45"/>
                        </div>
                        <div class="form-group col-sm-6"> 
                            <label for="cpf" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">CPF</label>
                            </br></br></br></br>
                            <input type="text" style="border-radius: 30px;" class="form-control fs-4" id="cpf" name="cpf" required="" value="${cliente.cpf}" maxlength="14"/>
                        </div>
                        <div class="form-group col-sm-6"> 
                            <label for="telefone" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Telefone</label>
                            </br></br></br></br>
                            <input type="text"  style="border-radius: 30px;" class="form-control fs-4" id="telefone" name="telefone" required="" value="${cliente.telefone}" maxlength="14"/>
                        </div>
                        <div class="form-group col-sm-6"> 
                            <label for="rua" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Rua</label>
                            </br></br></br></br>
                            <input type="text" style="border-radius: 30px" class="form-control fs-4" id="rua" name="rua" value="${cliente.rua}" maxlength="45"/>
                        </div>
                        <div class="form-group col-sm-6"> 
                            <label for="quadra" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Quadra</label>
                           </br></br></br></br>
                            <input type="text" style="border-radius: 30px" class="form-control fs-4" id="quadra" name="quadra" value="${cliente.quadra}" maxlength="45"/>
                        </div>
                        <div class="form-group col-sm-6"> 
                            <label for="numero" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">Numero</label>
                            </br></br></br></br>
                            <input type="text" style="border-radius: 30px" class="form-control fs-4" id="numero" name="numero" value="${cliente.numero}" maxlength="11" >
                        </div>
                        <div class="form-group col-sm-6"> 
                            <label for="cep" style="border-radius: 30px; background-color:#251631; width: 130px; text-align: center;" class="control-label text-light fs-4">CEP</label>
                            </br></br></br></br>
                            <input type="text" style="border-radius: 30px;" class="form-control fs-4" id="cep" name="cep" value="${cliente.cep}" maxlength="11" >
                        </div> 
                    </div>    
                    <div >
                        <button class="btn btn-success fs-3">Gravar</button>
                        <a href="listar_cliente.jsp" class="btn btn-warning fs-3">Voltar</a>
                    </div>
                </div>
                </form>

           
        </section>
    </body>
</html>