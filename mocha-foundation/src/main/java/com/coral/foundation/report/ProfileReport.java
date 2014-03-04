package com.coral.foundation.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.coral.foundation.facebook.FBImpl;
import com.coral.foundation.facebook.FBUserModel;
import com.coral.foundation.facebook.FBUserModel.FbTimeline;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.gwt.dom.client.Element;

public class ProfileReport {

	private String linkedinProfileUrl;
	private String profileExperenceCSSPath;
	private String profileEductionCSSPath;

	private static String defaultFBHeader = "<head><meta charset=\"utf-8\"><script>function envFlush(a){function b(c){for(var d in a)c[d]=a[d];}if(window.requireLazy){window.requireLazy(['Env'],b);}else{Env=window.Env||{};b(Env);}}envFlush({\"ajaxpipe_token\":\"AXhdvFTOGEOn2vKr\",\"lhsh\":\"RAQGbtm0s\"});</script><script>CavalryLogger=false;</script><noscript><meta http-equiv=\"refresh\" content=\"0; URL=/chris.diehl.568/timeline?_fb_noscript=1\" /></noscript><meta name=\"robots\" content=\"noodp, noydir\"><meta name=\"referrer\" content=\"default\" id=\"meta_referrer\"><link rel=\"alternate\" media=\"handheld\" href=\"https://www.facebook.com/chris.diehl.568/timeline\">    <link type=\"text/css\" rel=\"stylesheet\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/y9/r/7lIwj3pUbPU.css\">    <link type=\"text/css\" rel=\"stylesheet\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yb/r/PCxoPhiSBIF.css\">    <script src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yP/r/52An4RQwBGj.js\" crossorigin=\"anonymous\"></script><script></script><title id=\"pageTitle\">Chris Diehl</title><noscript><meta http-equiv=\"X-Frame-Options\" content=\"DENY\" /></noscript><link rel=\"shortcut icon\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/yl/r/H3nktOa7ZMg.ico\">    <link type=\"text/css\" rel=\"stylesheet\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yH/r/9GyFLLdtrs8.css\">    <link type=\"text/css\" rel=\"stylesheet\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/a5-0ouS2MEm.css\">    <link type=\"text/css\" rel=\"stylesheet\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yq/r/cGQMRZwtvZg.css\">    <link type=\"text/css\" rel=\"stylesheet\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yL/r/6U7sFmECIh5.css\">    <link type=\"text/css\" rel=\"stylesheet\" href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yg/r/DvT7utzw-XB.css\"><script>(require(\"ServerJSDefine\")).handleDefines([[\"URLFragmentPreludeConfig\",[],{\"incorporateQuicklingFragment\":true,\"hashtagRedirect\":true},137],[\"BootloaderConfig\",[],{},329]]);new (require(\"ServerJS\"))().handle({\"require\":[[\"markJSEnabled\"],[\"lowerDomain\"],[\"URLFragmentPrelude\"],[\"Bootloader\"]]});</script><link href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/y1/r/R6vnwwi9PK6.css\" type=\"text/css\" rel=\"stylesheet\"><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yI/r/zr8RC2vpnqA.js\"></script><link href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yr/r/2Wal4uuEDkR.css\" type=\"text/css\" rel=\"stylesheet\"><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yW/r/pNCp3kNeXsS.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yy/r/73SZam0vx0P.js\"></script><link href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/y5/r/-goTdhkX5ZY.css\" type=\"text/css\" rel=\"stylesheet\"><link href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yZ/r/uCeTRjt7KYR.css\" type=\"text/css\" rel=\"stylesheet\"><link href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/ya/r/l7dhKRBDvxR.css\" type=\"text/css\" rel=\"stylesheet\"><link href=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yy/r/CvEc9FxUHPi.css\" type=\"text/css\" rel=\"stylesheet\"><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yo/r/bWeUoN3wHMs.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yS/r/5Ml6JQbcIPT.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/y2/r/fZpwfSHjL15.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yq/r/sD_-OUjPR3s.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yS/r/whaCftT5mvm.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/zWLWIaIzSFV.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yH/r/MtPHdZ_mZbM.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yq/r/IHAFoF4mfoS.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yP/r/hnYz4hdWDju.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yY/r/NSFk0lkowMb.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yZ/r/Yw5vHkgdSON.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yy/r/Hm3tLkXiLJ2.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/ym/r/iEERXc7tJ9-.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yr/r/2R2wilj27QW.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/y2/r/qTMrlJ_GR6C.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/y5/r/DSDv2g_Fgd1.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yu/r/vxUNUj8xdEg.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yb/r/KEsH2_5-aZ8.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yl/r/_SxfmeA6kqC.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yW/r/syIh22vn8YE.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yn/r/Vakudal6p1W.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yl/r/BoVoYjM1-G8.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yd/r/Uo610XOouMY.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/yv/r/ZQ8l-x2UAIV.js\"></script><script crossorigin=\"anonymous\" async=\"\" src=\"https://fbstatic-a.akamaihd.net/rsrc.php/v2/y9/r/p-PAQQaZ3PU.js\"></script><style type=\"text/css\" charset=\"utf-8\">/* See license.txt for terms of usage *//** reset styling **/.firebugResetStyles {    z-index: 2147483646 !important;    top: 0 !important;    left: 0 !important;    display: block !important;    border: 0 none !important;    margin: 0 !important;    padding: 0 !important;    outline: 0 !important;    min-width: 0 !important;    max-width: none !important;    min-height: 0 !important;    max-height: none !important;    position: fixed !important;    transform: rotate(0deg) !important;    transform-origin: 50% 50% !important;    border-radius: 0 !important;    box-shadow: none !important;    background: transparent none !important;    pointer-events: none !important;    white-space: normal !important;}.firebugBlockBackgroundColor {    background-color: transparent !important;}.firebugResetStyles:before, .firebugResetStyles:after {    content: \"\" !important;}/**actual styling to be modified by firebug theme**/.firebugCanvas {    display: none !important;}/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */.firebugLayoutBox {    width: auto !important;    position: static !important;}.firebugLayoutBoxOffset {    opacity: 0.8 !important;    position: fixed !important;}.firebugLayoutLine {    opacity: 0.4 !important;    background-color: #000000 !important;}.firebugLayoutLineLeft, .firebugLayoutLineRight {    width: 1px !important;    height: 100% !important;}.firebugLayoutLineTop, .firebugLayoutLineBottom {    width: 100% !important;    height: 1px !important;}.firebugLayoutLineTop {    margin-top: -1px !important;    border-top: 1px solid #999999 !important;}.firebugLayoutLineRight {    border-right: 1px solid #999999 !important;}.firebugLayoutLineBottom {    border-bottom: 1px solid #999999 !important;}.firebugLayoutLineLeft {    margin-left: -1px !important;    border-left: 1px solid #999999 !important;}/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */.firebugLayoutBoxParent {    border-top: 0 none !important;    border-right: 1px dashed #E00 !important;    border-bottom: 1px dashed #E00 !important;    border-left: 0 none !important;    position: fixed !important;    width: auto !important;}.firebugRuler{    position: absolute !important;}.firebugRulerH {    top: -15px !important;    left: 0 !important;    width: 100% !important;    height: 14px !important;    background: url(\"data:image/png,%89PNG%0D%0A%1A%0A%00%00%00%0DIHDR%00%00%13%88%00%00%00%0E%08%02%00%00%00L%25a%0A%00%00%00%04gAMA%00%00%D6%D8%D4OX2%00%00%00%19tEXtSoftware%00Adobe%20ImageReadyq%C9e%3C%00%00%04%F8IDATx%DA%EC%DD%D1n%E2%3A%00E%D1%80%F8%FF%EF%E2%AF2%95%D0D4%0E%C1%14%B0%8Fa-%E9%3E%CC%9C%87n%B9%81%A6W0%1C%A6i%9A%E7y%0As8%1CT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AATE9%FE%FCw%3E%9F%AF%2B%2F%BA%97%FDT%1D~K(%5C%9D%D5%EA%1B%5C%86%B5%A9%BDU%B5y%80%ED%AB*%03%FAV9%AB%E1%CEj%E7%82%EF%FB%18%BC%AEJ8%AB%FA'%D2%BEU9%D7U%ECc0%E1%A2r%5DynwVi%CFW%7F%BB%17%7Dy%EACU%CD%0E%F0%FA%3BX%FEbV%FEM%9B%2B%AD%BE%AA%E5%95v%AB%AA%E3E5%DCu%15rV9%07%B5%7F%B5w%FCm%BA%BE%AA%FBY%3D%14%F0%EE%C7%60%0EU%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5JU%88%D3%F5%1F%AE%DF%3B%1B%F2%3E%DAUCNa%F92%D02%AC%7Dm%F9%3A%D4%F2%8B6%AE*%BF%5C%C2Ym~9g5%D0Y%95%17%7C%C8c%B0%7C%18%26%9CU%CD%13i%F7%AA%90%B3Z%7D%95%B4%C7%60%E6E%B5%BC%05%B4%FBY%95U%9E%DB%FD%1C%FC%E0%9F%83%7F%BE%17%7DkjMU%E3%03%AC%7CWj%DF%83%9An%BCG%AE%F1%95%96yQ%0Dq%5Dy%00%3Et%B5'%FC6%5DS%95pV%95%01%81%FF'%07%00%00%00%00%00%00%00%00%00%F8x%C7%F0%BE%9COp%5D%C9%7C%AD%E7%E6%EBV%FB%1E%E0(%07%E5%AC%C6%3A%ABi%9C%8F%C6%0E9%AB%C0'%D2%8E%9F%F99%D0E%B5%99%14%F5%0D%CD%7F%24%C6%DEH%B8%E9rV%DFs%DB%D0%F7%00k%FE%1D%84%84%83J%B8%E3%BA%FB%EF%20%84%1C%D7%AD%B0%8E%D7U%C8Y%05%1E%D4t%EF%AD%95Q%BF8w%BF%E9%0A%BF%EB%03%00%00%00%00%00%00%00%00%00%B8vJ%8E%BB%F5%B1u%8Cx%80%E1o%5E%CA9%AB%CB%CB%8E%03%DF%1D%B7T%25%9C%D5(%EFJM8%AB%CC'%D2%B2*%A4s%E7c6%FB%3E%FA%A2%1E%80~%0E%3E%DA%10x%5D%95Uig%15u%15%ED%7C%14%B6%87%A1%3B%FCo8%A8%D8o%D3%ADO%01%EDx%83%1A~%1B%9FpP%A3%DC%C6'%9C%95gK%00%00%00%00%00%00%00%00%00%20%D9%C9%11%D0%C0%40%AF%3F%EE%EE%92%94%D6%16X%B5%BCMH%15%2F%BF%D4%A7%C87%F1%8E%F2%81%AE%AAvzr%DA2%ABV%17%7C%E63%83%E7I%DC%C6%0Bs%1B%EF6%1E%00%00%00%00%00%00%00%00%00%80cr%9CW%FF%7F%C6%01%0E%F1%CE%A5%84%B3%CA%BC%E0%CB%AA%84%CE%F9%BF)%EC%13%08WU%AE%AB%B1%AE%2BO%EC%8E%CBYe%FE%8CN%ABr%5Dy%60~%CFA%0D%F4%AE%D4%BE%C75%CA%EDVB%EA(%B7%F1%09g%E5%D9%12%00%00%00%00%00%00%00%00%00H%F6%EB%13S%E7y%5E%5E%FB%98%F0%22%D1%B2'%A7%F0%92%B1%BC%24z3%AC%7Dm%60%D5%92%B4%7CEUO%5E%F0%AA*%3BU%B9%AE%3E%A0j%94%07%A0%C7%A0%AB%FD%B5%3F%A0%F7%03T%3Dy%D7%F7%D6%D4%C0%AAU%D2%E6%DFt%3F%A8%CC%AA%F2%86%B9%D7%F5%1F%18%E6%01%F8%CC%D5%9E%F0%F3z%88%AA%90%EF%20%00%00%00%00%00%00%00%00%00%C0%A6%D3%EA%CFi%AFb%2C%7BB%0A%2B%C3%1A%D7%06V%D5%07%A8r%5D%3D%D9%A6%CAu%F5%25%CF%A2%99%97zNX%60%95%AB%5DUZ%D5%FBR%03%AB%1C%D4k%9F%3F%BB%5C%FF%81a%AE%AB'%7F%F3%EA%FE%F3z%94%AA%D8%DF%5B%01%00%00%00%00%00%00%00%00%00%8E%FB%F3%F2%B1%1B%8DWU%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*UiU%C7%BBe%E7%F3%B9%CB%AAJ%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5J%95*U%AAT%A9R%A5*%AAj%FD%C6%D4%5Eo%90%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5%86%AF%1B%9F%98%DA%EBm%BBV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%ADV%AB%D5j%B5Z%AD%D6%E4%F58%01%00%00%00%00%00%00%00%00%00%00%00%00%00%40%85%7F%02%0C%008%C2%D0H%16j%8FX%00%00%00%00IEND%AEB%60%82\") repeat-x !important;    border-top: 1px solid #BBBBBB !important;    border-right: 1px dashed #BBBBBB !important;    border-bottom: 1px solid #000000 !important;}.firebugRulerV {    top: 0 !important;    left: -15px !important;    width: 14px !important;    height: 100% !important;    background: url(\"data:image/png,%89PNG%0D%0A%1A%0A%00%00%00%0DIHDR%00%00%00%0E%00%00%13%88%08%02%00%00%00%0E%F5%CB%10%00%00%00%04gAMA%00%00%D6%D8%D4OX2%00%00%00%19tEXtSoftware%00Adobe%20ImageReadyq%C9e%3C%00%00%06~IDATx%DA%EC%DD%D1v%A20%14%40Qt%F1%FF%FF%E4%97%D9%07%3BT%19%92%DC%40(%90%EEy%9A5%CB%B6%E8%F6%9Ac%A4%CC0%84%FF%DC%9E%CF%E7%E3%F1%88%DE4%F8%5D%C7%9F%2F%BA%DD%5E%7FI%7D%F18%DDn%BA%C5%FB%DF%97%BFk%F2%10%FF%FD%B4%F2M%A7%FB%FD%FD%B3%22%07p%8F%3F%AE%E3%F4S%8A%8F%40%EEq%9D%BE8D%F0%0EY%A1Uq%B7%EA%1F%81%88V%E8X%3F%B4%CEy%B7h%D1%A2E%EBohU%FC%D9%AF2fO%8BBeD%BE%F7X%0C%97%A4%D6b7%2Ck%A5%12%E3%9B%60v%B7r%C7%1AI%8C%BD%2B%23r%00c0%B2v%9B%AD%CA%26%0C%1Ek%05A%FD%93%D0%2B%A1u%8B%16-%95q%5Ce%DCSO%8E%E4M%23%8B%F7%C2%FE%40%BB%BD%8C%FC%8A%B5V%EBu%40%F9%3B%A72%FA%AE%8C%D4%01%CC%B5%DA%13%9CB%AB%E2I%18%24%B0n%A9%0CZ*Ce%9C%A22%8E%D8NJ%1E%EB%FF%8F%AE%CAP%19*%C3%BAEKe%AC%D1%AAX%8C*%DEH%8F%C5W%A1e%AD%D4%B7%5C%5B%19%C5%DB%0D%EF%9F%19%1D%7B%5E%86%BD%0C%95%A12%AC%5B*%83%96%CAP%19%F62T%86%CAP%19*%83%96%CA%B8Xe%BC%FE)T%19%A1%17xg%7F%DA%CBP%19*%C3%BA%A52T%86%CAP%19%F62T%86%CA%B0n%A9%0CZ%1DV%C6%3D%F3%FCH%DE%B4%B8~%7F%5CZc%F1%D6%1F%AF%84%F9%0F6%E6%EBVt9%0E~%BEr%AF%23%B0%97%A12T%86%CAP%19%B4T%86%CA%B8Re%D8%CBP%19*%C3%BA%A52huX%19%AE%CA%E5%BC%0C%7B%19*CeX%B7h%A9%0C%95%E1%BC%0C%7B%19*CeX%B7T%06%AD%CB%5E%95%2B%BF.%8F%C5%97%D5%E4%7B%EE%82%D6%FB%CF-%9C%FD%B9%CF%3By%7B%19%F62T%86%CA%B0n%D1R%19*%A3%D3%CA%B0%97%A12T%86uKe%D0%EA%B02*%3F1%99%5DB%2B%A4%B5%F8%3A%7C%BA%2B%8Co%7D%5C%EDe%A8%0C%95a%DDR%19%B4T%C66%82fA%B2%ED%DA%9FC%FC%17GZ%06%C9%E1%B3%E5%2C%1A%9FoiB%EB%96%CA%A0%D5qe4%7B%7D%FD%85%F7%5B%ED_%E0s%07%F0k%951%ECr%0D%B5C%D7-g%D1%A8%0C%EB%96%CA%A0%A52T%C6)*%C3%5E%86%CAP%19%D6-%95A%EB*%95q%F8%BB%E3%F9%AB%F6%E21%ACZ%B7%22%B7%9B%3F%02%85%CB%A2%5B%B7%BA%5E%B7%9C%97%E1%BC%0C%EB%16-%95%A12z%AC%0C%BFc%A22T%86uKe%D0%EA%B02V%DD%AD%8A%2B%8CWhe%5E%AF%CF%F5%3B%26%CE%CBh%5C%19%CE%CB%B0%F3%A4%095%A1%CAP%19*Ce%A8%0C%3BO*Ce%A8%0C%95%A12%3A%AD%8C%0A%82%7B%F0v%1F%2FD%A9%5B%9F%EE%EA%26%AF%03%CA%DF9%7B%19*Ce%A8%0C%95%A12T%86%CA%B8Ze%D8%CBP%19*Ce%A8%0C%95%D1ae%EC%F7%89I%E1%B4%D7M%D7P%8BjU%5C%BB%3E%F2%20%D8%CBP%19*Ce%A8%0C%95%A12T%C6%D5*%C3%5E%86%CAP%19*Ce%B4O%07%7B%F0W%7Bw%1C%7C%1A%8C%B3%3B%D1%EE%AA%5C%D6-%EBV%83%80%5E%D0%CA%10%5CU%2BD%E07YU%86%CAP%19*%E3%9A%95%91%D9%A0%C8%AD%5B%EDv%9E%82%FFKOee%E4%8FUe%A8%0C%95%A12T%C6%1F%A9%8C%C8%3D%5B%A5%15%FD%14%22r%E7B%9F%17l%F8%BF%ED%EAf%2B%7F%CF%ECe%D8%CBP%19*Ce%A8%0C%95%E1%93~%7B%19%F62T%86%CAP%19*Ce%A8%0C%E7%13%DA%CBP%19*Ce%A8%0CZf%8B%16-Z%B4h%D1R%19f%8B%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1%A2%A52%CC%16-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2EKe%98-Z%B4h%D1%A2EKe%D02%5B%B4h%D1%A2EKe%D02%5B%B4h%D1%A2E%8B%96%CA0%5B%B4h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%16-%95a%B6h%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-Z*%C3l%D1%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z%B4T%86%D9%A2E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4h%A9%0C%B3E%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%D1R%19f%8B%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1%A2%A52%CC%16-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2EKe%98-Z%B4h%D1%A2EKe%D02%5B%B4h%D1%A2EKe%D02%5B%B4h%D1%A2E%8B%96%CA0%5B%B4h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%16-%95a%B6h%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-Z*%C3l%D1%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z%B4T%86%D9%A2E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4h%A9%0C%B3E%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%A9%0CZf%8B%16-Z%B4h%D1R%19f%8B%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1R%19%B4%CC%16-Z%B4h%D1%A2%A52%CC%16-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2%A52h%99-Z%B4h%D1%A2EKe%98-Z%B4h%D1%A2EKe%D02%5B%B4h%D1%A2EKe%D02%5B%B4h%D1%A2E%8B%96%CA0%5B%B4h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%96%CA%A0e%B6h%D1%A2E%8B%16-%95a%B6h%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-%95A%CBl%D1%A2E%8B%16-Z*%C3l%D1%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z*%83%96%D9%A2E%8B%16-Z%B4T%86%D9%A2E%8B%16-Z%B4T%06-%B3E%8B%16-Z%B4%AE%A4%F5%25%C0%00%DE%BF%5C'%0F%DA%B8q%00%00%00%00IEND%AEB%60%82\") repeat-y !important;    border-left: 1px solid #BBBBBB !important;    border-right: 1px solid #000000 !important;    border-bottom: 1px dashed #BBBBBB !important;}.overflowRulerX > .firebugRulerV {    left: 0 !important;}.overflowRulerY > .firebugRulerH {    top: 0 !important;}/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */.fbProxyElement {    position: fixed !important;    pointer-events: auto !important;}</style>";

	// private LinkedinConnectionDao dao = SpringContextUtils.getBean(LinkedinConnectionDao.class);

	public ProfileReport(String linkedinProfileUrl, String profileExperenceCSSPath, String profileEductionCSSPath) {
		this.setLinkedinProfileUrl(linkedinProfileUrl);
		this.setProfileEductionCSSPath(profileEductionCSSPath);
		this.setProfileExperenceCSSPath(profileExperenceCSSPath);
	}

	public ProfileReport() {

	}

	@SuppressWarnings("deprecation")
	private Map<String, String> faceBookLogin() {
		Map<String, String> jSoupCookie = new HashMap<String, String>();
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();

			HttpGet httpget = new HttpGet("http://www.facebook.com/login.php");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			// System.out.println("Login form get: " + response.getStatusLine());
			if (entity != null) {
				entity.consumeContent();
			}
			// System.out.println("Initial set of cookies:");
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			if (cookies.isEmpty()) {
				// System.out.println("None");
			}
			else {
				for (int i = 0; i < cookies.size(); i++) {
					// System.out.println("- " + cookies.get(i).toString());
				}
			}

			HttpPost httpost = new HttpPost("http://www.facebook.com/login.php");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("email", "vancezhao@gmail.com"));
			nvps.add(new BasicNameValuePair("pass", "jsvskk"));

			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			String fbLoginAuth = null;
			response = httpclient.execute(httpost);
			for (Header h : response.getAllHeaders()) {
				if (h.getName().contains("Location")) {
					fbLoginAuth = h.getValue();
				}
			}
			entity = response.getEntity();
			// System.out.println("Double check we've got right page " + EntityUtils.toString(entity));
			// System.out.println("Login form get: " + response.getStatusLine());
			if (entity != null) {
				entity.consumeContent();
			}

			System.out.println("Post logon cookies:");
			cookies = httpclient.getCookieStore().getCookies();

			if (cookies.isEmpty()) {
				System.out.println("None");
			}
			else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
					jSoupCookie.put(cookies.get(i).getName(), cookies.get(i).getValue());
				}
			}
			return jSoupCookie;
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public LinkedinConnection parseProfilePage() {
		// Document doc;
		// try {
		// doc = Jsoup.connect(linkedinProfileUrl).get();
		// LinkedinConnection conn = dao.findConnectByPublicProfile(linkedinProfileUrl);
		// Elements profileEduction = doc.select(profileEductionCSSPath);
		// Elements profileExperience = doc.select(profileExperenceCSSPath);
		// conn.setEduction(profileEduction.html());
		// conn.setExperience(profileExperience.html());
		// System.out.println(profileEduction.html());
		// System.out.println(profileExperience.html());
		// return conn;
		// }
		// catch (IOException e) {
		// e.printStackTrace();
		// }
		return null;
	}

	public ArrayList<String> parseFacebookProfilePage(String fbProfileUrl) {
		fbProfileUrl = fbProfileUrl + "/about";
		ArrayList<String> fbTimelineSections = new ArrayList<String>();
		Document doc;
		try {
			Connection con = Jsoup.connect(fbProfileUrl);
			con.request().timeout(60000);
			doc = con.cookies(faceBookLogin()).get();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("fb.log")));
			bw.write(doc.html());
			String profileMainHtml = doc.html();
			for (String str : profileMainHtml.split("\n")) {
				// find this user's TimeLine section only
				if (str.contains("</code>") && str.contains("fbTimelineSection") && !str.contains("Others Named")) {
					str = StringUtils.removeStart(StringUtils.removeEnd(str.trim(), "--></code>"), "<!--");
					fbTimelineSections.add(str);
				}
			}

			if (!fbProfileUrl.endsWith("/about")) {
				fbTimelineSections.addAll(parseFacebookProfilePage(fbProfileUrl + "/about"));
			}

			return fbTimelineSections;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public FbTimeline parseFacebookProfileTimeline(String fbProfileUrl) {
		fbProfileUrl = fbProfileUrl + "/timeline";
		FBUserModel fbUserModel = new FBUserModel();
		FbTimeline fbTimelineSections = fbUserModel.new FbTimeline();
		Document doc;
		try {
			Connection con = Jsoup.connect(fbProfileUrl);
			con.request().timeout(60000);
			doc = con.cookies(faceBookLogin()).get();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("fb_profile.log")));
			bw.write(doc.html());
			String profileMainHtml = doc.html();
			fbTimelineSections.setHeader(getDefaultFBHeader());
			// find the TimeLine by class timelineUnitContainer
			for (String str : profileMainHtml.split("\n")) {
				if (str.contains("timelineUnitContainer")) {
					str = StringUtils.removeStart(StringUtils.removeEnd(str.trim(), "--></code>"), "<!--");
					fbTimelineSections.getContent().add(str);
				}
			}

			// fbTimelineSections.add(doc.head().outerHtml());
			// fbTimelineSections.add(Jsoup.parse(doc.html()).getElementsByTag("style").first().data());
			return fbTimelineSections;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		ProfileReport p = new ProfileReport();
		String fbProfileUrl;
		// fbProfileUrl = "https://www.facebook.com/chun.chen";
		fbProfileUrl = "https://www.facebook.com/gong.xiaojing";
		String userName = p.getUserNamebyFBProfile(fbProfileUrl);
		System.out.println("User Name is: " + userName);
		// List<String> timelines = p.parseFacebookProfileTimeline(fbProfileUrl);
		//
		// System.out.println(getDefaultFBHeader());
		// for (String s : timelines) {
		// // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// System.out.println(s);
		// // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// }
	}

	/*
	 * Basic logic is to parse the <span itemprop="name">Terry Crews</span> in the return html
	 */
	public String getUserNamebyFBProfile(String fbProfileUrl) {
		// ArrayList<String> results = parseFacebookProfilePage(fbProfileUrl);
		// String parseStr = "href=\"" + fbProfileUrl + "\">";
		// for (String s : results) {
		// Document html = Jsoup.parse(s);
		// System.out.println();
		// for (org.jsoup.nodes.Element e : html.getElementsByTag("span")) {
		// if (e.attr("itemprop").contains("name")) {
		// return e.html();
		// }
		// }
		// if (s.contains(parseStr)) {
		// Document doc = Jsoup.parseBodyFragment(s.toString());
		// // org.jsoup.nodes.Element body = doc.body();
		// System.out.println("element is: " + doc.getElementsByTag("a").size());
		// for (org.jsoup.nodes.Element e : doc.getElementsByTag("a")) {
		// return e.ownText();
		// }
		// break;
		// }
		// }
		Connection con = Jsoup.connect(fbProfileUrl);
		con.request().timeout(60000);
		Document doc;
		try {
			doc = con.cookies(faceBookLogin()).get();
			System.out.println(doc.getElementsByTag("title").get(0).html());
			return doc.getElementsByTag("title").get(0).html();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public String getLinkedinProfileUrl() {
		return linkedinProfileUrl;
	}

	public void setLinkedinProfileUrl(String linkedinProfileUrl) {
		this.linkedinProfileUrl = linkedinProfileUrl;
	}

	public String getProfileExperenceCSSPath() {
		return profileExperenceCSSPath;
	}

	public void setProfileExperenceCSSPath(String profileExperenceCSSPath) {
		this.profileExperenceCSSPath = profileExperenceCSSPath;
	}

	public String getProfileEductionCSSPath() {
		return profileEductionCSSPath;
	}

	public void setProfileEductionCSSPath(String profileEductionCSSPath) {
		this.profileEductionCSSPath = profileEductionCSSPath;
	}

	public static String getDefaultFBHeader() {
		return defaultFBHeader;
	}

	public static void setDefaultFBHeader(String defaultFBHeader) {
		ProfileReport.defaultFBHeader = defaultFBHeader;
	}
}
