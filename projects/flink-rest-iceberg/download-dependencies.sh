mkdir -p shared-lib

curl -fL -o shared-lib/iceberg-flink-runtime-1.20-1.9.1.jar \
  https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-flink-runtime-1.20/1.9.1/iceberg-flink-runtime-1.20-1.9.1.jar

curl -fL -o shared-lib/flink-s3-fs-hadoop-1.20.1.jar \
  https://repo1.maven.org/maven2/org/apache/flink/flink-s3-fs-hadoop/1.20.1/flink-s3-fs-hadoop-1.20.1.jar
