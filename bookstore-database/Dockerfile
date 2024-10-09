FROM postgres:17.2 
ENV POSTGRES_DB=bookstore
COPY ./table_init.sql /docker-entrypoint-initdb.d/
