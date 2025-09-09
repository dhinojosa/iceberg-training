FROM spark:3.5.6-scala2.12-java17-python3-ubuntu

ENV ICEBERG_VERSION=1.6.1
ENV SCALA_BINARY_VERSION=2.12
ENV SPARK_MAJOR_VERSION=3.5

# Use bash for clearer tracing and ensure we can write to SPARK_HOME/jars
SHELL ["/bin/bash", "-lc"]
USER root
RUN set -euxo pipefail; \
    mkdir -p /opt/spark/jars; \
    chmod -R a+rwX /opt/spark/jars

# Download iceberg spark runtime (prints resolved URL)
RUN set -euxo pipefail; \
    URL="https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}/${ICEBERG_VERSION}/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}-${ICEBERG_VERSION}.jar"; \
    echo "Resolved runtime URL: ${URL}"; \
    curl -fSL "${URL}" -o "/opt/spark/jars/iceberg-spark-runtime-${SPARK_MAJOR_VERSION}_${SCALA_BINARY_VERSION}-${ICEBERG_VERSION}.jar"

# ... existing code ...
# Download AWS bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-aws-bundle/${ICEBERG_VERSION}/iceberg-aws-bundle-${ICEBERG_VERSION}.jar" \
      -o "/opt/spark/jars/iceberg-aws-bundle-${ICEBERG_VERSION}.jar"
# ... existing code ...
# Download GCP bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-gcp-bundle/${ICEBERG_VERSION}/iceberg-gcp-bundle-${ICEBERG_VERSION}.jar" \
      -o "/opt/spark/jars/iceberg-gcp-bundle-${ICEBERG_VERSION}.jar"
# ... existing code ...
# Download Azure bundle
RUN set -euxo pipefail; \
    curl -fSL "https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-azure-bundle/${ICEBERG_VERSION}/iceberg-azure-bundle-${ICEBERG_VERSION}.jar" \
      -o "/opt/spark/jars/iceberg-azure-bundle-${ICEBERG_VERSION}.jar"

ENV SPARK_HOME=/opt/spark

WORKDIR /opt/spark/work-dir
USER spark
