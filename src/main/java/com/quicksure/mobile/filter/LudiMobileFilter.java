package com.quicksure.mobile.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quicksure.mobile.entity.Userinfor;

public class LudiMobileFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {		 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String path = req.getContextPath();
		String url = req.getRequestURI();
		Userinfor user = (Userinfor) session.getAttribute("loginUser");
		String conString = req.getHeader("REFERER");// 获取父url
		if (url.endsWith(".jsp") || url.endsWith(".do")) {
			if ("".equals(conString) || null == conString) {
				String servletPath = req.getServletPath();
				if (servletPath.indexOf("quicksurehome") >= 0
						|| servletPath.indexOf("LoginUser") >= 0
						|| servletPath.indexOf("MP_verify_PPlSQIplLY8fbefx.txt") >= 0
						|| servletPath.indexOf("/paymentCompleteServlet/goPaymentSucessPage.do")>=0
						|| servletPath.indexOf("/login.jsp")>=0
						|| servletPath.indexOf("/paymentComplete.do")>=0
						|| servletPath.indexOf("/goToFirstScreen.do")>=0
						|| servletPath.indexOf("/goToMyAccount.do")>=0
						|| servletPath.indexOf("/yiluHomePage.jsp")>=0
						|| servletPath.indexOf("/jiayixianHome.jsp")>=0
						|| servletPath.indexOf("/junanbaoHome.jsp")>=0
						|| servletPath.indexOf("/PolicyCallback.do")>=0  
						|| servletPath.indexOf("/PaymentCallback.do")>=0){
					session.removeAttribute("orderno");
					session.removeAttribute("jiayixianorderno");
					session.removeAttribute("yilupinganorderno");
					chain.doFilter(request, response);
				} else { // 当前请求url
					if(servletPath.indexOf("/quicksureFeiche/yilupingan")>=0){
						
						resp.sendRedirect(path
								+"/views/quicksureFeiche/yilupingan/jsp/yiluHomePage.jsp");
					}else if(servletPath.indexOf("/quicksureFeiche/jiayixian")>=0){
						
						resp.sendRedirect(path
								+"/views/quicksureFeiche/jiayixian/jsp/jiayixianHome.jsp");
					}else if(servletPath.indexOf("/quicksureFeiche/junanbao")>=0){
						
						resp.sendRedirect(path
								+"/views/quicksureFeiche/junanbao/jsp/junanbaoHome.jsp");
					}else{
						resp.sendRedirect(path
								+"/views/quicksure/jsp/quicksurehome.jsp");
					}
				}
			} else if (url.endsWith("/quicksurehome.jsp")
					|| url.endsWith("/LoginUser.jsp")
					|| url.endsWith("getDptCode.do")
					|| url.endsWith("checkPhoneCode.do")
					|| url.endsWith("userLogin.do")
					|| url.endsWith("phoneCheck.do")
					|| url.endsWith("/registUser.jsp")
					|| url.endsWith("registUser.do")
					|| url.endsWith("resetPassword.do")
					|| url.endsWith("/resetPassword.jsp")
					|| url.endsWith("goPaymentSucessPage.do")
					|| url.endsWith("paymentComplete.do")
					|| url.endsWith("userEverRegist.do")
					|| url.endsWith("goToFirstScreen.do")
					|| url.endsWith("goToMyAccount.do")
					|| url.endsWith("checkAgentCode.do")//注册时代理人识别码校验
					|| url.endsWith("/login.jsp")
					|| url.endsWith("/getVerify.do")
					|| url.endsWith("/checkVerify.do")
					|| url.endsWith("/batch_search.jsp")
					|| url.endsWith("getWechatJSAccess.do")
					|| (url.endsWith("weixiLoginByOpenId.do") && conString.indexOf("userId")>=0)
				){
				chain.doFilter(request, response);
			} else if (user == null) {
				if(url.endsWith("/myAccount.jsp")){
					//车险myaccount
					req.getRequestDispatcher("/views/quicksure/jsp/LoginUser.jsp?action=myaccount")
						.forward(request, response);
				}else if(url.endsWith("/feiche_myAccount.jsp")){
					//非车myaccount
					req.getRequestDispatcher("/views/quicksure/jsp/LoginUser.jsp?action=fcmyaccount")
						.forward(request, response);
				}else if(url.endsWith("/jiayixianHome.jsp")
						|| url.endsWith("/goTopersonInfo.do")){
					//驾意险首页
					req.getRequestDispatcher("/views/quicksure/jsp/LoginUser.jsp?action=jiayixian")
						.forward(request, response);
				}else if(url.endsWith("/junanbaoHome.jsp")
						|| url.endsWith("/getSetMealSumbit.do")){
					//君安保首页
					req.getRequestDispatcher("/views/quicksure/jsp/LoginUser.jsp?action=junanbao")
						.forward(request, response);
				}else if(url.endsWith("/yiluHomePage.jsp")
						|| url.endsWith("/immeInsuranceSubmit.do")){
					//一路平安首页
					req.getRequestDispatcher("/views/quicksure/jsp/LoginUser.jsp?action=yilupingan")
						.forward(request, response);
				}else{
					// 跳转到登陆页面
					req.getRequestDispatcher("/views/quicksure/jsp/LoginUser.jsp")
						.forward(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
