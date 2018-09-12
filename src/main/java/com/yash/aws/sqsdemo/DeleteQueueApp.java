package com.yash.aws.sqsdemo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class DeleteQueueApp {
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
    	
    	//Delete Queue
    	sqsClient.deleteQueue("karlroth-queue");
    	
    	System.out.println("Queue deleted successfully!");
	}

}
