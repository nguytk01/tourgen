[![Build Status](https://travis-ci.org/nguytk01/tourgen.svg?branch=master)](https://travis-ci.org/nguytk01/tourgen)

# tourgen
Indiana High School Cross Country Tournament Generator.


## How to build without maven ##
### Requirements: ###
* Git client
* a local JDK (7 or above preferably) installation.
### Windows ###
1. Clone this repository to your computer
2. Open Windows Command Line (`cmd.exe`)
3. Change your current directory to the directory of the local repository created in step 1.
4. If you already have JAVA_HOME environment variable pointing to a JDK folder, then please skip this step. Otherwise, please run `maven-env-cmd.bat` to set the environment variable to the path to your JDK installation.
5. Run `.\mvnw.cmd compile`.
6. Run `.\mvnw.cmd package`.
7. The executable jar is generated in the tourgen\view\target folder.

## How to import this project into Eclipse IDE ##
1. Please make sure you start Eclipse with an empty workspace.
2. `File` > `Import` > Existing Projects into Workspace
3. Set the `Root Directory` path to the `tourgen` folder, the one containing the README.md file.
4. `Next` > `Finish`

## How to run checkstyle ##
* Follow the following link to install Checkstyle in Eclipse http://checkstyle.org/eclipse-cs/#!/
* Follow the following link to activate Checkstyle in Eclipse http://checkstyle.org/eclipse-cs/#!/project-setup
* Follow the following link to convert tabs to spaces in Eclipse https://stackoverflow.com/questions/407929/how-do-i-change-eclipse-to-use-spaces-instead-of-tabs
** Menu `Window` > `Preferences` > `Java` > `Code Style`  > `Formatter`.
** Create a new style based on Eclipse default
** In `General Settings`, change `Tab Policy` to `Spaces Only`
** `Tab size` to be `4`
** `Indentation size` to `2`
