package en.common.frame.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import en.common.frame.engine.IConstants;
import en.common.frame.shiro.SessionUser;

public class BaseController {

	protected HttpServletRequest request;

	protected HttpServletResponse response;
	
	protected String  errorCode;
	
	protected static Logger logger = LogManager.getLogger();
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		
		this.request = request;
		this.response = response;
	}
	
	
	public SessionUser getSessionUser() throws Exception{
		Subject currentUser = SecurityUtils.getSubject();
		SessionUser sessionUser = (SessionUser)currentUser.getSession().getAttribute(IConstants.SESSION_USER_KEY);
		if(sessionUser == null )
			throw new Exception("登陆已失效,请重新登陆");
		return sessionUser;
	}
	
	public Subject getCurrentUser(){
		return SecurityUtils.getSubject();
	}
	
	public void setResultValue(String result) {
		response.setContentType("text/xml; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			//System.out.println("result: "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setStoreErrorResult(String result) {
		response.setContentType("text/xml; charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print("{success : false ,msg :\""+result+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setErrorResultValue(String result) {
		setResultValue("error&" + result);
	}
	
	public String getErrMsg(Exception e){
		
		return  e.getCause() == null ? e.getMessage() : e.getCause()
				.getMessage();
	}
}
