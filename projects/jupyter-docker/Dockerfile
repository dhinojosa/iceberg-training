FROM jupyter/pyspark-notebook:latest

# Set versions
ENV ICEBERG_VERSION=1.9.2
ENV SPARK_VERSION=3.5.1
ENV AWS_ACCESS_KEY_ID=minioadmin
ENV AWS_SECRET_ACCESS_KEY=minioadmin
ENV AWS_REGION=us-east-1

USER root

# Install MinIO client
RUN wget https://dl.min.io/client/mc/release/linux-amd64/mc -O /usr/local/bin/mc && \
    chmod +x /usr/local/bin/mc

# Download Iceberg Spark Runtime JARs
RUN curl -L -o /opt/spark/jars/iceberg-spark-runtime.jar \
      https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-spark-runtime-${SPARK_VERSION}_2.12/${ICEBERG_VERSION}/iceberg-spark-runtime-${SPARK_VERSION}_2.12-${ICEBERG_VERSION}.jar && \
    curl -L -o /opt/spark/jars/iceberg-aws-bundle.jar \
      https://repo1.maven.org/maven2/org/apache/iceberg/iceberg-aws-bundle/${ICEBERG_VERSION}/iceberg-aws-bundle-${ICEBERG_VERSION}.jar

# Switch back to default Jupyter user
USER $NB_UID

# Install Python packages (add more if needed)
RUN pip install --no-cache-dir \
    pyarrow \
    pandas \
    jupyterlab \
    ipython-sql \
    openpyxl \
    seaborn

# Copy optional Spark config if provided
# COPY spark-defaults.conf /usr/local/spark/conf/ # optional

CMD ["start-notebook.sh"]