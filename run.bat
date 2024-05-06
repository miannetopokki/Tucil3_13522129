@echo off
javac -d bin src\*.java src\MapBuilder\*.java src\FileParser\*.java src\UCS\*.java src\Graf\*.java src\Util\*.java src\Simpul\*.java src\AStar\*.java src\GBFS\*.java
cd bin
java Main
