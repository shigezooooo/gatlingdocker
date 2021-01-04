# Gatling Execution Container SetUp

## Preparation
- WorkPC（Windows or Mac）
- `docker` command is available on WorkPC

## Directory Layout
Prepare the directory layout as follows.

```
  |--- result ← Output test-scenario's result.
  |--- scenario
  |       |
  |       |--- resources ← Locate test-scenario's resources file.(csv, txt)
  |       |
  |       |--- simulations ← Locate test-scenario's file.
  |
  |--- Dockerfile
  |--- README.md
```



## Create Docker Images

Move the directory containing the `Dockerfile`, and execute this command.

```
docker build -t gatling .
```



## Create Container

Execute this command, then you can create a execution container.

（About the `-v` option, please set according to your environment.）

```shell
docker run -i -d
 -v C:/docker/gatling/scenario/:/work/gatling-charts-highcharts-bundle-3.3.1/user-files
 -v C:/docker/gatling/result:/work/gatling-charts-highcharts-bundle-3.3.1/results
 --name gatling_container gatling /bin/sh
```



## Access Container

Execute this command, then you can enter the container.

```
docker exec -it gatling_container /bin/bash
```



## Execute Gatling!!

After enter the container, you execute this command in the situation you locate scenario's file on the `scenario/simulations` directory.
```
sh gatling-charts-highcharts-bundle-3.3.1/bin/gatling.sh  
```

## License
This source is released under the MIT License, see LICENSE.
