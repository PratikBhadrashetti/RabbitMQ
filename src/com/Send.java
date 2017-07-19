package com;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	 private final static String QUEUE_NAME = "hello";

	  public static void main(String[] argv)
	      throws java.io.IOException, TimeoutException {
	   
		  ConnectionFactory factory = new ConnectionFactory();
		  factory.setHost("localhost");
		  Connection connection = factory.newConnection();
		  Channel channel = connection.createChannel();
		  
		  
		  String message = "Hello World!";
		  for(int i=0;i<10;i++)
		  {
			  System.out.println(i);
			  channel.queueDeclare(QUEUE_NAME+i, false, false, false, null);
			  channel.basicPublish("", QUEUE_NAME+i, null, message.getBytes());
		  }
		 
		  System.out.println(" [x] Sent '" + message + "'");
		  
		  channel.close();
		  connection.close();
	  }
}
