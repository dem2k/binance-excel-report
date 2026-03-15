#!/bin/bash

# Wenn nicht in Terminal gestartet
if [ ! -t 0 ]; then
    notify-send "Error" "This program must be run in a terminal." &>/dev/null
    exit 1
fi

SCRIPT_DIR="${BASH_SOURCE[0]%/*}"

java -cp "$SCRIPT_DIR/target/classes:$SCRIPT_DIR/target/dependency/*" dem2k.AppMain -key=$(pass BINANCE_READONLY_KEY) -sec=$(pass BINANCE_READONLY_SECRET) $@

