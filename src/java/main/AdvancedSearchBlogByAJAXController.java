/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.BlogDAO;
import dao.BlogImageDAO;
import entity.Blog;
import entity.BlogImage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author legion
 */
@WebServlet(name = "AdvancedSearchBlogByAJAXController", urlPatterns = {"/advanced-blog-search"})
public class AdvancedSearchBlogByAJAXController extends HttpServlet {

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

        String txt = request.getParameter("txt");
        String selectedMealType = request.getParameter("meals");
        String selectedFoods = request.getParameter("foods");
        String selectedSpecialDietType = request.getParameter("specials");
        String selectedPriceRange = request.getParameter("prices");

        ArrayList<String> foods = new ArrayList<>();
        ArrayList<String> meals = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> specials = new ArrayList<>();

        if (selectedMealType != null) {
            for (String value : selectedMealType.split(",")) {
                meals.add(value.trim());
            }
        }
        if (selectedFoods != null) {
            for (String value : selectedFoods.split(",")) {
                foods.add(value.trim());
            }
        }

        if (selectedPriceRange != null) {
            for (String value : selectedPriceRange.split(",")) {
                prices.add(value.trim());
            }
        }

        if (selectedSpecialDietType != null) {
            for (String value : selectedSpecialDietType.split(",")) {
                specials.add(value.trim());
            }
        }
        List<Blog> listBlog = new ArrayList<Blog>();

        ArrayList<BlogImage> listImage = BlogImageDAO.INS.getFirstImageOfAllBlog();
        if (meals.isEmpty() && foods.isEmpty() && prices.isEmpty() && specials.isEmpty()) {
            listBlog = BlogDAO.INS.searchBlogByTitle(txt);
        } else {
            listBlog = BlogDAO.INS.getBlogByAdvancedSearch(txt, meals, foods, prices, specials);
        }
        try (PrintWriter out = response.getWriter()) {
            if (listBlog != null && !listBlog.isEmpty()) {
                for (Blog blog : listBlog) {
                    out.println(" <div class=\"col-lg-4 col-md-4 col-sm-6 mix oranges fresh-meat\">");
                    out.println("<div class=\"featured__item\">");
                    for (BlogImage image : listImage) {
                        if (Integer.parseInt(image.getBlogID()) == blog.getBlogID()) {
                            out.println("<div class=\"featured__item__pic\"><img src=\"data:image/*;base64," + image.getImageLink() + "\" alt=\"\"></div>");
                        }
                    }
                    out.println("<div class=\"featured__item__text\"><h6><a href=\"blog-detail?id=" + blog.getBlogID() + "\">" + blog.getTitle() + "</a></h6></div>");
                    out.println("</div></div>");
                }
                out.println("</div>");
            }

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
