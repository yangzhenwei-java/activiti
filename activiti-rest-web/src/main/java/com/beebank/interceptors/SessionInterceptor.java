package com.beebank.interceptors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.beebank.constants.ErrorCode;
import com.beebank.model.RspDTO;
import com.beebank.utils.log.LogUtil;

public class SessionInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		HttpSession session = request.getSession();
		if (session == null) {
			LogUtil.info("登陆超时的请求拦截[{}]", url);
			res(response, new RspDTO(ErrorCode.ERROR_LOGIN_TIMEOUT, "登陆超时"));
			return false;
		}
		return true;
	}

	private void res(HttpServletResponse response, RspDTO rsp) throws IOException {

		response.setContentType("application/json");
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.print(JSONObject.toJSON(rsp));
		out.flush();
		out.close();

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
