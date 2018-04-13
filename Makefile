all: wolf3d

wolf3d: *.java
	javac *.java

run:
	java Wolf

clean:
	rm -f *.class *~
