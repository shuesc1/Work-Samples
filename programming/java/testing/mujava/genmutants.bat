REM Be sure to specify the location of the tools.jar file on your system using the JAVA_TOOLS variable below

SET JAVA_TOOLS="C:\Program Files\Java\JDK\lib\tools.jar"

SET CLASSNAME=mujava.gui.GenMutantsMain

java -cp lib/mujava.jar;lib/openjava.jar;%JAVA_TOOLS% %CLASSNAME%


