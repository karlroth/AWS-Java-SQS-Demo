package com.yash.aws.sqsdemo;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class ListQueueApp {

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
    	
		
		System.out.println("Listing available queues: ");
		List<String> queueUrls = sqsClient.listQueues().getQueueUrls();
		for (String queueUrl : queueUrls) {
			System.out.println(queueUrl);
		}
		System.out.println();
		
	}
}
