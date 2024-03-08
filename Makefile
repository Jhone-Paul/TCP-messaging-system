SRC_DIR = src
BIN_DIR = bin

JC = "javac"
JVM = "java"

client_classes = src/server.java\
				 src/client.java\

clean:
	rm -rf "bin"

program:
	javac -d ${BIN_DIR} ${client_classes}
	ls ${BIN_DIR} 

client:
	${JVM} -classpath ${BIN_DIR} client $(IP)

server:
	${JVM} -classpath ${BIN_DIR} server


ifeq ($(strip $(MAKECMDGOALS)),)
    $(error Usage: make <clean, program, client, server>)
endif

