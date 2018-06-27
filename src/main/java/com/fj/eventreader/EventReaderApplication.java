package com.fj.eventreader;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventReaderApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(EventReaderApplication.class, args);

    
    String projectId = Config.ProjectID;
    String subscriptionId = Config.SubscriptionName;

    ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
    // Instantiate an asynchronous message receiver
    MessageReceiver receiver =
        new MessageReceiver() {
          @Override
          public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
            // handle incoming message, then ack/nack the received message
            System.out.println("Data : " + message.getData().toStringUtf8());
            System.out.println("");
            consumer.ack();
          }
        };

    Subscriber subscriber = null;
    try {
      // Create a subscriber for "my-subscription-id" bound to the message receiver
      subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
      subscriber.startAsync();
      // ...
    } finally {

      Thread.sleep(999999999);

      // stop receiving messages
      if (subscriber != null) {
        subscriber.stopAsync();
      }
    }
	}
}
