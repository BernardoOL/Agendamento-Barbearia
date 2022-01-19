/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Perfil;
import model.PerfilDAO;

/**
 *
 * @author pfela
 */
public class GerenciarMenuPerfil extends HttpServlet {

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
        String mensagem = "";
        String idPerfil = request.getParameter("idPerfil");
        String acao = request.getParameter("acao");
        
        try {
            PerfilDAO pDAO = new PerfilDAO();
            Perfil p = new Perfil();
            if (acao.equals("gerenciar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    p = pDAO.getCarregaPorID(Integer.parseInt(idPerfil));
                    if (p.getIdPerfil() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu_perfil.jsp");
                        request.setAttribute("perfilv", p);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Perfil não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            if (acao.equals("desvincular")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idMenu = request.getParameter("idMenu");
                    if (idMenu.equals("")) {
                        mensagem = "O campo idMenu deverá ser selecionado";
                    } else {
                        if (pDAO.desvincular(Integer.parseInt(idMenu), Integer.parseInt(idPerfil))) {
                            mensagem = "Desvinculado com sucesso!";
                        } else {
                            mensagem = "Erro ao desvincular";
                        }
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_menu_perfil.do?acao=gerenciar&idPerfil=" + idPerfil + "';");
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
        String idPerfil = request.getParameter("idPerfil");
        String idMenu = request.getParameter("idMenu");

        try {
            if (idPerfil.equals("") || idMenu.equals("")) {
                mensagem = "Campos Obrigatórios deverão ser selecionados";

            } else {
                PerfilDAO pDAO = new PerfilDAO();
                if (pDAO.vincular(Integer.parseInt(idMenu), Integer.parseInt(idPerfil))) {
                    mensagem = "Vinculado com sucesso!";
                } else {
                    mensagem = "Erro ao vincular o menu ao perfil";
                }
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_menu_perfil.do?acao=gerenciar&idPerfil=" + idPerfil + "';");
        out.println("</script>");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
