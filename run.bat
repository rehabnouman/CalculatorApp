@echo off
echo Starting Scientific Calculator...
javac --module-path "C:\Users\PMLS\Downloads\javafx-sdk-21.0.5\lib" --add-modules javafx.controls -d bin src/*.java
java --module-path "C:\Users\PMLS\Downloads\javafx-sdk-21.0.5\lib" --add-modules javafx.controls -cp bin CalculatorApp
pause
