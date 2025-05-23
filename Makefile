setup:
	./gradlew wrapper --gradle-version 8.14.1
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

update-deps:
	./gradlew refreshVersions

.PHONY: build
