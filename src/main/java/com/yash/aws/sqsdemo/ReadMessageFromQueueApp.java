package com.yash.aws.sqsdemo;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class ReadMessageFromQueueApp {

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

		//Reading message from queue
    	System.out.println("Reading message(s) from queue...");
    	ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(standardQueueUrl)
    			  .withWaitTimeSeconds(10)
    			  .withMaxNumberOfMessages(10);
    	
    	List<Message> sqsMessages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
    	System.out.println("Printing messages: ");
    	
    	
    	for(Message msg: sqsMessages) {
    		System.out.println(msg.getBody());
    	}
    	System.out.println();
    	
    	//Delete message from the queue 
    	System.out.println("Deleteing message(s) from queue...");
    	for(Message msg: sqsMessages) {
    		sqsClient.deleteMessage(new DeleteMessageRequest()
      			  .withQueueUrl(standardQueueUrl)
      			  .withReceiptHandle(msg.getReceiptHandle()));
    	}
    
    	System.out.println("Messages deleted successfully!");
    	
	}

}
