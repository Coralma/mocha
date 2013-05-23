package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

import com.homepage.model.Order;
import com.homepage.model.OrderDao;
import com.homepage.model.User;

public class DemoApplication extends HttpServlet {

	private User user = new User();

	// @Override
	// public void init(){
	//
	// }
	//
	// @Override
	// public Class<? extends Page> getHomePage() {
	//
	// return Pay.class;
	// }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		build(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		build(req, resp);
	}

	/*
	 * <form action="https://www.sandbox.paypal.com/cgi-bin/webscr"
	 * method="post"> <input type="hidden" name="cmd" value="_s-xclick"> <input
	 * type="hidden" name="hosted_button_id" value="EJVAHKJ9G5HY8"> <input
	 * type="hidden" name="notify_url"
	 * value="http://bf91e7fa2864.v2.localtunnel.com/ipn/validate"> <input
	 * type="image"
	 * src="https://www.sandbox.paypal.com/zh_XC/i/btn/btn_subscribeCC_LG.gif"
	 * border="0" name="submit" alt="PayPal"> <img alt="" border="0"
	 * src="https://www.sandbox.paypal.com/zh_XC/i/scr/pixel.gif" width="1"
	 * height="1" /> </form>
	 */

	private void build(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Cookie[] cookies = req.getCookies();

		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName());
		}
		
		Set<String> keys=req.getParameterMap().keySet();
		
		for(String key:keys){
			System.out.println(req.getAttribute("key"));
		}
		

		Order order = new Order();
		order.setOrderOID(UUID.randomUUID().toString());
		OrderDao orderDao = new OrderDao(order);
		orderDao.createOrderWithAccountAndUser(order,user);
		System.out.println("create new orderOID " + order.getOrderOID());
		
		HttpSession session=req.getSession();
		

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<form action='https://www.sandbox.paypal.com/cgi-bin/webscr'method='post'>");

		out.println("<input type='hidden' name='cmd' value='_s-xclick'> ");

		out.println("<input type='hidden' name='hosted_button_id' value='EJVAHKJ9G5HY8'>");

		out.println("<input type='hidden' name='notify_url' value='http://bf91e7fa2864.v2.localtunnel.com/ipn/validate'> ");

		out.println("<input type='image' src='https://www.sandbox.paypal.com/zh_XC/i/btn/btn_subscribeCC_LG.gif' border='0' name='submit' alt='PayPal'> ");

		out.println("<img alt='' border='0' src='https://www.sandbox.paypal.com/zh_XC/i/scr/pixel.gif' width='1' height='1' />");

		out.println("</form>");

		String DATA = req.getParameter("DATA");
		if (DATA != null) {
			out.println(DATA);
		} else {
			out.println("No text entered.");
		}

		out.println("");
		out.close();
	}
}
