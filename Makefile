SRC_DIR = src
BIN_DIR = bin

JC = "javac"
JVM = "java"

client_classes = src/TCPServer.java\
				 src/TCPClient.java\

clean:
	rm -rf "bin"

program:
	javac -d ${BIN_DIR} ${client_classes}
	ls ${BIN_DIR} 
#run make client FILE=file.pdf
client:
	ifeq ($(FILE),)
		$(error FILE is not set. please run command: make client FILE=your_file_name)
	endif
	${JVM} -classpath ${BIN_DIR} TCPClient ${FILE}

server:
	${JVM} -classpath ${BIN_DIR} TCPServer


ifeq ($(strip $(MAKECMDGOALS)),)
    $(error Usage: make <clean, program, client, server>)
endif

