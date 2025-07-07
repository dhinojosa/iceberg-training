FROM flink:1.20-scala_2.12-java17

USER root

# Copy user-specific Flink configuration
COPY conf/core-site.xml /opt/flink/conf/
COPY conf/config.yaml /opt/conf/

# Copy the pre-downloaded Hadoop to tmp
COPY resources/hadoop-3.4.1.tar.gz /tmp/hadoop.tar.gz

# Copy AWS resources and hadoop aws
COPY resources/hadoop-aws-3.4.1.jar /opt/flink/lib/
COPY resources/aws-java-sdk-bundle-1.12.526.jar /opt/flink/lib/

# Extract Hadoop and copy necessary files
RUN tar xzvf /tmp/hadoop.tar.gz -C /opt && \
    mv /opt/hadoop-3.4.1 /opt/hadoop && \
    cp /opt/hadoop/share/hadoop/common/*.jar /opt/flink/lib/ && \
    cp /opt/hadoop/share/hadoop/common/lib/*.jar /opt/flink/lib/ && \
    cp /opt/hadoop/share/hadoop/hdfs/*.jar /opt/flink/lib/ && \
    cp /opt/hadoop/share/hadoop/mapreduce/*.jar /opt/flink/lib/ && \
    cp /opt/hadoop/share/hadoop/yarn/*.jar /opt/flink/lib/ && \
    cp /opt/hadoop/etc/hadoop/hdfs-site.xml /opt/flink/conf/ && \
    rm /tmp/hadoop.tar.gz

# Add Iceberg runtime JAR to Flink's lib directory
COPY resources/iceberg-flink-runtime-1.20-1.9.1.jar /opt/flink/lib/

# Set up Flink S3 Hadoop plugin
RUN mkdir -p /opt/flink/plugins/s3-fs-hadoop && \
    cp /opt/flink/opt/flink-s3-fs-hadoop-1.20.1.jar /opt/flink/plugins/s3-fs-hadoop/

   # Download the Iceberg AWS bundle jar for version 1.9.1
RUN curl -fL -o /opt/flink/lib/iceberg-aws-bundle-1.9.1.jar \
    https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-aws-bundle/1.9.1/iceberg-aws-bundle-1.9.1.jar


#RUN echo "-> Install JARs: AWS / Hadoop S3" && \
#    curl -fL -o /opt/flink/lib/awscore-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/aws-core/2.31.76/aws-core-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/sdk-core-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/sdk-core/2.31.76/sdk-core-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/s3-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/s3/2.31.76/s3-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/auth-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/auth/2.31.76/auth-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/identity-spi-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/identity-spi/2.31.76/identity-spi-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/regions-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/regions/2.31.76/regions-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/utils-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/utils/2.31.76/utils-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/http-client-spi-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/http-client-spi/2.31.76/http-client-spi-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/url-connection-client-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/url-connection-client/2.31.76/url-connection-client-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/sts-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/sts/2.31.76/sts-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/apache-client-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/apache-client/2.31.76/apache-client-2.31.76.jar && \
#    curl -fL -o /opt/flink/lib/retries-spi-2.31.76.jar \
#      https://repo1.maven.org/maven2/software/amazon/awssdk/retries-spi/2.31.76/retries-spi-2.31.76.jar

# Ensure the container runs as the non-privileged flink user
USER flink
