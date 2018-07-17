package com.alcohol.util;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoWebsocket {
	
	
	private static List<Session> lecho = new ArrayList<Session>();
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private  Session session = null;
	//  静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	/**
	 * 打开连接
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session){
		this.session= session;
		//存入会话中
		lecho.add(session);
		onlineCount = onlineCount+1;
		System.out.println("当前人数："+onlineCount);
		System.out.println("sessionId:" + session.getId() +"已连接");
		
	}


	/**
	 * 关闭连接
	 */
	@OnClose
	public void onClose(){
		lecho.remove(this);
		onlineCount = onlineCount - 1;  // 在线人数减1
		System.out.println("有一连接关闭！当前在线人数为" + onlineCount);
		
	}
	
	/**
	 * 
	 * @param session   可选的参数
	 * @param msg   客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(Session session,String msg){
		System.out.println("客户端说："+msg);
		//向每个用户发送消息
		for (Session s : lecho) {
			s.getAsyncRemote().sendText(msg);
		}
	}
	
}
