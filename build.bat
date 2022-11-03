::@echo offq
javac Game.java View.java Controller.java Model.java Pipe.java Mario.java Json.java
if %errorlevel% neq 0 (
e) else (
	echo Compiled correctly!  Running Game...
	java Game	
)