/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DBManager.DBObjects.Address;
import DBObjectManager.AddressManager;
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
public class ManageAddress extends HttpServlet {

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
            AddressManager addressManager = new AddressManager();
            HashMap hm = new HashMap();
            String operationName = request.getParameter("crud").toString();
            if (operationName.equalsIgnoreCase("add")) {
                String addressDetail = request.getParameter("address");
                String locLatStr = request.getParameter("loc_lat");
                String locLngStr = request.getParameter("loc_lng");
                String majorTypeStr = request.getParameter("major_type");
                String minorTypeStr = request.getParameter("minor_type");
                String addPosLat = request.getParameter("address_pos_lat");
                String addPosLng = request.getParameter("address_pos_lng");

                int majorType = 0, minorType = 0;
                majorType = Integer.parseInt(majorTypeStr);
                minorType = Integer.parseInt(minorTypeStr);

                hm = addressManager.addNewAddress(addressDetail, locLatStr, locLngStr, majorType, minorType, addPosLat, addPosLng);
                
            }else if(operationName.equalsIgnoreCase("modify")){
                String originalIdStr = request.getParameter("pk_id");
                int originalId = Integer.parseInt(originalIdStr);

                String addressDetail = request.getParameter("address");
                String locLatStr = request.getParameter("loc_lat");
                String locLngStr = request.getParameter("loc_lng");
                String majorTypeStr = request.getParameter("major_type");
                String minorTypeStr = request.getParameter("minor_type");
                String addPosLat = request.getParameter("address_pos_lat");
                String addPosLng = request.getParameter("address_pos_lng");

                int majorType = 0, minorType = 0;
                majorType = Integer.parseInt(majorTypeStr);
                minorType = Integer.parseInt(minorTypeStr);

                hm = addressManager.modifyAddress(addressDetail, locLatStr, locLngStr, majorType, minorType, addPosLat, addPosLng, originalId);
            }else if(operationName.equalsIgnoreCase("view")){
                String originalIdStr = request.getParameter("pk_id");
                int originalId = Integer.parseInt(originalIdStr);

                hm = addressManager.getAddress(originalId);
            }else if(operationName.equalsIgnoreCase("remove")){
                String originalIdStr = request.getParameter("pk_id");
                int originalId = Integer.parseInt(originalIdStr);

                hm = addressManager.getAddress(originalId);
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
