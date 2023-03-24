# Team Project

Installation instructions:

git clone https://gitlab.cim.rhul.ac.uk/TeamProject10/PROJECT.git

cd PROJECT

follow the docker instructions in "docker instructions.rtf"

Can be run directly in maven or through eclipse run configurations:

maven:

mvn clean compile javafx:run

eclipse;

import the project folder into eclipse
create a new run configuration, 
set the workspace to the project folder
set the goal as clean compile javafx:run

if you are running in eclipse you will have to check the classpath is set up correctly for the postgres jar
by default it's set to look in usr/share/java - as that's where it's stored on nomachine
but if you're not on nomachine you will have to change it to the provided jar (comes cloned with the rest of the project):
right click on the project, go to Build Path -> configure build path
click on the current postgres jar listing, click remove,
click classpath, click add external jars, navigate to the top level of the project folder, select the postgres jar there.

on running:
when prompted enter your database username and password (see "docker instructions.rtf")
on a first time start up make sure you enter "Y" - you should see sql statements outputted to the console. 
THE SYSTEM WILL NOT WORK IF YOU DO NOT TYPE IN Y (MUST BE CAPITAL).
default staff login is:
id: 123456789
password: pass1
there is an odd glitch where in some systems the same instance of the program ran with first time 
setup of the project fails on a customer submitting an order.
if this occurs just run the project again without first time setup and it will work.



