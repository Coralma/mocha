package com.ipnmessage.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import com.ipnmessage.pages.IpnMessageVaildation;
import com.ipnmessage.*;

public class IpnMessageValdation extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processReuqest(request, response);
	}

	private void processReuqest(HttpServletRequest request,
			HttpServletResponse response) {
		Map<?, ?> paramsMap = request.getParameterMap();
		if (paramsMap.size() <= 0) {
			System.out.println("Error populat here.. No param found");
		} else {
			IpnMessageVaildation ipnMessageValidation = new IpnMessageVaildation(
					request, paramsMap);
			try {
				processPayPalValidationÏ(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void processPayPalValidationÏ(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpClient client = new DefaultHttpClient();
		String PAYPAL_URL = "https://www.sandbox.paypal.com/cgi-bin/webscr";
		HttpPost post = new HttpPost(PAYPAL_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("cmd", "_notify-validate"));
		for (Enumeration<String> e = request.getParameterNames(); e
				.hasMoreElements();) {
			String name = e.nextElement();
			String value = request.getParameter(name);
			params.add(new BasicNameValuePair(name, value));
		}
		post.setEntity(new UrlEncodedFormEntity(params));
		String rc = getRC(client.execute(post)).trim();
		if ("VERIFIED".equals(rc)) {
			System.out.println("The request has been verified, thank you");
			
			

		} else {
			System.out.println("failed, the response message is: " + rc);

		}
	}

	private String getRC(HttpResponse response) throws IOException,
			IllegalStateException {
		InputStream is = response.getEntity().getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String result = "";
		String line = null;
		while ((line = br.readLine()) != null) {
			result += line;
		}
		return result;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processReuqest(request, response);
	}

}
