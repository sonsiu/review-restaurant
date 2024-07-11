/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import consts.IConstants;
import entity.UserGoogle;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author legion
 */
public final class Service {

    private Service() {
    }

    ;
    
    public static Properties getPropertiesByFileName(String fileName) {
        Properties properties = new Properties();
        try (InputStream inputStream = Service.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e);
        }
        return properties;
    }

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static String genRandSixDigit() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    public static String getImagePath(HttpServletRequest request, Part part, String folderPath) {
        try {
            String realpath = request.getServletContext().getRealPath("/" + folderPath);
            String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Path.of(realpath))) {
                Files.createDirectory(Path.of(realpath));
            }
            part.write(realpath + "/" + filename);
            String imagePath = folderPath + "/" + filename;
            return imagePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] getMultipleImagePaths(HttpServletRequest request, String folderPath, Part billLink) {
        try {
            // Get all uploaded parts (considering multiple images)
            Collection<Part> parts = request.getParts();

            String[] imagePaths = new String[parts.size()]; // Array to store image paths
            int i = 0;

            for (Part part : parts) {
                if (part != billLink) {
                    // Check if the part contains an image file
                    if (part.getContentType() != null && part.getContentType().startsWith("image/")) {
                        String realpath = request.getServletContext().getRealPath("/" + folderPath);
                        String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();

                        if (!Files.exists(Path.of(realpath))) {
                            Files.createDirectory(Path.of(realpath));
                        }

                        part.write(realpath + "/" + filename);
                        imagePaths[i++] = folderPath + "/" + filename;
                    }
                }
            }

            return imagePaths;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractUsername(String email) {
        if (email == null || email.isEmpty()) {
            return "";
        }
        int index = email.indexOf('@');
        if (index == -1) {
            return email;
        }
        return email.substring(0, index);
    }

    public static Timestamp getNowAfter10Minutes() {
        java.util.Date date = new java.util.Date();
        long curTime = date.getTime();
        long minutesToAdd = 10 * 60 * 1000;
        Timestamp result = new Timestamp(curTime + minutesToAdd);
        return result;
    }

    public static int compareTime(Timestamp expDate) { // -1 hop le, 0 hop le, 1 khong hop le
        java.util.Date date = new java.util.Date();
        Timestamp curTime = new Timestamp(date.getTime());
        return curTime.compareTo(expDate);
    }

    public static boolean isValidatedPassword(String password) {
        String regex = consts.IConstants.PASSWORD_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(IConstants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", IConstants.GOOGLE_CLIENT_ID)
                        .add("client_secret", IConstants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", IConstants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", IConstants.GOOGLE_GRANT_TYPE).build())
                .execute()
                .returnContent()
                .asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = IConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogle googlePojo = new Gson().fromJson(response, UserGoogle.class);

        return googlePojo;
    }

    public static String combineLocationStrings(String str1, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if(!str1.isBlank()){
            sb.append(str1);
            sb.append(", ");
        }
        if(!str2.isBlank()){
            sb.append(str2);
            sb.append(", ");
        }
        if(!str3.isBlank()){
            sb.append(str3);
        }
        return sb.toString();
    }
    
     public static ArrayList<String> extractValidHashtags(String text) {
        HashSet<String> seenTags = new HashSet<>(); // Use a HashSet for efficient uniqueness check
        ArrayList<String> validTags = new ArrayList<>();
        for (String part : text.split(",")) {
            part = part.trim(); 
            if (part.startsWith("#") && part.length() > 1 && part.length() < 16 && !part.contains(" ")) {
                if (seenTags.add(part)) { 
                    validTags.add(part);
                }
            }
        }
        return validTags;
    }
     
    public static String translateToVietnamese(String txt){
        return switch (txt) {
            case "Vietnamese" -> "Việt";
            case "Chinese" -> "Trung";
            case "Japanese" -> "Nhật";
            case "Korean" -> "Hàn";
            case "Thai" -> "Thái";
            case "American" -> "Mỹ";
            case "Europe" -> "Âu";
            case "Other" -> "Khác";
            case "Breakfast" -> "Ăn sáng";
            case "Lunch" -> "Ăn trưa";
            case "Brunch" -> "Ăn giữa trưa-sáng";
            case "Dinner" -> "Ăn tối";
            case "Late Night" -> "Ăn đêm";
            case "Drink" -> "Đồ uống";
            case "Vegan" -> "Ăn chay";
            case "High-protein" -> "Giàu protein";
            case "Low-protein" -> "Ít protein";
            case "Glueten-free" -> "Không Glueten";
            case "Low-fat" -> "Ít béo";
            case "Diabetic Diet" -> "Chế dộ ăn tiểu đường";
            default -> "NAN";
        };
    }

    public static void main(String[] args) {
//        String str = "123";
//        System.out.println(new Service().getMd5(str));
        //202cb962ac59075b964b07152d234b70

        System.out.println(getMd5("1"));
        //Your password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter and one digit.
    }

}
