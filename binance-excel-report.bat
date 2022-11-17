@ECHO OFF
IF NOT EXIST %~dp0config\common.xlsx (
	MKDIR %~dp0config 1>nul 2>&1
	 COPY %~dp0target\classes\common.xlsx %~dp0config\common.xlsx 1>nul 2>&1
)
IF NOT EXIST %~dp0config\properties.bat (
	ECHO @SET BINANCE_APIKEY=XXXXXXXYOURAPIKEYXXXXXXXXXXXXXX >> %~dp0config\properties.bat
	ECHO @SET BINANCE_SECRET=XXXXXXYOURAPISECRETXXXXXXXXXXXX >> %~dp0config\properties.bat
)
CALL %~dp0config\properties.bat
SET CLASSPATH=%~dp0target\classes;%~dp0config
FOR %%i IN ("%~dp0target\dependency\*.jar") do call :addcp %%i
java dem2k.AppMain -key=%BINANCE_APIKEY% -sec=%BINANCE_SECRET% %*
GOTO ende
:addcp
SET CLASSPATH=%1;%CLASSPATH%
:ende
