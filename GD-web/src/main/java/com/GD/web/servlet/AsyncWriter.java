package com.GD.web.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;

/**
 * 向一个 Queue<AsyncContext> 中每个 Context 的 Writer 进行输出
 * 
 * @author zzm
 */
public class AsyncWriter {

	/**
	 * AsyncContext 队列
	 */
	public static final Queue<AsyncContext> queue = new ConcurrentLinkedQueue<AsyncContext>();

	/**
	 * 消息队列
	 */
	private static final BlockingQueue<String> MESSAGE_QUEUE = new LinkedBlockingQueue<String>();

	/**
	 * 发送消息到异步线程，最终输出到 http response 流
	 * 
	 * @param cbuf
	 * @param off
	 * @param len
	 * @throws IOException
	 */
	private void sendMessage(String message) throws IOException {
		try {
			MESSAGE_QUEUE.put(message);
		} catch (Exception ex) {
			IOException t = new IOException();
			t.initCause(ex);
			throw t;
		}
	}

	/**
	 * 异步线程，当消息队列中被放入数据，将释放 take 方法的阻塞，将数据发送到 http response 流上
	 */
	private Runnable notifierRunnable = new Runnable() {
		public void run() {
			boolean done = false;
			while (!done) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String message = null;
				try {
					message = MESSAGE_QUEUE.take();
					System.out.println("-------message:" + message + "  size:" + queue.size() + "------------------");
					for (AsyncContext ac : queue) {
						try {
							PrintWriter acWriter = ac.getResponse().getWriter();
							acWriter.println(htmlEscape(message));
							acWriter.flush();
						} catch (IOException ex) {
							System.out.println("ioex:" + ex);
							queue.remove(ac);
						}
					}
				} catch (InterruptedException iex) {
					done = true;
					System.out.println(iex);
				}
			}
		}
	};

	/**
	 * @param message
	 * @return
	 */
	private String htmlEscape(String message) {
		return "<script type='text/javascript'>\nwindow.parent.update(\"" + message.replaceAll("\n", "").replaceAll("\r", "") + "\");</script>\n";
	}

	/**
	 * 保持一个默认的 writer，输出至控制台 这个 writer 是同步输出，其它输出到 response 流的 writer 是异步输出
	 */
	private static final Writer DEFAULT_WRITER = new OutputStreamWriter(System.out);

	/**
	 * 构造 AsyncContextQueueWriter
	 * 
	 * @param queue
	 */
	AsyncWriter() {
		Thread notifierThread = new Thread(notifierRunnable);
		System.out.println("------------- notifierRunnable start -----------------");
		notifierThread.start();
	}
	
	public void add(AsyncContext ac) throws Exception {
		queue.add(ac);
		for (int i=0;i<100;i++) {
			String message = "log ----------------  " + i;
			this.write(message);
		}
		System.out.println("add message done");
	}
	
	public void write(String message) throws IOException {
		DEFAULT_WRITER.write(message);
		sendMessage(message);
	}

	public void flush() throws IOException {
		DEFAULT_WRITER.flush();
	}

	public void close() throws IOException {
		DEFAULT_WRITER.close();
		for (AsyncContext ac : queue) {
			ac.getResponse().getWriter().close();
		}
	}
}
