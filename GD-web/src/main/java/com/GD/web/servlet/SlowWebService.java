package com.GD.web.servlet;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;

public class SlowWebService implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("---------------create queue --------------------");
		Queue<AsyncContext> jobQueue = new ConcurrentLinkedQueue<AsyncContext>();
		sce.getServletContext().setAttribute("slowWebServiceJobQueue", jobQueue);
		// pool size matching Web services capacity
		
//		while (true) {
//			System.out.println("loop");
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (!jobQueue.isEmpty()) {
//				System.out.println("-------------- queue is not empty ---------------------");
//				final AsyncContext aCtx = jobQueue.poll();
//				executor.execute(new Runnable() {
//					public void run() {
//						ServletRequest request = aCtx.getRequest();
//						// get parameteres
//						// invoke a Web service endpoint
//						// set results
//						// aCtx.forward("/result.jsp");
//						System.out.println("----------------listener------------");
//					}
//				});
//			}
//		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
