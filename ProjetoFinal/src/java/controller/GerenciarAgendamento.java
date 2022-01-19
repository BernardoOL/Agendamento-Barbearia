package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agendamento;
import model.AgendamentoDAO;
import model.Cliente;
import model.Servico;
import model.Status;
import model.Usuario;

/**
 *
 * @author User
 */
public class GerenciarAgendamento extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String acao = request.getParameter("acao");
        String idAgendamento = request.getParameter("idAgendamento");
        String mensagem="";
        
        
        Agendamento agendamento = new Agendamento();
        
        try {
            
                AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            if(acao.equals("alterar")){
                    if(GerenciarLogin.verificarPermissao(request, response)){
                    agendamento = agendamentoDAO.getCarregaPorID(Integer.parseInt(idAgendamento));
                    if(agendamento.getIdAgendamento()>0){
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_agendamento.jsp");
                        request.setAttribute("agendamento", agendamento);
                        disp.forward(request, response);
                    }else{
                        mensagem = "Agendamento não encontrado";
                    }
                }
            }else{
                    mensagem = "Acesso Negado!";
                }
                
                if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                    agendamento.setIdAgendamento(Integer.parseInt(idAgendamento));
                    if(agendamentoDAO.deletar(agendamento)){
                        mensagem = "Agendamento Deletado com Sucesso!";
                    }else{
                        mensagem = "Erro ao excluir Agendamento";
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
        out.println("location.href='listar_agendamento.jsp';");
        out.println("</script>");
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idAgendamento = request.getParameter("idAgendamento");
        String idCliente = request.getParameter("idCliente");
        String idUsuario = request.getParameter("idUsuario");
        String idServico = request.getParameter("idServico");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");
        String idStatus = request.getParameter("idStatus");

        try {

            SimpleDateFormat dataf = new SimpleDateFormat("yyyy-MM-dd");

            Agendamento agendamento = new Agendamento();

            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

            if (idAgendamento != null && !idAgendamento.isEmpty()) {
                agendamento.setIdAgendamento(Integer.parseInt(idAgendamento));
            }

            if (idCliente.equals("") || idUsuario.equals("") || idServico.equals("") || data.equals("") || hora.equals("")) {
                mensagem = "Campos Obrigatorios deverão ser preenchidos!";
            } else {
                    
                agendamento.setDataHora(dataf.parse(data));
                agendamento.setHoraAgendamento(hora);
                
                
                Cliente cliente = new Cliente();
                cliente.setIdCliente(Integer.parseInt(idCliente));
                agendamento.setCliente(cliente);

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(idUsuario));
                    agendamento.setVendedor(usuario);
                
                
                if(idStatus!= null && !idStatus.isEmpty()){
                    Status status = new Status();
                    status.setIdStatus(Integer.parseInt(idStatus));
                    agendamento.setStatus(status);
                }else{
                    Status status = new Status();
                    status.setIdStatus(1);
                    agendamento.setStatus(status);

                }    
                
                Servico servico = new Servico();
                servico.setIdServico(Integer.parseInt(idServico));
                agendamento.setServico(servico);

            }

            if (agendamentoDAO.registrar(agendamento)) {
                mensagem = "Agendado com Sucesso!";
            } else {
                mensagem = "Erro ao agendar";
            }

        } catch (Exception e) {
            out.println(e);
            e.printStackTrace();
            mensagem = "Erro ao executar1";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_agendamento.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}