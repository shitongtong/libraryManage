@echo off 

set SETTINGS_HOME="D:\maven\settings.xml"

call mvn install:install-file -Dfile=lib/mibble-2.9.3.jar -DgroupId=net.percederberg.mibble -DartifactId=mibble -Dversion=2.9.3 -Dpackaging=jar --settings %SETTINGS_HOME%
call mvn install:install-file -Dfile=lib/mibble-2.9.3.jar -DgroupId=net.percederberg.mibble -DartifactId=mibble-mibs -Dversion=2.9.3 -Dpackaging=jar --settings %SETTINGS_HOME%

pause
