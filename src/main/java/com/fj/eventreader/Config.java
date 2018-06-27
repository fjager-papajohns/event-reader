package com.fj.eventreader;

public class Config {
    // GCP Project ID where the topic is (make sure to specify the project's ID, not the name)
    public static String ProjectID = "my-project-id";
    
    // The name of the subscription to use to read the events (not including the 'path')
    public static String SubscriptionName = "test-subscription";
}
