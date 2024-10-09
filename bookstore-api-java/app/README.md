# GET STARTED

- Run the database first -> docker run -e POSTGRES_PASSWORD=password -p 5432:5432 postgres
  - create a new database
  - load the dataset -> https://www.kaggle.com/datasets/drahulsingh/best-selling-books?select=best-selling-books.csv
- launch API
  - create the controllers
  - create the repositories
  - link controllers to repositories
  - develop unit test alongside
- launch front-end
- create the docker-compose to get everything managed

# TODO 2024/10/10
- Dockerfile java
  - check how to compile with custom output name for .jar
- test docker-compose.yaml
- make custom Dockerfile for database
  - create bookstore schema
  - load Kaggle dataset with API -> 
- commit online