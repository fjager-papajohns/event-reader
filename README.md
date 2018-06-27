# event-streamer
Spring Boot app that reads PubSub events from GCP and prints them to your local terminal.

### Usage
1. Create a GCP Subscription for the Topic you want to read events from.
2. Create a GCP Service Account that has permissions to read from that Subscription, and download the Key file (.json).
3. Set a local environment variable on your machine that points to the Key file, like this: `export GOOGLE_APPLICATION_CREDENTIALS=~/Downloads/key_file.json` (replacing 'key_file' with your actual filename). _Note: Windows users will have to reboot after doing this for it to take effect._
4. Edit `src/main/java/com/fj/eventreader/Config.java` and enter your GCP ProjectID and Subscription name values.
5. Compile and run the app. Once it's running, it will continuously print incoming events to your console until you quit the app.

**Note:** If there are a lot of existing messages already in the subscription, it will play 'catch-up' for a minute and fetch all of those first, before it goes into real-time mode.