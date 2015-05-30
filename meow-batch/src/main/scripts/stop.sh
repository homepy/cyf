#!/bin/sh
user=`whoami`
pgrep -u $user -f io.github.homepy.meow.batch.MeowBatch | xargs kill -9