package com.GD.web.servlet;

import java.io.PrintWriter;
import java.util.Queue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

/**
 * 业务处理线程
 * 
 * @author Luxh
 */
public class BusinessHandleThread implements Runnable {

	// 异步操作的上下文对象,通过构造方法传进来
	private AsyncContext asyncContext;

	public BusinessHandleThread(AsyncContext asyncContext) {
		this.asyncContext = asyncContext;
	}

	@Override
	public void run() {
		try {
			ServletResponse response = asyncContext.getResponse();
			PrintWriter out = response.getWriter();
			for (int i = 0; i < 100; i++) {
				String message = "test" + i;
				Thread.sleep(500);
				out.println(htmlEscape(message));
				System.out.println(message);
				out.flush();
			}
			// 这句话会响应到客户端
			// 告诉启动异步处理的Servlet异步处理已完成，Servlet就会提交请求
			asyncContext.complete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String htmlEscape(String message) {
		return "<script type='text/javascript'>\nwindow.parent.update(\"" + message.replaceAll("\n", "").replaceAll("\r", "") + "\");</script>\n";
	}

}