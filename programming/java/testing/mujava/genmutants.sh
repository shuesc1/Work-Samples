#!/bin/sh

# Be sure to specify the location of the tools.jar file on your system using the JAVA_TOOLS variable below

# Use this if you are running on Linux; you may need to modify it depending on which version of Java you have:
# JAVA_TOOLS=/usr/java/jdk1.8.0_112/lib/tools.jar

# Use this if you are running on a Mac; you may need to modify it depending on which version of Java you have:
JAVA_TOOLS=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/lib/tools.jar

# This checks that the file exists:
if [ -f "$JAVA_TOOLS" ]
then
    CLASSNAME=mujava.gui.GenMutantsMain
    java -cp lib/mujava.jar:lib/openjava.jar:$JAVA_TOOLS $CLASSNAME
else
    echo "Could not find file $JAVA_TOOLS ; please check that JAVA_TOOLS is set correctly in the script"
fi

