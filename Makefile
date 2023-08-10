setup:
	gradle wrapper --gradle-version 8.2.1
	gradle build

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

check-java-deps:
	./gradlew dependencyUpdates -Drevision=release

.PHONY: build
