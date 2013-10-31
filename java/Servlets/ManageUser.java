/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import DBManager.DBObjects.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Zakaria
 */




public class ManageUser extends HttpServlet {

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
     
            User user = new User();
            String operationName = request.getParameter("crud").toString();

            if(operationName.equalsIgnoreCase("add")){
                String userName = request.getParameter("user_name");
                String pass = request.getParameter("password");
                String access = request.getParameter("access");
                String userTypeStr = request.getParameter("user_type");
                int userType = 1;
                if(userTypeStr != null){
                    userType = Integer.parseInt(userTypeStr);
                }
                String pLat = request.getParameter("plat");
                String pLng = request.getParameter("plng");
                String msg = user.addUser(userName, pass, access, userType, pLat, pLng);
                out.write(msg);
            }
            else if(operationName.equalsIgnoreCase("view")){
                String userName = request.getParameter("user_name");
                Vector vector =  user.getUserInfoByName(userName);
                JSONArray jArr = new JSONArray(vector);
                out.write(jArr.toString());
            }
            
            else if(operationName.equalsIgnoreCase("login"))
            {
                String userName = request.getParameter("user_name");
                String pass = request.getParameter("password");
              
                String pLat = request.getParameter("plat");
                String pLng = request.getParameter("plng");
                HashMap userHM = new HashMap();
                   
              if(user.getLogIn(userName, pass) == true)
              {
                   userHM.put("password_match", "true");
              }
              else 
              {
                   userHM.put("password_match", "false");
              }
               Vector vecJsonResponse = new Vector();
               vecJsonResponse.add(userHM);
               JSONArray jArr = new JSONArray(vecJsonResponse);
                out.write(jArr.toString());
                //out.write(msg);
            }
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
