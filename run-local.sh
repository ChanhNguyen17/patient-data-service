#!/bin/bash

export SPRING_DATASOURCE_USERNAME='postgres'
export SPRING_DATASOURCE_PASSWORD='postgres'
export SPRING_DATASOURCE_URL='jdbc:postgresql://localhost:6255/postgres'

./gradlew bootRun
