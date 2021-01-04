# ベースイメージを取得（GatlingはJava環境で動く）
FROM openjdk:11

# 言語設定
ENV LANG C.UTF-8

# Gatlingのソースを解凍するのに必要なパッケージを取得
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y wget bsdtar

# workディレクトリを作成し、移動
RUN mkdir /work
RUN cd /work

# /workを作業ディレクトリに設定
WORKDIR /work

# Gatlingのソースをダウンロード&インストール
RUN wget -qO- https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/3.3.1/gatling-charts-highcharts-bundle-3.3.1-bundle.zip | bsdtar -xvf-

# GATLING_HOMEを設定
ENV GATLING_HOME=/work/gatling-charts-highcharts-bundle-3.3.1
