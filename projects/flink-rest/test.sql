CREATE CATALOG iceberg_rest WITH (
  'type' = 'iceberg',
  'catalog-type' = 'rest',
  'uri' = 'http://iceberg-rest:8181',
  'warehouse' = 's3a://warehouse',
  's3.endpoint' = 'http://minio:9000',
  's3.access-key' = 'minioadmin',
  's3.secret-key' = 'minioadmin',
  'fs.s3a.impl' = 'org.apache.hadoop.fs.s3a.S3AFileSystem',
  's3.region' = 'us-east-1',
  's3.path-style-access' = 'true'
);

USE CATALOG iceberg_rest;

CREATE DATABASE IF NOT EXISTS demo;

USE demo;

CREATE TABLE customers (
                           id BIGINT,
                           name STRING,
                           email STRING
) PARTITIONED BY (email);

INSERT INTO customers VALUES
                          (1, 'Alice Smith', 'alice@example.com'),
                          (2, 'Bob Johnson', 'bob@example.com'),
                          (3, 'Carol Adams', 'carol@example.com');

SELECT * FROM customers$snapshots;                        1751868122000
SELECT * FROM customers /*+ OPTIONS ('as-of-timestamp' = '1751867400000') */;
SELECT * FROM customers /*+ OPTIONS ('snapshot-id' = '8703282990995995474') */;
SELECT UNIX_TIMESTAMP('2025-07-07 05:50:00.000000 ', 'yyyy-MM-dd HH:mm:ss.SSSSSS') * 1000;


