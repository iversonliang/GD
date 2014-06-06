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
	private Queue<AsyncContext> jobQueue;

	public BusinessHandleThread(AsyncContext asyncContext, Queue<AsyncContext> jobQueue) {
		this.asyncContext = asyncContext;
		this.jobQueue = jobQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			ServletResponse response = asyncContext.getResponse();
			PrintWriter out = response.getWriter();
			out.println("业务处理方法执行");
			// 这句话会响应到客户端
			// 告诉启动异步处理的Servlet异步处理已完成，Servlet就会提交请求
			asyncContext.complete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}