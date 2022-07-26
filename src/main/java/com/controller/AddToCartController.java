package com.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CartBean;
import com.dao.CartDao;

@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	int pId=Integer.parseInt(request.getParameter("pid"));
	HttpSession session=request.getSession();
	int userId=(Integer)session.getAttribute("userid");
	
	CartBean cart = new CartBean();
	cart.setpId(pId);
	cart.setUserId(userId);
	
	CartDao cartDao = new CartDao();
	cartDao.addToCart(cart);
	
	response.sendRedirect("ListProductController");
	
	}
}
