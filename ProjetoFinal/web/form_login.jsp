<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="estilo/login.css">
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="main">  
            
                <input type="checkbox" id="chk" aria-hidden="true">

                <div class="signup">


                    <%
                        String mensagem = (String) request.getSession().getAttribute("mensagem");
                        if (mensagem != null) {
                            request.getSession().removeAttribute("mensagem");


                    %>
                    <div class="alert alert-info"><%=mensagem%></div>
                    <%
                        }
                    %>


                </div>
                <div class="login">
                    <form action="gerenciar_login.do" method="POST">


                        <label for="chk" aria-hidden="true">Login</label>
                        <input type="text" name="login" placeholder="Login" required="">
                        <input type="password" name="senha" placeholder="Senha" required="">

                        <button type="submit" name="">Login</button>
                    </form>
                    <div class="gif"></div>
                </div>
            </div>
        </div>
    </form>	
</body>
</html>