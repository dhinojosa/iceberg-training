#!/bin/bash
docker buildx create --use && docker buildx build --platform linux/amd64,linux/arm64 -t dhinojosa/pyspark-iceberg-notebook:0.1.1 --push -f Dockerfile .
