![alt-text](http://nancyfriedman.typepad.com/.a/6a00d8341c4f9453ef01a3fd2857f5970b-pi)

# Project Description

Meh.com is a one-deal-a-day site which exposes a basic RESTful API for retrieving information on today's current deal.

Your task is to create an Android app which ties into this API and displays today's deal to the user. This project will require networking, persistent storage, UI, and interaction with several Android APIs.


### Requirements

* General requirements:
 * The app should be written in kotlin.
 * Commit often to git, as this will help track changes and evaluate your progress over time.

* Application Requirements:
 1. Show Today's deal
 2. Show past deals that the app has previously downloaded
 3. Alert the user when a new deal is posted

There are no strict UI requirements or specific designs which need to be implemented. You are free to implement UI and navigation as you see fit, but it should be intuitive (and not ugly).
[This ugly, rough mock-up](https://www.fluidui.com/editor/live/preview/p_Sn2Cv14MpoOr9ErbnmTKtvzJaKQh7HOl.1436286731938) is one example of a possible navigation structure (use the arrow keys on your keyboard to navigate between pages).

#### 1. Show Today's Deal
When showing today's deal, include as much info about the deal as possible, including:
 * Product name
 * Product photos
 * Product price
 * A button when opens the deal in a browser
 * Story
 * Specs
 * Features
 * Apply the deal's theme (background color and text/accent color)
 * (Optional/bonus points) Chart of the daily poll
 * (Optional/bonus points) Daily video

To retrieve today's deal information, you'll need to use the [Meh API](https://meh.com/forum/topics/meh-api) which requires an API key. Rather than having you register your own account, you can use this API key: `ZXzByssyPuglD85cLS1WJrwKda4ik9s0`. I recommend using Retrofit to connect to the API and pull the info.

You should show some sort of loading indicator until the data is loaded. If there is a network issue or other error, display a user-friendly message in place of the above information.

Once you have the deal information, persist it to disk using Realm. You will use the saved information later for #2.

#### 2. Show past deals
On a separate page, show a list of past deals the app has saved. There is no way to retrieve past information from the Meh API, so you are not expected to display deals that the app has not previously downloaded. Each list item show show a subset of the deal information, including at least the first product image, product name, and product price. Tapping on any item should open a full deal page similar or identical to what would be shown for requirement #1.

#### 3. Alert the user when a new deal is posted
Meh posts new deals shortly after midnight, Eastern Time (10 pm Mountain Time). The servers are sometimes busy and it doesn't always happen exactly on the hour, so in order to alert users when a new deal is posted you will need to poll the API once a minute (up to 15 minutes) until a new deal appears, starting on the hour. To schedule this, you can look into either Android's AlarmManager or sync adapters. AlarmManager is the easier option for beginners. If you choose to implement this requirement, provide a switch somewhere in the app where the user can disable it. To alert the user when a new deal appears, show a system notification and vibrate the phone.

---

### Libraries you'll want to use
The following are libraries we commonly use in Izeni's projects. You'll want to use them in this project in order to familiarize yourself with them:
 * [Kotlin] (http://kotlinlang.org): Essentially a better Java. Learn how to set is up for Android [here.](https://kotlinlang.org/docs/tutorials/kotlin-android.html)
 * [Realm](https://realm.io/): A NoSQL database with transparent object mapping. [Documentation here](https://realm.io/docs/java/latest/)
 * [Retrofit](http://square.github.io/retrofit/): A RESTful Android client, simplifies networking and uses Google's GSON to provide free deserialization.
 * [Picasso](http://square.github.io/picasso/): Helps with image download and caching. And by 'helps' I mean it basically does everything for you.

---

## Notes:
 * Keep in mind that this is a coding challenge. While I can lend help when you get stuck, it is to your benefit to spend time working through any issues you encounter.
 * You can use any libraries you want outside of the list above.  Using libraries is welcome and encouraged here and we like to see if you understand when and when not to use libraries to solve problems.
 * The response from the Meh API may not exactly match what is documented on their website. This is a very common occurrence when working on client projects and you can expect to run into issues like on a daily basis at Izeni.
