package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDAO;


public class GerenciarCliente extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String acao = request.getParameter("acao");
        String idCliente = request.getParameter("idCliente");
        
        
        String mensagem = "";
        
        Cliente c = new Cliente();
        
        try{
            
            ClienteDAO cDAO = new ClienteDAO();
            if(acao.equals("alterar")){
                    if(GerenciarLogin.verificarPermissao(request, response)){
                    c = cDAO.getCarregaPorID(Integer.parseInt(idCliente));
                    if(c.getIdCliente()>0){
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_cliente.jsp");
                        request.setAttribute("cliente", c);
                        disp.forward(request, response);
                    }else{
                        mensagem = "Cliente não encontrado";
                    }
                }
            }else{
                mensagem = "Acesso Negado!";
            }
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    c.setIdCliente(Integer.parseInt(idCliente));
                    if(cDAO.deletar(c)){
                        mensagem = "Cliente Deletado com Sucesso!";
                    }else{
                        mensagem = "Erro ao excluir Cliente";
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
        out.println("location.href='listar_cliente.jsp';");
        out.println("</script>");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String cpf = request.getParameter("cpf");
        String rua = request.getParameter("rua");
        String quadra = request.getParameter("quadra");
        String numero = request.getParameter("numero");
        String cep = request.getParameter("cep");
        
        String mensagem = "";
        
        Cliente c = new Cliente();
        
        try{
            
            ClienteDAO cDAO = new ClienteDAO();
            
            if(!idCliente.isEmpty()){
                c.setIdCliente(Integer.parseInt(idCliente));
            }
            
            if(nome.equals("") || cpf.equals("") || telefone.equals("")){
            mensagem = "Campos obrigatórios deverão ser preenchidos!";
            }else{
                
                c.setNome(nome);
                c.setTelefone(telefone);
                c.setCpf(cpf);
                c.setRua(rua);
                c.setQuadra(quadra);
                c.setNumero(numero);
                c.setCep(cep);
            }if(cDAO.gravar(c)){
                mensagem = "Gravado com sucesso!";
            }else{
                mensagem = "Erro ao executar";
            }
        
        
        }catch(Exception e){
            out.println(e);
            mensagem = "Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_cliente.jsp';");
        out.println("</script>");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
