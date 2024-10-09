# GET STARTED

- Run the database first -> docker run -e POSTGRES_PASSWORD=password -p 5432:5432 postgres
  - create a new database
  - load the dataset -> https://www.kaggle.com/datasets/drahulsingh/best-selling-books?select=best-selling-books.csv
- launch API
- launch front-end
- create the docker-compose eventually to run in production mode

# TODO 2025/01/07
- Dockerfile java
  - check how to compile with custom output name for .jar
- test docker-compose.yaml
- make custom Dockerfile for database
  - create bookstore schema
- API
  - CRUD customer controllers
  - Link physical book to book