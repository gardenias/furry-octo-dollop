#!/bin/sh

java -Duser.timezone=GMT+8 \
  -server \
  -Xmx4g \
  -Xms4g \
  -Xss256k \
  -XX:+UseG1GC \
  -XX:+UseCompressedOops \
  -XX:+UseCompressedClassPointers \
  -XX:+SegmentedCodeCache \
  -XX:AutoBoxCacheMax=20000 \
  -XX:+AlwaysPreTouch \
  -XX:-OmitStackTraceInFastThrow \
  -Dhttp.exec.thread.size=$1 \
  -DPROFILE=loadtest -DENV=loadtest \
  -jar target/x-integration-tests-0.0.1-SNAPSHOT.jar | tee out.log
