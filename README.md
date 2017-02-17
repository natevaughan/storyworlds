# Storyworlds
Create and collaborate on interactive fiction. Anyone can continue the story.

## Running Storyworlds Locally
Clone the repository and make sure to have the following dependencies installed
* Java 8
* Mongodb
* Maven
* Node.js for building web assets
* npm for building web assets
* gulp-cli (you may need this if running on ubuntu)

### Configuring environment
Storyworlds requires two environment variables to be set (by editing ~/.bashrc or ~/.bash_profile): 
     
     MONGODB_URI = <your local mongodb uri, likely mongodb://localhost:27017>
     MONGODB_DATABASE_NAME = storyworlds

### Building the web app
Run the following commands:

    npm install
    gulp
    mvn clean package install
    java -jar target/storyworlds-0.0.1-SNAPSHOT.jar console
    
The application will start in console mode and take you through a series of steps to setup a Spring Security user.

## Web application
Web application mode is a simple Angular.js app that will allow you to select storyworld and interact with it in a simple console-like web environment.

## Standalone application
The standalone command-line version of Storyworlds does not allow collaboration but allows creation and editing of storyworlds in a classic text-based console game feel. 

# Commands

The primary game commands can be listed at any time by typing `help`.