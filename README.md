# Team Project

Installation instructions:

git clone https://gitlab.cim.rhul.ac.uk/TeamProject10/PROJECT.git

cd PROJECT

follow the docker instructions in "docker instructions.rtf"

The project can be run directly in maven or through eclipse run configurations:

maven:

mvn clean compile javafx:run

eclipse:

import the project folder into eclipse
create a new run configuration, 
set the workspace to the project folder
set the goal as clean compile javafx:run

if you are running in eclipse you will have to check the classpath is set up correctly for the postgres jar
by default it's set to look in usr/share/java - you will need to change this as below if you are using eclipse
right click on the project, go to Build Path -> configure build path
click on the current postgres jar listing, click remove,
click classpath, click add external jars, navigate to the top level of the project folder, select the postgres jar there.

USEAGE INSTRUCTIONS

on running:
when prompted enter your database username and password (see "docker instructions.rtf")
on a first time start up make sure you enter "Y" - you should see sql statements outputted to the console. 
THE SYSTEM WILL NOT WORK IF YOU DO NOT TYPE IN Y ON THE FIRST SETUP (MUST BE CAPITAL).
After completing first time setup the program will exit. Launch it again, and select N instead when prompted.
The system is now ready for use.
default staff login is:
id: 123456789
password: pass1


