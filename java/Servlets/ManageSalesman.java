/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import DBObjectManager.SalesmanManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class ManageSalesman extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            SalesmanManager salesmanManager = new SalesmanManager();
            HashMap hm = new HashMap();
            String operationName = request.getParameter("crud").toString();
            if (operationName.equalsIgnoreCase("add")) {
                 String name = request.getParameter("name");
                 String address = request.getParameter("address");
                 String phone = request.getParameter("phone");
                 String currentAreaCode = request.getParameter("current_area_code");
                hm = salesmanManager.addNewSalesman(name, address, phone, currentAreaCode);
            }else if(operationName.equalsIgnoreCase("modify")){
                String originalIdStr = request.getParameter("salesman_id");
                int originalId = Integer.parseInt(originalIdStr);
                String name = request.getParameter("name");
                 String address = request.getParameter("address");
                 String phone = request.getParameter("phone");
                 String currentAreaCode = request.getParameter("current_area_code");
                hm = salesmanManager.modifySalesman(name, address, phone, currentAreaCode, originalId);
            }else if(operationName.equalsIgnoreCase("view")){
                String originalIdStr = request.getParameter("salesman_id");
                int originalId = Integer.parseInt(originalIdStr);

                hm = salesmanManager.getSalesman(originalId);
            }else if(operationName.equalsIgnoreCase("remove")){
                String originalIdStr = request.getParameter("salesman_id");
                int originalId = Integer.parseInt(originalIdStr);

                hm = salesmanManager.getSalesman(originalId);
            }
            JSONObject jObj = new JSONObject(hm);
            out.write(jObj.toString());
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
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