FROM spark:3.5.3-scala2.12-java17-python3-ubuntu

ENV ICEBERG_VERSION=1.6.1
ENV SCALA_BINARY_VERSION=2.12
ENV SPARK_MAJOR_VERSION=3.5
ENV SPARK_HOME=/opt/spark
ENV JARS_DIR=${SPARK_HOME}/jars

SHELL ["/bin/bash", "-lc"]
USER root

# Ensure directory existance
RUN set -euxo pipefail; \
    test -d "${JARS_DIR}"; \
    mkdir -p ${JARS_DIR}; \
    chmod -R a+rwX ${JARS_DIR}

# Download iceberg spark runtime (prints resolved URL)
RUN set -euxo pipefail; \
    URL="https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}/${ICEBERG_VERSION}/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}-${ICEBERG_VERSION}.jar"; \
    echo "Resolved runtime URL: ${URL}"; \
    curl -fSL "${URL}" -o "${JARS_DIR}/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}-${ICEBERG_VERSION}.jar"

# Download AWS bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-aws-bundle/${ICEBERG_VERSION}/iceberg-aws-bundle-${ICEBERG_VERSION}.jar" \
      -o "${JARS_DIR}/iceberg-aws-bundle-${ICEBERG_VERSION}.jar"

# Download GCP bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-gcp-bundle/${ICEBERG_VERSION}/iceberg-gcp-bundle-${ICEBERG_VERSION}.jar" \
      -o "${JARS_DIR}/iceberg-gcp-bundle-${ICEBERG_VERSION}.jar"

# Download Azure bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-azure-bundle/${ICEBERG_VERSION}/iceberg-azure-bundle-${ICEBERG_VERSION}.jar" \
      -o "${JARS_DIR}/iceberg-azure-bundle-${ICEBERG_VERSION}.jar"

RUN set -euxo pipefail; \
    HADOOP_VERSION=3.3.4 && \
    echo "Downloading hadoop-aws-${HADOOP_VERSION}.jar" && \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-aws/${HADOOP_VERSION}/hadoop-aws-${HADOOP_VERSION}.jar" \
      -o "${JARS_DIR}/hadoop-aws-${HADOOP_VERSION}.jar" && \
    AWS_BUNDLE_VERSION="1.12.698" && \
    echo "Downloading aws-java-sdk-bundle-${AWS_BUNDLE_VERSION}.jar" && \
    curl -fSL "https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-bundle/${AWS_BUNDLE_VERSION}/aws-java-sdk-bundle-${AWS_BUNDLE_VERSION}.jar" \
      -o "${JARS_DIR}/aws-java-sdk-bundle-${AWS_BUNDLE_VERSION}.jar" && \
    echo "Installed: $(ls -1 ${JARS_DIR}/hadoop-aws-*.jar) and $(ls -1 ${JARS_DIR}/aws-java-sdk-bundle-*.jar)"

ENV SPARK_HOME=/opt/spark

WORKDIR /opt/spark/work-dir
USER spark
