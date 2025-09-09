mkdir -p resources

if [ ! -f resources/hadoop-3.4.1.tar.gz ]; then
  curl -fL -o resources/hadoop-3.4.1.tar.gz \
    https://archive.apache.org/dist/hadoop/common/hadoop-3.4.1/hadoop-3.4.1.tar.gz
fi

if [ ! -f resources/iceberg-flink-runtime-1.20-1.9.1.jar ]; then
  curl -fL -o resources/iceberg-flink-runtime-1.20-1.9.1.jar \
    https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-flink-runtime-1.20/1.9.1/iceberg-flink-runtime-1.20-1.9.1.jar
fi

# Download hadoop-aws JAR (matches your Hadoop version: 3.4.1)
if [ ! -f resources/hadoop-aws-3.4.1.jar ]; then
  curl -fL -o resources/hadoop-aws-3.4.1.jar https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-aws/3.4.1/hadoop-aws-3.4.1.jar
fi

# Download aws-java-sdk-bundle JAR (compatible with Hadoop 3.4.1)
if [ ! -f resources/aws-java-sdk-bundle-1.12.526.jar ]; then
  curl -fL -o resources/aws-java-sdk-bundle-1.12.526.jar https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-bundle/1.12.526/aws-java-sdk-bundle-1.12.526.jar
fi
