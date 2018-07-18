package com.alcohol.util;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class Config implements ServerApplicationConfig{

	/**
	 * 通过注解的方式实现
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> arg0) {
		System.out.println("arg..."+arg0.size());
		return arg0;
	}

	/**
	 * 通过接口的方式实现
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
		return null;
	}

}
