build-teams:
	cd ./teams-api && ./gradlew clean build -x test

build-issues:
	cd ./issues-api && ./gradlew clean build -x test

build-boards:
	cd ./boards-api && ./gradlew clean build -x test

build-all:
	make build-teams
	make build-issues
	make build-boards

up:
	docker-compose -f ./docker/docker-compose.yaml up

build-docker:
	docker-compose -f ./docker/docker-compose.yaml build