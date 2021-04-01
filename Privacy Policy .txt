ShareBy
This is an Android based, social media App. Its main purpose is to bring neighborhood activities and chats closer by providing an online medium. Create your neighborhood group via Google Maps Location and invite your neighbors, friends, and families. Ask questions, borrow something, help others, and much more. Be friendly to others and keep growing. Inbuilt Chats to discuss among each other as well as among the whole group.

The whole Project is based on Firebase Cloud Service. User data is NOT stored locally on the device, so Internet connection is necessary to run the App.

More features coming soon...

Screenshot
  
 
 

Quick explanation of project directory :
firebase function : It contains single file named index.js having cloud function.
scrnshots: Sample screenshot from mobile.
Prerequisites
Firebase project with Realtime database for android having package name "com.zero.shareby".
google-services.json: during firebase project creation, you will get the google-services.json file, download and save this file.
Android SDK v28
Android Support Repository
Open and Run Project
For Android App:

open android studio, select File -> Import -> "Existing Projects into your workspace".
Go to the path where you cloned the Repo: (repoFolder)\code
paste the google-services.json to "app" folder.
rebuild the project and run.
For cloud function:

initialize Firebase SDK for Cloud Functions as explained here,
open index.js and paste the code from "(repoFolder)\cloud function\index.js".
deploy the cloud function.
Built With
Language: java for android, javascript for cloud function.
Firebase : Realtime database, Firebase Auth, Firebase storage, Firebase config, Functions
Author
Jay Patel
Support
Please feel free to submit issues with any bugs or other unforeseen issues you experience.
