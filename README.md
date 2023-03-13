# Recipe API Service
Recipe API is a simple Kotlin Spring Boot based API that using MySQL as the Database

## Swagger UI
Start the application and go to the url => 
http://127.0.0.1:8080/swagger-ui/index.html for swagger documentation.

## Database
The simple scheme for the data used 
```roomsql
CREATE DATABASE `yourrecipes`

-- yourrecipes.recipe definition

CREATE TABLE `recipe` (
  `recipeId` int(11) NOT NULL AUTO_INCREMENT,
  `recipeName` varchar(100) NOT NULL,
  `recipeInstructions` text NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT current_timestamp(),
  `recipeImage` varchar(200) NOT NULL,
  PRIMARY KEY (`recipeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;


-- yourrecipes.ingredients definition

CREATE TABLE `ingredients` (
  `ingredientsId` int(11) NOT NULL AUTO_INCREMENT,
  `recipeId` int(11) NOT NULL,
  `ingredientsDetail` text NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`ingredientsId`),
  KEY `recipeId` (`recipeId`),
  CONSTRAINT `ingredients_ibfk_1` FOREIGN KEY (`recipeId`) REFERENCES `recipe` (`recipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;


-- yourrecipes.instructions definition

CREATE TABLE `instructions` (
  `instructionsId` int(11) NOT NULL AUTO_INCREMENT,
  `instructionDetails` text DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `recipeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`instructionsId`),
  KEY `instructions_FK` (`recipeId`),
  CONSTRAINT `instructions_FK` FOREIGN KEY (`recipeId`) REFERENCES `recipe` (`recipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

```
The frontend of the application located at [repo](https://github.com/paulouskunda/RecipeApp.git)

You can check out the blog post for this API on medium

Happy Coding...
