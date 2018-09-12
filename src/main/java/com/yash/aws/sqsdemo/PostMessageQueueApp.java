package com.yash.aws.sqsdemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class PostMessageQueueApp {
	public static void main(String[] args) {
		
		// Set credentials
    	System.out.println("Setting credentials...");
    	AWSCredentials credentials = new BasicAWSCredentials(
    			"<AWS Access Key>",
    			"<AWS Secret Key");
    	
    	//Create client
    	System.out.println("Creating SQS client...");
    	AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
    			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
    			  .withRegion(Regions.US_EAST_1)
    			  .build();

    	String standardQueueUrl = "<queueUrl>";
    	
    	//Post message to queue
    	System.out.println("Posting message(s) to queue..."); 
    	Map<String, MessageAttributeValue> messageAttributes = new HashMap<String, MessageAttributeValue>();
    	messageAttributes.put("AttributeOne", new MessageAttributeValue()
    	  .withStringValue("Karl was here...")
    	  .withDataType("String"));  
    	 
    	
    	SendMessageRequest sendMessageStandardQueue = new SendMessageRequest()
    	  .withQueueUrl(standardQueueUrl)
    	  .withMessageBody("A simple message.")
    	  .withDelaySeconds(30)
    	  .withMessageAttributes(messageAttributes);
    	 
    	sqsClient.sendMessage(sendMessageStandardQueue);
    	
    	System.out.println("Message sent!");
    	
	}
	
}
