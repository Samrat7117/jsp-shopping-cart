package com.example; // it is define the project package

import jakarta.servlet.*; // servlet functionility
import jakarta.servlet.http.*; // servlet functionility
import java.io.IOException; //
import java.util.*; // it is used for list and the array list

public class CartServlet extends HttpServlet { //this line handel the http request (get and post)

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) // it is used for when a user submit a form 
            throws ServletException, IOException {

        String item = request.getParameter("item"); // that reads the item name from input

        HttpSession session = request.getSession(); // this session is stores the data per user

      
        List<String> cart = (List<String>) session.getAttribute("cart"); // that help to retrive the cart and typecasting also required and where the object change to the string

        if (cart == null) { // 1st time user cart does not exit
            cart = new ArrayList<>(); 
        }

       
        if (item != null && !item.trim().isEmpty()) { // helps to prevent the null item
            cart.add(item);
        }

     
        session.setAttribute("cart", cart); // update the session data


        response.sendRedirect("cart"); // it redirect to the ("/cart") and it also trigers the do get method
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) // It is used for when the user visits
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> cart = (List<String>) session.getAttribute("cart");

        request.setAttribute("cartItems", cart); // it passes the cart data to the jsp page

        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp"); // here rd means object and this helps to send the request to the cart .jsp and jsp will display the cart items
        rd.forward(request, response);
    }
}