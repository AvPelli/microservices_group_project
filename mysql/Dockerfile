
FROM mysql:5.7
# Copy our custom SQL file to /docker-entrypoint-initdb.d folder
ENV MYSQL_ROOT_PASSWORD=root
COPY create_dbs.sql /docker-entrypoint-initdb.d/create_dbs.sql