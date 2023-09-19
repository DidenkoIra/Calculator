all: build install

build:
	@./mvnw clean -Dmaven.clean.failOnError=false compile assembly:single -DskipTests=true
	@make -C src/cppSmartCalc/

install:
	@cp target/SmartCalc3.jar /usr/bin
	@cp jaropen.desktop /usr/share/applications/
	@update-desktop-database /usr/share/applications/

clean:
	@./mvnw clean -Dmaven.clean.failOnError=false
	@make clean -C src/cppSmartCalc/
