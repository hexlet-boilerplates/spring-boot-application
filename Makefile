setup:
	./gradlew wrapper --gradle-version 9.2.1
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

test:
	./gradlew test

lint:
	./gradlew spotlessApply

.PHONY: setup app clean build dev reload-classes install test lint
