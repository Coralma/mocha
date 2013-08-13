/**
 * 
 */
package com.coral.foundation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Coral
 *
 */
public class StrUtils {
	
	public static String empty = "";

	public static String like(String condition) {
		return "%" + condition +"%"; 
	}
	
	public static String tabTitle(String title) {
		return "   " + title +"   "; 
	}
	
	/** upperFirstLetter */
	public static String capitalizeFirstLetter(String s) {
		String rs = s.substring(0, 1).toUpperCase() + s.substring(1);
		return rs;
	}
	
	public static String lowCaseFirstLetter(String s) {
		String rs = s.substring(0, 1).toLowerCase() + s.substring(1);
		return rs;
	}
	
	public static String getter(String s) {
		return "get" + capitalizeFirstLetter(s);
	}
	
	public static String setter(String s) {
		return "set" + capitalizeFirstLetter(s);
	}

	public static String cleanString(String str) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	    Matcher m = p.matcher(str);
	    return m.replaceAll("");
	}
	
	public static boolean isEmpty(Object obj) {
		if(obj == null) return true;
		if("".equals(obj.toString())) return true;
		return false;
	}
	public static boolean isEmptys(Object... objs) {
		for(Object obj : objs) {
			if(isEmpty(obj)) {
				return true;
			}
		}
		return false;
	}
	public static boolean isEmpty(String str) {
		if(str == null) return true;
		if("".equals(str)) return true;
		return false;
	}
	
	public static boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher isEmail = pattern.matcher(email);
		return isEmail.matches();
	}
	
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$|-\\d+$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean isCorrectInput(String... inputs) {
		for(String input : inputs) {
			if(isEmpty(input)) {
				return false;
			}
		}
		return true;
	}
	
	public static String asciiToXhtml(String ascii) {
		String html = ascii.replaceAll("\n", "<br/>").replaceAll("\t", "&nbsp;&nbsp;");
		html= linkHtml(html,"http://"); 
		html = linkHtml(html,"https://");
		return html;
	}
	
	public static String linkHtml(String html, String httpStr) {
		String htmlResult = "";
		String[] httpArray =html.split(httpStr);
		for(int i= 1; i< httpArray.length; i++) {
			String valueHtml = httpStr + httpArray[i];
			int endHttpIndex = valueHtml.indexOf(" ");
			if(endHttpIndex <= 0) {
				endHttpIndex = valueHtml.length(); 
			}
			String url = valueHtml.substring(0,endHttpIndex);
			if(url.endsWith(".") || url.endsWith(")")) {
				endHttpIndex = endHttpIndex - 1;
				url = valueHtml.substring(0,endHttpIndex);
			}
			String link = "<a href=\""+ url +"\" target=\"_blank\">" + url + "</a>";
			String finalHtml = link +  valueHtml.substring(endHttpIndex);
			httpArray[i] = finalHtml;
		}
		// recombile to a link
		for(int i= 0; i < httpArray.length; i++) {
			htmlResult += httpArray[i];
		}
		return htmlResult;
	}
	
	/**
	 * Change "UserName" to "USER_NAME"
	 * @param propertyName
	 * @return
	 */
	public static String genDBName(String propertyName) {
		List<String> dbNames = new ArrayList<String>();
		int startIndex=0,endIndex =0;
		
		for(int i=0; i<propertyName.length(); i++) {
			char c = propertyName.charAt(i);
			if(Character.isUpperCase(c)) {
				if(i==0) continue;
				endIndex = i;
				String subStr= propertyName.substring(startIndex, endIndex);
				startIndex= endIndex;
				dbNames.add(subStr);
			}
		}
		if(startIndex != 0) {
			String subStr= propertyName.substring(startIndex);
			dbNames.add(subStr);
		}
		if(dbNames.size() == 0) {
			return propertyName.toUpperCase();
		} else {
			String rs = "";
			for(String s : dbNames) {
				rs += s.toUpperCase()+"_";
			}
			rs = rs.substring(0, rs.lastIndexOf("_"));
			return rs.toUpperCase();
		}
	}
	
	/**
	 * Change "UserName" to "User Name"
	 * @param propertyName
	 * @return String
	 */
	public static String genLabel(String propertyName) {
		List<String> dbNames = new ArrayList<String>();
		int startIndex=0,endIndex =0;
		
		for(int i=0; i<propertyName.length(); i++) {
			char c = propertyName.charAt(i);
			if(Character.isUpperCase(c)) {
				if(i==0) continue;
				endIndex = i;
				String subStr= propertyName.substring(startIndex, endIndex);
				startIndex= endIndex;
				dbNames.add(subStr);
			}
		}
		if(startIndex != 0) {
			String subStr= propertyName.substring(startIndex);
			dbNames.add(subStr);
		}
		if(dbNames.size() == 0) {
			return capitalizeFirstLetter(propertyName);
		} else {
			String rs = "";
			for(String s : dbNames) {
				rs += capitalizeFirstLetter(s) +" ";
			}
			rs = rs.substring(0, rs.lastIndexOf(" "));
			return rs;
		}
	}
	
	public static String getRandomString(int length) {
	 	String base = "abcdefghijkmnpqrstuvwxy3456789";
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	}

	public static void main(String[] args) {
		System.out.println(isNumber("0.2"));
//		for(int i=0;i<10000;i++) {
//			System.out.println(getRandomString(6));
//		}
//	    System.out.println(genDBName("user"));
//	    System.out.println(genDBName("userName"));
//	    System.out.println(genDBName("disctinct"));
//	    System.out.println(genDBName("chooseFlag"));

//		System.out.println(genLabel("user"));
//	    System.out.println(genLabel("userName"));
//	    System.out.println(genLabel("disctinct"));
//	    System.out.println(genLabel("chooseFlag"));
//	    System.out.println(asciiToXhtml("123\n456\n789"));
//	    System.out.println(asciiToXhtml("Share the file Sea.jpg by the link http://localhost:9090/cooperate/share?key=092af515-a15c-4e2d-893e-988b601af57e is good!"));
//	    System.out.println(asciiToXhtml("Share the file Sea.jpg by the link http://localhost:9090/cooperate/share?key=092af515-a15c-4e2d-893e-988b601af57e"));
//	    System.out.println(asciiToXhtml("Over on the Emergent Futures Tumblr (http://emergentfutures.tumblr.com) we caught this post by +Paul Higgins about RoboEarth. Book share."));
//	    System.out.println(asciiToXhtml("The easiest ways to get the latest version of Vaadin 7 are described at http://vaadin.com/download. If you are using Maven or Ivy, the 7.0.2 version is already available in the central maven repository."));
	}
}
