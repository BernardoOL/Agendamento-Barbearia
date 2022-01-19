package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.Usuario;
import model.UsuarioDAO;



public class GerenciarUsuario extends HttpServlet {

   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String acao = request.getParameter("acao");
        String idUsuario = request.getParameter("idUsuario");
        
        String mensagem = "";
        
        
        
        try{
            Usuario u = new Usuario();
            UsuarioDAO uDAO = new UsuarioDAO();
            if(GerenciarLogin.verificarPermissao(request, response)){
                    if(acao.equals("alterar")){
                        u = uDAO.getCarregaPorID(Integer.parseInt(idUsuario));
                        if(u.getIdUsuario()>0){
                            RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_usuario.jsp");
                            request.setAttribute("usuario", u);
                            disp.forward(request, response);
                        }else{
                            mensagem = "Usuario não encontrado";
                        }
                    }
            }else{
                mensagem = "Acesso Negado!";
            }
            
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                        u.setIdUsuario(Integer.parseInt(idUsuario));
                        if(uDAO.deletar(u)){
                            mensagem = "Usuario desativado com Sucesso!";
                        }else{
                            mensagem = "Erro ao desativar Usuario";
                        }
                    }
                }else{
                mensagem = "Acesso Negado!";
            }
            
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_usuario.jsp';");
        out.println("</script>");
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        PrintWriter out = response.getWriter();
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idPerfil = request.getParameter("idPerfil");
        String mensagem = "";
        
        Usuario u = new Usuario();
        if(!idUsuario.isEmpty())
            u.setIdUsuario(Integer.parseInt(idUsuario));

            if(nome.equals("")||login.equals("")||senha.equals("")||status.equals("")||idPerfil.equals("")){
                mensagem = "Campos obrigatorios deverão ser preenchidos!";
            }else{
                u.setNome(nome);
                u.setLogin(login);
                u.setSenha(senha);
                u.setStatus(Integer.parseInt(status));
                Perfil p = new Perfil();
                p.setIdPerfil(Integer.parseInt(idPerfil));
                u.setPerfil(p);
                try{
                    UsuarioDAO uDAO = new UsuarioDAO();
                if(uDAO.gravar(u)){
                    mensagem = "Gravado com sucesso!";
                }else{
                    mensagem = "Erro ao gravar!";
                }
            }catch(Exception e){
                out.print(e);
                mensagem = "Erro ao executar";            
            }    
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"')");
        out.println("location.href='listar_usuario.jsp';");
        out.println("</script>"); 
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}