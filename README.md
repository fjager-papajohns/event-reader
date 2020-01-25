# event-reader
Spring Boot app that reads PubSub events from GCP and prints them to your local terminal.  Please refer to https://spring.io/guides/gs/messaging-gcp-pubsub/

### Usage
1. Create a GCP Subscription for the Topic you want to read events from.
2. Setup the right permissions by any of options below.
    1. Create a GCP Service Account that has permissions to read from that Subscription, and download the Key file (.json).  Then either...
    2. Run the command `gcloud auth application-default login` to use your personal account's permissions.
4. Edit `src/main/resources/application.properties` and enter your GCP ProjectID, credential file location, and subscription name values.  If using your user credentials and not a service account you should leave the credential file property commented out.
    1. You can use the environment variable "GOOGLE_CLOUD_PROJECT" and leave the property `spring.cloud.gcp.project-id` commented out.
    2. You can use the environment variable "GOOGLE_APPLICATION_CREDENTIALS" and leave the property `spring.cloud.gcp.credentials.location` commented out.  This only works with service accounts.
5. Compile and run the app. Once it's running, it will continuously print incoming events to your console until you quit the app.

**Note:** If there are a lot of existing messages already in the subscription, it will play 'catch-up' for a minute and fetch all of those first, before it goes into real-time mode.