package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Servico;
import model.ServicoDAO;

/**
 *
 * @author User
 */
public class GerenciarServico extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String acao = request.getParameter("acao");
        String idServico = request.getParameter("idServico");
        
       
        
        String mensagem = "";
        
        
        
        try{
            Servico s = new Servico();
            ServicoDAO sDAO = new ServicoDAO();
            if(acao.equals("alterar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    s = sDAO.getCarregaPorId(Integer.parseInt(idServico));
                    if(s.getIdServico()>0){
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_servico.jsp");
                        request.setAttribute("servico", s);
                        disp.forward(request, response);
                    }else{
                        mensagem = "Serviço não encontrado";
                    }
                }
            }else{
                mensagem = "Acesso Negado!";
             
            }
            
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                        s.setIdServico(Integer.parseInt(idServico));
                        if(sDAO.deletar(s)){
                            mensagem = "Serviço Deletado com Sucesso!";
                        }else{
                            mensagem = "Erro ao excluir Serviço";
                        }
                    }
            }else{
                mensagem = "Acesso Negado!";
            }
        
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar3";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_servico.jsp';");
        out.println("</script>");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String idServico = request.getParameter("idServico");     
        String nome = request.getParameter("nome");
        String valor = request.getParameter("valor");
        
        String mensagem = "";
        
        Servico s = new Servico();
        
        try{
            ServicoDAO sDAO = new ServicoDAO();
            if(!idServico.isEmpty()){
                s.setIdServico(Integer.parseInt(idServico));
            }
            if(valor.equals("") || nome.equals("")){
                mensagem = "Campos obrigatórios deverão ser preenchidos";
            }else{
                s.setNome(nome);
                double novovalor=0;
                if(!valor.isEmpty()){
                    novovalor = Double.parseDouble(valor.replace(".","").replace(",", "."));
                }
                s.setValor(novovalor);
                
            }if(sDAO.gravar(s)){
                mensagem = "Gravado com sucesso!"; 
            }else{
                mensagem = "Erro ao gravar o serviço";
            }        
        }catch(Exception e){
            out.println(e);
            mensagem = "Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('"+mensagem+"');");
        out.println("location.href='listar_servico.jsp';");
        out.println("</script>");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
