@echo off
title EXCAVATOR

setlocal EnableDelayedExpansion
set cp=.
for /F %%a in ('dir ..\lib\*.jar /b') do set cp=!cp!;..\lib\%%a

@echo on

java -cp ../config;%cp% -Xms256m -Xmx768m -XX:MaxDirectMemorySize=128m com.jf.stock.selection.Strategy1 ..

pause
