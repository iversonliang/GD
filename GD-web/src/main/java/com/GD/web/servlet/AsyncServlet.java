package com.GD.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * asyncSupported属性默认是false,如果需要开启支持异步处理功能,需要设置为true
 */
@WebServlet(asyncSupported = true, urlPatterns="/async.do")
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Log logger = LogFactory.getLog(this.getClass());
	
	private Executor executor = Executors.newFixedThreadPool(10);

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("Servlet  begin <br>");
		System.out.println("servlet start");

		// 进入异步模式,调用业务处理线程进行业务处理
		// Servlet不会被阻塞,而是直接往下执行
		// 业务处理完成后的回应由AsyncContext管理
		AsyncContext asyncContext = request.startAsync(request, response);
		
//		ServletContext appScope = request.getServletContext();
//		System.out.println("-------------add contenxt to queue---------------");
//		Queue<AsyncContext> jobQueue = (Queue<AsyncContext>)appScope.getAttribute("slowWebServiceJobQueue");
//		jobQueue.add(asyncContext);
//		System.out.println("-------------queue size " + jobQueue.size() + " -----------------------");
		
		
		BusinessHandleThread businessHandleThread = new BusinessHandleThread(asyncContext);
		Thread thread = new Thread(businessHandleThread);
		thread.start();
		// asyncContext.start(businessHandleThread);
		// 也可以用这种方法启动异步线程

		out.println("Servlet end <br>");
		System.out.println("servlet done");
		out.flush();
	}
	
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Cache-Control", "private");
//		response.setHeader("Pragma", "no-cache");
//		request.setCharacterEncoding("UTF-8");
//		PrintWriter writer = response.getWriter();
//		// for IE
//		writer.println("<!-- Comet is a programming technique that enables web servers to send data to the client without having any need for the client to request it. -->\n");
//		writer.flush();
//
//		final AsyncWriter aw = new AsyncWriter();
//		final AsyncContext ac = request.startAsync();
//		ac.setTimeout(10 * 60 * 1000);
//		ac.addListener(new AsyncListener() {
//			public void onComplete(AsyncEvent event) throws IOException {
//				aw.queue.remove(ac);
//			}
//
//			public void onTimeout(AsyncEvent event) throws IOException {
//				aw.queue.remove(ac);
//			}
//
//			public void onError(AsyncEvent event) throws IOException {
//				aw.queue.remove(ac);
//			}
//
//			public void onStartAsync(AsyncEvent event) throws IOException {
//			}
//		});
//		System.out.println("--------------------add----------------");
//		try {
//			aw.add(ac);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("-------------servlet done-----------------");
//	}
}
