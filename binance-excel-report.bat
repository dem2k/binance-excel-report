@ECHO OFF
IF NOT EXIST %~dp0config\common.xlsx (
	MKDIR %~dp0config 1>nul 2>&1
	 COPY %~dp0target\classes\common.xlsx %~dp0config\common.xlsx 1>nul 2>&1
)
IF NOT EXIST %~dp0config\properties.bat (
	ECHO @SET BINANCE_APIKEY=XXXXXXXYOURAPIKEYXXXXXXXXXXXXXX >> %~dp0config\properties.bat
	ECHO @SET BINANCE_SECRET=XXXXXXYOURAPISECRETXXXXXXXXXXXX >> %~dp0config\properties.bat
	ECHO WARNING: Check your Settings in %~dp0config\properties.bat before first Run.
	EXIT
)
CALL %~dp0config\properties.bat
SET CLASSPATH=%~dp0config;%~dp0target\classes
FOR %%i IN ("%~dp0target\dependency\*.jar") DO CALL :addcp %%i
java dem2k.AppMain -key=%BINANCE_APIKEY% -sec=%BINANCE_SECRET% %*
GOTO ende
:addcp
SET CLASSPATH=%1;%CLASSPATH%
:ende
