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


.PHONY: parser clean build run runall clobber
