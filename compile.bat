@REM Java needs to be install in order to run and compile the game

@REM Windows command prompt type: compile.bat TicTacToe
@REM Power shell type: .\compile TicTacToe

@REM %1 is kinda like a variable and will be replaced by what comes after compile
@REM in this example it would be replaced by TicTacToe automatically when the script runs.
@REM java %1    -will run the java program
@REM @REM will make the comments not be printed out when running the script

javac %1.java

@REM will create needed manifest.txt file with "Main-Class" directive for jar.exe
echo Main-Class: TicTacToe >manifest.txt

@REM runs JDK's jare.exe
jar cvfm TicTacToe.jar manifest.txt *.class

@REM This line is only for GUI applications
@REM if you have a command line Input /Output, type use java -jar  filename.jar
TicTacToe.jar