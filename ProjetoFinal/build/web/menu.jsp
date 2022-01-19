<%@page import="controller.GerenciarLogin"%>
<%@page import="model.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="estilo/body.css" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<%
    Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("ulogado", ulogado);


%>
        <nav id="menu-h">
            <ul>
                <c:if test="${ulogado!=null && ulogado.perfil!=null}">
                    <c:forEach var="menu" items="${ulogado.perfil.menus}">
                        <c:if test="${menu.exibir==1}">
                            <li><a class="bi bi-scissors fs-4 me-2" href="${menu.link}">${menu.nome}</a></li>
                        </c:if>
                    </c:forEach>
                </c:if>
                <li><a href="gerenciar_login.do" class="link-light bi bi-scissors fs-4 me-2">Sair</a></li>
            </ul>
        </nav>

<link rel="stylesheet" href="estilo/menu.css" type="text/css"/>