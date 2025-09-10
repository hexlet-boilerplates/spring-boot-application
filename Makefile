setup:
	./gradlew wrapper --gradle-version 9.0.0
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

update-deps:
	./gradlew refreshVersions

.PHONY: build
