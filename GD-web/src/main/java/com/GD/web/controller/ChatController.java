package com.GD.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @作者 Neeke
 * @文件名 ChatController.java
 * @作用 处理聊天消息
 * @Blog http://www.ineeke.com
 */
public class ChatController {
	
	public static final String DIR = "/chat";
	protected Log logger = LogFactory.getLog(this.getClass());
 
//    //存放所有的用户请求
//    private final Map<String, DeferredResult<Message>> chatRequests = new ConcurrentHashMap<String, DeferredResult<Message>>();
//    private final Map<String, Message> LEFT_TALK = new ConcurrentHashMap<String, Message>();
//    //时间格式化
//    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// 
//    /**
//     * @作者 Neeke
//     * @作用 登录
//     * @param name 用户名
//     * @param session 会话
//     * @return 聊天室页面
//     */
//    @RequestMapping(value = "/login.do", method=RequestMethod.POST)
//    public ModelAndView login(@RequestParam String name, HttpSession session){
//    	ModelAndView model = new ModelAndView("/chat/room");
//        session.setAttribute("user", name);
//        Message msg = new Message();
//        msg.setUser("系统");
//        msg.setDate(sdf.format(new Date()));
//        msg.setContent(name + "已加入");
//        //通知所有用户有人进入聊天室
//        processMessage(msg);
//        model.addObject("user", name);
//        return model;
//    }
// 
//    /**
//     * 
//     * @作者 Neeke
//     * @作用 读取最新消息
//     * @param session 会话
//     * @return DeferredResult<Message>
//     */
//    @RequestMapping(value = "/getMessages.do", method=RequestMethod.GET)
//    @ResponseBody
//    public DeferredResult<Message> getMessages(HttpSession session){
//        //取出当前登录用户
//        final String user = (String)session.getAttribute("user");
//        //创建DeferredResult<Message>
//        DeferredResult<Message> dr = new DeferredResult<Message>();
//        //若用户不存在则直接返回，否则将其放入用户请求列表中然后返回
//        if(null == user){
//            return dr;
//        }else{
//            //当DeferredResult对客户端响应后将其从列表中移除
//            dr.onCompletion(new Runnable() {
// 
//                @Override
//                public void run() {
//                    // TODO 自动生成的方法存根
//                    chatRequests.remove(user);
//                }
//            });
//            Message message = LEFT_TALK.get(user);
//            if (message != null) {
//            	dr.setResult(message);
//            	LEFT_TALK.remove(user);
//            }
//            chatRequests.put(user, dr);
//            return dr;
//        }
//    }
// 
//    /**
//     * @作者 Neeke
//     * @作用 接收客户端消息
//     * @param session 会话
//     * @param content 消息内容
//     * @return Map<String, String>
//     */
//    @RequestMapping(value = "/setMessage.do", method=RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> setMessage(HttpSession session, @RequestParam String content){
//    	String user = (String)session.getAttribute("user");
//        Message msg = new Message();
//        msg.setContent(user + "：" + content);
//        msg.setDate(sdf.format(new Date()));
//        msg.setUser(user);
//        //发布消息给所有用户
//        processMessage(msg);
//        Map<String, String> map = new HashMap<String, String>(1);
//        map.put("success", "true");
//        return map;
//    }
//    
//    @RequestMapping(value = "/setPrivateMessage.do", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, String> setPrivateMessage(HttpSession session, @RequestParam String content, String toUser){
//    	String user = (String)session.getAttribute("user");
//        Message msg = new Message();
//        msg.setContent(user + " 对你说：" + content);
//        msg.setDate(sdf.format(new Date()));
//        msg.setUser(user);
//        //发布消息给所有用户
//        this.processPrivateMessage(msg, toUser);
//        Map<String, String> map = new HashMap<String, String>(1);
//        map.put("success", "true");
//        return map;
//    }
// 
//    /**
//     * @作者 Neeke
//     * @作用 退出聊天室
//     * @param session 会话
//     * @return Map<String, String>
//     */
//    @RequestMapping(value = "/logout.do", method=RequestMethod.GET)
//    @ResponseBody
//    public Map<String, String> logout(HttpSession session){
// 
//        Message msg = new Message();
//        String user = (String)session.getAttribute("user");
//        msg.setContent("已离开");
//        msg.setDate(sdf.format(new Date()));
//        msg.setUser(user);
//        chatRequests.remove(user);
//        //通知所有用户有人离开聊天室
//        processMessage(msg);
//        Map<String, String> map = new HashMap<String, String>(1);
//        map.put("success", "true");
//        return map;
//    }
// 
//    /**
//     * @作者 Neeke
//     * @作用 将消息信息发布给所有在线用户
//     * @param msg 消息
//     */
//    private void processMessage(Message msg){
//        Set<String> keys = chatRequests.keySet();
//        for(String key : keys){
//            chatRequests.get(key).setResult(msg);
//        }
//    }
//    
//    private void processPrivateMessage(Message msg, final String user) {
//    	DeferredResult<Message> dr = chatRequests.get(user);
//    	if (dr != null) {
//    		dr.setResult(msg);
//    	} else {
//    		LEFT_TALK.put(user, msg);
//    	}
//    }
}