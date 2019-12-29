
To compile and use in command line:
- cd C:\YOUR_DIR\Connect4\src
- javac *.java
- java InterfaceCustom
AI type and search depth is changeable in Interface Custom.



To compile and use in command line with jar file:
- cd C:\YOUR_DIR\Connect4\src
- javac *.java
- cd C:\YOUR_DIR\Connect4\lib
- java -jar connect_4_coordinator.jar e0 ../src             (One engine competing against itself)
or 
- java -jar connect_4_coordinator.jar e0 ../src1 e1 ../src2     (Two different engines competing)

Note: Directory name can't have spaces for e0.
