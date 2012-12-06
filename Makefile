parser: src/forkbomb/frontend/parser/Lure.jj
	javacc $^

src/forkbomb/frontend/parser/Lure.jj: Lure.jjt
	jjtree Lure.jjt

build: clean parser
	mkdir bin
	find src -name *.java | xargs javac -d bin

clean:
	rm -rf bin


runall: build
	script/runall

sparktest: spark/src/sparktest/SparkTest.java
	javac -cp 'lib/*' -d bin $^

sparkrpnb: spark/src/rpn/RPNCalc.java
	javac -cp 'lib/*' -d bin $^

sparkrpn: sparkrpnb
	java -cp 'lib/*':bin rpn.RPNCalc


.PHONY: parser clean build run runall clobber sparktest
