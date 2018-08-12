package com.zl.lqian.utils;

/**
 *
 * message body
 */
public class RabbitMetaMessage {
	String exchange;
	String routingKey;
	boolean autoTrigger;
	boolean returnCallback;
	Object payload;
	
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	public boolean isReturnCallback() {
		return returnCallback;
	}
	public void setReturnCallback(boolean returnCallback) {
		this.returnCallback = returnCallback;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	public boolean isAutoTrigger() {
		return autoTrigger;
	}
	public void setAutoTrigger(boolean autoTrigger) {
		this.autoTrigger = autoTrigger;
	}
	
	
	

}
