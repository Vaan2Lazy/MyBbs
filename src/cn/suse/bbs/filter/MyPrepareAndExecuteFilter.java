package cn.suse.bbs.filter;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
/**
 * 修改 ueidtor在线编辑器与Struts2在上传图片或其他东西时的请求的冲突
 * @author Lazy
 *
 */
public class MyPrepareAndExecuteFilter extends StrutsPrepareAndExecuteFilter {
	  
      
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {  
	        HttpServletRequest request = (HttpServletRequest) req;  
	        HttpServletResponse response=(HttpServletResponse)res;
			//设置字符集
			request.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html;charset=UTF-8");
	        String url = request.getRequestURI();  	                    
	       if ("/bbs/ueditor/jsp/imageUp.jsp".equals(url) || "/bbs/ueditor/jsp/fileUp.jsp".equals(url)) {
		    //if(url.contains("/bbs/ueditor/jsp/")){ 
		    	chain.doFilter(request, response);  
	        }else{
		        	super.doFilter(request,response,chain);
	        }
	    }  
}
