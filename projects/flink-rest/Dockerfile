FROM flink:1.20-scala_2.12-java17

USER flink

# Iceberg + Flink runtime
RUN echo "-> Install JARs: Dependencies for Iceberg" && \
    curl -fL -o ./lib/iceberg-flink-runtime-1.20-1.9.1.jar \
    https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-flink-runtime-1.20/1.9.1/iceberg-flink-runtime-1.20-1.9.1.jar


RUN echo "Add Flink S3 Plugin" && \
    mkdir /opt/flink/plugins/s3-fs-hadoop && \
    cp /opt/flink/opt/flink-s3-fs-hadoop-*.jar /opt/flink/plugins/s3-fs-hadoop/

RUN echo "-> Install JARs: AWS / Hadoop S3" && \
    curl -fL -o /opt/flink/lib/hadoop-aws-3.4.1.jar \
      https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-aws/3.4.1/hadoop-aws-3.4.1.jar && \
    curl -fL -o /opt/flink/lib/awscore-2.31.76.jar \
      https://repo1.maven.org/maven2/software/amazon/awssdk/awscore/2.31.76/awscore-2.31.76.jar && \
    curl -fL -o /opt/flink/lib/sdk-core-2.31.76.jar \
      https://repo1.maven.org/maven2/software/amazon/awssdk/sdk-core/2.31.76/sdk-core-2.31.76.jar && \
    curl -fL -o /opt/flink/lib/s3-2.31.76.jar \
      https://repo1.maven.org/maven2/software/amazon/awssdk/s3/2.31.76/s3-2.31.76.jar

# Hadoop dependencies directly to lib
RUN curl -fL -o /opt/flink/lib/commons-configuration2-2.1.1.jar \
      https://repo1.maven.org/maven2/org/apache/commons/commons-configuration2/2.1.1/commons-configuration2-2.1.1.jar && \
    curl -fL -o /opt/flink/lib/commons-logging-1.1.3.jar \
      https://repo1.maven.org/maven2/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar && \
    curl -fL -o /opt/flink/lib/hadoop-auth-3.4.1.jar \
      https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-auth/3.4.1/hadoop-auth-3.4.1.jar && \
     curl -fL -o /opt/flink/lib/hadoop-common-3.4.1.jar \
      https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-common/3.4.1/hadoop-common-3.4.1.jar && \
    curl -fL -o /opt/flink/lib/hadoop-shaded-guava-1.4.0.jar \
      https://repo1.maven.org/maven2/org/apache/hadoop/thirdparty/hadoop-shaded-guava/1.4.0/hadoop-shaded-guava-1.4.0.jar && \
     curl -fL -o /opt/flink/lib/stax2-api-4.2.2.jar \
      https://repo1.maven.org/maven2/org/codehaus/woodstox/stax2-api/4.2.2/stax2-api-4.2.2.jar && \
     curl -fL -o /opt/flink/lib/woodstox-core-5.3.0.jar \
      https://repo1.maven.org/maven2/com/fasterxml/woodstox/woodstox-core/7.1.1/woodstox-core-7.1.1.jar && \
     curl -fL -o /opt/flink/lib/hadoop-hdfs-client-3.4.1.jar \
       https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-hdfs-client/3.4.1/hadoop-hdfs-client-3.4.1.jar && \
     curl -fL -o /opt/flink/lib/hadoop-mapreduce-client-core-3.4.1.jar \
      https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-mapreduce-client-core/3.4.1/hadoop-mapreduce-client-core-3.4.1.jar && \
     curl -fL -o /opt/flink/lib/hadoop-hdfs-3.4.1.jar \
       https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-hdfs/3.4.1/hadoop-hdfs-3.4.1.jar
