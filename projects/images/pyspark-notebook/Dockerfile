# Dockerfile.jupyter. Ensured that Spark is running 3.5
FROM quay.io/jupyter/pyspark-notebook:2024-10-14

ENV ICEBERG_VERSION=1.6.1
ENV SCALA_BINARY_VERSION=2.12
ENV SPARK_MAJOR_VERSION=3.5

# Use bash for tracing and make sure we can write to Spark's jars dir
SHELL ["/bin/bash", "-lc"]
USER root
RUN set -euxo pipefail; \
    mkdir -p /usr/local/spark/jars; \
    chmod -R a+rwX /usr/local/spark/jars

# Download Iceberg Spark runtime (print resolved URL before downloading)
RUN set -euxo pipefail; \
    RUNTIME_URL="https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}/${ICEBERG_VERSION}/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}-${ICEBERG_VERSION}.jar"; \
    echo "Resolved runtime URL: ${RUNTIME_URL}"; \
    curl -fSL "${RUNTIME_URL}" -o "/usr/local/spark/jars/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}-${ICEBERG_VERSION}.jar"

# Download AWS bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-aws-bundle/${ICEBERG_VERSION}/iceberg-aws-bundle-${ICEBERG_VERSION}.jar" \
      -o "/usr/local/spark/jars/iceberg-aws-bundle-${ICEBERG_VERSION}.jar"

# Download GCP bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-gcp-bundle/${ICEBERG_VERSION}/iceberg-gcp-bundle-${ICEBERG_VERSION}.jar" \
      -o "/usr/local/spark/jars/iceberg-gcp-bundle-${ICEBERG_VERSION}.jar"

# Download Azure bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-azure-bundle/${ICEBERG_VERSION}/iceberg-azure-bundle-${ICEBERG_VERSION}.jar" \
      -o "/usr/local/spark/jars/iceberg-azure-bundle-${ICEBERG_VERSION}.jar"

RUN set -euxo pipefail; \
    HADOOP_VERSION=3.3.4 && \
    echo "Downloading hadoop-aws-${HADOOP_VERSION}.jar" && \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-aws/${HADOOP_VERSION}/hadoop-aws-${HADOOP_VERSION}.jar" \
      -o "/usr/local/spark/jars/hadoop-aws-${HADOOP_VERSION}.jar" && \
    AWS_BUNDLE_VERSION="1.12.698" && \
    echo "Downloading aws-java-sdk-bundle-${AWS_BUNDLE_VERSION}.jar" && \
    curl -fSL "https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-bundle/${AWS_BUNDLE_VERSION}/aws-java-sdk-bundle-${AWS_BUNDLE_VERSION}.jar" \
      -o "/usr/local/spark/jars/aws-java-sdk-bundle-${AWS_BUNDLE_VERSION}.jar" && \
    echo "Installed: $(ls -1 ${JARS_DIR}/hadoop-aws-*.jar) and $(ls -1 ${JARS_DIR}/aws-java-sdk-bundle-*.jar)"

# (Optional) extras you like:
USER jovyan
RUN pip install --no-cache-dir pandas numpy matplotlib
