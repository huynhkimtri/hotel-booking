/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trihk.hotelbooking.entity.Hotel;
import trihk.hotelbooking.entity.HotelArea;
import trihk.hotelbooking.entity.RoomType;
import trihk.hotelbooking.service.HotelService;
import trihk.hotelbooking.service.RoomService;

/**
 *
 * @author TriHuynh
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = "home.jsp";
        try {
            String area = request.getParameter("area");
            String checkinDate = request.getParameter("checkin");
            String checkoutDate = request.getParameter("checkout");
            String amountNum = request.getParameter("amount");
            int amount, areaId;
            try {
                amount = Integer.parseInt(amountNum.trim());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            try {
                areaId = Integer.parseInt(area.trim());
            } catch (NumberFormatException e) {
                areaId = 0;
            }

            HotelService hotelService = new HotelService();
            List<HotelArea> listAreas = hotelService.getListHotelArea();

            List<Hotel> listHotel = hotelService.getHotelsWithAvailableHotelRoom(areaId, checkinDate, checkoutDate, amount);
            request.setAttribute("LIST_HOTEL", listHotel);
            request.setAttribute("LIST_AREA", listAreas);
            request.setAttribute("AREA", areaId);
            request.setAttribute("CK_IN_DATE", checkinDate);
            request.setAttribute("CK_OUT_DATE", checkoutDate);
            request.setAttribute("AMOUNT", amount);
        } catch (NumberFormatException e) {
            // TODO
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
