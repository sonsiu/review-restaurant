/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.*;
import entity.Notification;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.*;
import service.Service;

/**
 *
 * @author legion
 */
@MultipartConfig
@WebServlet(name = "CreateBlogController", urlPatterns = {"/createblog"})
public class CreateBlogController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DemoUpload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
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
        try{
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");
            List<Notification> listNotif = NotificationDAO.INS.getNLastDayNotificationByUserId(2, user.getId());
            request.setAttribute("listNotif", listNotif);

            request.getRequestDispatcher("createblog.jsp").forward(request, response);
            
        }catch(Exception e){
            response.sendRedirect("errorpage.jsp");
        }
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
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String city = request.getParameter("textCity");
        String district = request.getParameter("textDistrict");
        String ward = request.getParameter("textWard");

        ArrayList<String> tags = Service.extractValidHashtags(request.getParameter("tags-array"));

        String[] selectedMealType = request.getParameterValues("meal-type");
        String[] selectedFoodType = request.getParameterValues("food-type");
        String[] selectedSpecialDietType = request.getParameterValues("special-diet-type");
        String selectedPriceRange = request.getParameter("price-range");

        int foodQuality = Integer.parseInt(request.getParameter("foodQuality"));
        int environment = Integer.parseInt(request.getParameter("environment"));
        int service = Integer.parseInt(request.getParameter("service"));
        int pricing = Integer.parseInt(request.getParameter("pricing"));
        int hygiene = Integer.parseInt(request.getParameter("hygiene"));

        String location = Service.combineLocationStrings(city, district, ward);
        String address = request.getParameter("address");

        //Get current user_id based on account that logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        
        //Get Rating
        float rate = Float.parseFloat(request.getParameter("overall"));

        //Get all the image path string
        Part bill = request.getPart("billLink");
        InputStream inputStream = bill.getInputStream();
        Collection<Part> parts = request.getParts();

        // ADD session user name here
        boolean result = BlogDAO.INS.createBlog(
                user.getId(),
                title, description, inputStream,
                rate, "null", location, address,
                foodQuality, environment, service, pricing, hygiene
        );
        //Get the newest blog id that was created above           
        int blogID = BlogDAO.INS.getNewestBlogID();

        //If created failed , return immediately
        if (result) {
            request.setAttribute("notify", Notification.builder().type("success").title("Bài viết tạo thành công").message("Vui lòng đợi được xác minh").build());
        } else {
            request.setAttribute("notify", Notification.builder().type("error").title("Bài viết tạo thất bại ").message("Có lỗi xảy ra").build());
            request.getRequestDispatcher("showblog").forward(request, response);
            return;
        }

        //Else Continue filling data to other tables
        //ADD Hashtags 
        for (String tag : tags) {
            if (!tag.isBlank() || !tag.isEmpty()) {
                BlogInfoDAO.INS.addCategoryType(String.valueOf(blogID), tag, "hashtag");
            }
        }
        //ADD Info : Meal Type , Food Type , Special Diet , Price Range
        if (selectedMealType != null) {
            for (String mealType : selectedMealType) {
                BlogInfoDAO.INS.addCategoryType(String.valueOf(blogID), mealType, "meal");
            }
        }

        if (selectedFoodType != null) {
            for (String FoodType : selectedFoodType) {
                BlogInfoDAO.INS.addCategoryType(String.valueOf(blogID), FoodType, "food");
            }
        }

        if (selectedSpecialDietType != null) {
            for (String SpecialDietType : selectedSpecialDietType) {
                BlogInfoDAO.INS.addCategoryType(String.valueOf(blogID), SpecialDietType, "special");
            }
        }

        BlogInfoDAO.INS.addCategoryType(String.valueOf(blogID), selectedPriceRange, "price");

        //Insert all images of the blog into blog_image
        BlogImageDAO.INS.addImage(String.valueOf(blogID), parts, bill);

        //Remove title , desc saving once the creation is complete
        if (session.getAttribute("sessionTitle") != null) {
            session.removeAttribute("sessionTitle");
        }
        if (session.getAttribute("sessionDesc") != null) {
            session.removeAttribute("sessionDesc");
        }

        request.getRequestDispatcher("showblog").forward(request, response);
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

//        Part image = request.getPart("imageLink");
//        String imageFileName = image.getSubmittedFileName();
//        String uploadPath = IConstants.IMAGE_FOLDER_PATH + imageFileName;
//        try {
//            FileOutputStream fos = new FileOutputStream(uploadPath);
//            InputStream is = image.getInputStream();
//            byte[] data = new byte[is.available()];
//            is.read(data);
//            fos.write(data);
//            fos.close();
//            path = uploadPath;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
