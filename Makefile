setup:
	./gradlew wrapper --gradle-version 9.8.2
	./gradlew build

app:
	./gradlew bootRun --args='--spring.profiles.active=dev'

clean:
	./gradlew clean

build:
	./gradlew clean build

dev: app

reload-classes:
	./gradlew -t classes

install:
	./gradlew installDist
	mise exec -- hk install --mise

test:
	./gradlew test

lint:
	./gradlew spotlessCheck compileJava compileTestJava
	rumdl check .
	typos
	jactionlint

lint-fix:
	./gradlew spotlessApply

.PHONY: setup app clean build dev reload-classes install test lint lint-fix
