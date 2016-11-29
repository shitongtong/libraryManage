#!/bin/bash

PID=$(ps -ef|grep novel-spider.jar|grep -v grep |awk '{print $2}')

if [ "x$PID" != "x" ];then

echo "novel-spider is runing....."

kill -9 $PID && echo "novel-spider is killed"

BASEDIR=$(dirname $0)
if [ ! -d "$BASEDIR/logs" ]; then
  mkdir $BASEDIR/logs
fi

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS -jar $BASEDIR/novel-spider.jar > $BASEDIR/logs/stdout.log 2>&1 &

else

BASEDIR=$(dirname $0)
if [ ! -d "$BASEDIR/logs" ]; then
  mkdir $BASEDIR/logs
fi

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS -jar $BASEDIR/novel-spider.jar > $BASEDIR/logs/stdout.log 2>&1 &

fi

echo "novel-spider is start OK......"

