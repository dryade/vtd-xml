javac com\ximpleware\*.java
javac com\ximpleware\parser\*.java

jar -cvf vtd-xml_light.jar com\ximpleware\*.class com\ximpleware\parser\*.class

del/S *.class 
