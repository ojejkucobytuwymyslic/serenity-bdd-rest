# QA task

This is a simple QA task project using Serenity with Cucumber and REST Assured

## Requirements:
Java 8 and Maven 3.8.4 or higher. Depending on preferred IDE plugins for Lombok and Cucumber.

## Installation:

1.After cloning repo please run in main folder:

`mvn clean install`

2.Happy testing:)

## Run all tests:

`mvn clean verify`

## Run tests using tags:

`mvn clean verify -Dcucumber.filter.tags="@Performance"`

## Additional report after finished tests:

`target/site/serenity/index.html`
