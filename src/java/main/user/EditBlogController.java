/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.*;
import entity.Blog;
import entity.BlogImage;
import entity.BlogInfo;
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
import java.util.ArrayList;
import java.util.Collection;
import service.Service;

/**
 *
 * @author legion
 */
@MultipartConfig
@WebServlet(name = "EditBlogController", urlPatterns = {"/edit-blog"})
public class EditBlogController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Blog blog = BlogDAO.INS.getBlogDetailByID(id);

            if (blog == null || blog.getStatus() != 0) {
                response.sendRedirect("errorpage.jsp");
                return;
            }

            ArrayList<BlogInfo> mealType = BlogInfoDAO.INS.getType(id, "meal");
            ArrayList<BlogInfo> foodType = BlogInfoDAO.INS.getType(id, "food");
            ArrayList<BlogInfo> specialDietType = BlogInfoDAO.INS.getType(id, "special");
            ArrayList<BlogInfo> priceType = BlogInfoDAO.INS.getType(id, "price");
            ArrayList<String> hashtags = BlogInfoDAO.INS.getTagsByBlogID(id);

            for (BlogInfo meal : mealType) {
                if (meal.getInfoValue().equals(Service.translateToVietnamese("Breakfast"))) {
                    boolean isBreakfast = true;
                    request.setAttribute("isBreakfast", isBreakfast);
                }
                if (meal.getInfoValue().equals(Service.translateToVietnamese("Lunch"))) {
                    boolean isLunch = true;
                    request.setAttribute("isLunch", isLunch);
                }
                if (meal.getInfoValue().equals(Service.translateToVietnamese("Dinner"))) {
                    boolean isDinner = true;
                    request.setAttribute("isDinner", isDinner);
                }
                if (meal.getInfoValue().equals(Service.translateToVietnamese("Brunch"))) {
                    boolean isBrunch = true;
                    request.setAttribute("isBrunch", isBrunch);
                }
                if (meal.getInfoValue().equals(Service.translateToVietnamese("Late Night"))) {
                    boolean isLateNight = true;
                    request.setAttribute("isLateNight", isLateNight);
                }
                if (meal.getInfoValue().equals(Service.translateToVietnamese("Drink"))) {
                    boolean isDrink = true;
                    request.setAttribute("isDrink", isDrink);
                }

            }

            for (BlogInfo food : foodType) {
                if (food.getInfoValue().equals(Service.translateToVietnamese("Vietnamese"))) {
                    boolean isVietnamese = true;
                    request.setAttribute("isVietnamese", isVietnamese);
                }
                if (food.getInfoValue().equals(Service.translateToVietnamese("Chinese"))) {
                    boolean isChinese = true;
                    request.setAttribute("isChinese", isChinese);
                }
                if (food.getInfoValue().equals(Service.translateToVietnamese("Japanese"))) {
                    boolean isJapanese = true;
                    request.setAttribute("isJapanese", isJapanese);
                }
                if (food.getInfoValue().equals(Service.translateToVietnamese("Korean"))) {
                    boolean isKorean = true;
                    request.setAttribute("isKorean", isKorean);
                }
                if (food.getInfoValue().equals(Service.translateToVietnamese("Thai"))) {
                    boolean isThai = true;
                    request.setAttribute("isThai", isThai);
                }
                if (food.getInfoValue().equals(Service.translateToVietnamese("American"))) {
                    boolean isAmerican = true;
                    request.setAttribute("isAmerican", isAmerican);
                }
                if (food.getInfoValue().equals(Service.translateToVietnamese("Europe"))) {
                    boolean isEurope = true;
                    request.setAttribute("isEurope", isEurope);
                }
                if (food.getInfoValue().equals(Service.translateToVietnamese("Other"))) {
                    boolean isOther = true;
                    request.setAttribute("isOther", isOther);
                }
            }

            for (BlogInfo special : specialDietType) {
                if (special.getInfoValue().equals(Service.translateToVietnamese("Vegan"))) {
                    boolean isVegan = true;
                    request.setAttribute("isVegan", isVegan);
                }
                if (special.getInfoValue().equals(Service.translateToVietnamese("Glueten-free"))) {
                    boolean isGlueten = true;
                    request.setAttribute("isGlueten", isGlueten);
                }
                if (special.getInfoValue().equals(Service.translateToVietnamese("Low-fat"))) {
                    boolean isLowFat = true;
                    request.setAttribute("isLowFat", isLowFat);
                }
                if (special.getInfoValue().equals(Service.translateToVietnamese("Diabetic Diet"))) {
                    boolean isDiabetic = true;
                    request.setAttribute("isDiabetic", isDiabetic);
                }
                if (special.getInfoValue().equals(Service.translateToVietnamese("High-protein"))) {
                    boolean isHigh = true;
                    request.setAttribute("isHigh", isHigh);
                }
                if (special.getInfoValue().equals(Service.translateToVietnamese("Low-protein"))) {
                    boolean isLow = true;
                    request.setAttribute("isLow", isLow);
                }
            }

            for (BlogInfo price : priceType) {
                if (price.getInfoValue().equals("$ (>10000 vnd)")) {
                    boolean tier1 = true;
                    request.setAttribute("tier1", tier1);
                }
                if (price.getInfoValue().equals("$+ (10000 - 50000 vnd)")) {
                    boolean tier2 = true;
                    request.setAttribute("tier2", tier2);
                }
                if (price.getInfoValue().equals("$$ (50001 - 200000 vnd)")) {
                    boolean tier3 = true;
                    request.setAttribute("tier3", tier3);
                }
                if (price.getInfoValue().equals("$$+ (200001 - 500000 vnd)")) {
                    boolean tier4 = true;
                    request.setAttribute("tier4", tier4);
                }
                if (price.getInfoValue().equals("$$$ (<500000 vnd)")) {
                    boolean tier5 = true;
                    request.setAttribute("tier5", tier5);
                }
            }

            ArrayList<BlogImage> images = BlogImageDAO.INS.getImagesByBlogID(id);

            request.setAttribute("blogId", blog.getBlogID());
            request.setAttribute("title", blog.getTitle());
            request.setAttribute("description", blog.getDescription());
            request.setAttribute("address", blog.getEateryAddress());
            request.setAttribute("location", blog.getEateryLocation());
            request.setAttribute("billImage", blog.getBillLink());
            request.setAttribute("images", images);

            request.setAttribute("quality", blog.getFoodQualityRate());
            request.setAttribute("environment", blog.getEnvironmentRate());
            request.setAttribute("price", blog.getPricingRate());
            request.setAttribute("service", blog.getServiceRate());
            request.setAttribute("hygine", blog.getHygienRate());

            request.setAttribute("hashtag", hashtags);
            request.setAttribute("hashtagString", String.join(",", hashtags));

            request.getRequestDispatcher("editblog.jsp").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("errorpage.jsp");
            return;
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
        try {
            //String blogId = request.getParameter("blogID");
            String blogID = request.getParameter("blogID");

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");

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

            float rate = Float.parseFloat(request.getParameter("overall"));

            Part bill = request.getPart("billLink");
            InputStream inputStream = bill.getInputStream();
            Collection<Part> parts = request.getParts();

            if (BlogImageDAO.INS.removeBlogImageByBlogID(blogID)) {
                BlogImageDAO.INS.addImage(blogID, parts, bill);
            }

            if (BlogInfoDAO.INS.removeType(blogID, "hashtag")) {
                for (String tag : tags) {
                    if (!tag.isBlank() || !tag.isEmpty()) {
                        BlogInfoDAO.INS.addCategoryType(blogID, tag, "hashtag");
                    }
                }
            }

            if (BlogInfoDAO.INS.removeType(blogID, "meal")) {
                if (selectedMealType != null) {
                    for (String mealType : selectedMealType) {
                        BlogInfoDAO.INS.addCategoryType(blogID, mealType, "meal");
                    }
                }
            }
            if (BlogInfoDAO.INS.removeType(blogID, "food")) {
                if (selectedFoodType != null) {
                    for (String FoodType : selectedFoodType) {
                        BlogInfoDAO.INS.addCategoryType(blogID, FoodType, "food");
                    }
                }
            }
            if (BlogInfoDAO.INS.removeType(blogID, "special")) {
                if (selectedSpecialDietType != null) {
                    for (String SpecialDietType : selectedSpecialDietType) {
                        BlogInfoDAO.INS.addCategoryType(blogID, SpecialDietType, "special");
                    }
                }

            }

            if (BlogInfoDAO.INS.removeType(blogID, "price")) {
                BlogInfoDAO.INS.addCategoryType(blogID, selectedPriceRange, "price");
            }

            boolean result = BlogDAO.INS.updateBlog(
                    blogID, user.getId(), title, description,
                    inputStream, rate, location,
                    address, foodQuality, environment, service, pricing, hygiene);

            //If created failed , return immediately
            if (result) {
                request.setAttribute("notify", Notification.builder().type("success").title("Bài viết chỉnh sửa thành công").message("Chi tiết bài viết đã được cập nhật").build());
            } else {
                request.setAttribute("notify", Notification.builder().type("error").title("Bài viết chỉnh sửa thất bại ").message("Có lỗi xảy ra").build());
            }
            request.getRequestDispatcher("showblog").forward(request, response);
            return;

        } catch (Exception e) {
            response.sendRedirect("errorpage.jsp");
            return;
        }
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
