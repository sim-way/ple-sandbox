@echo off
REM Maven wrapper batch script
setlocal enableextensions
cd /d "%~dp0"
set "BASEDIR=%CD%"
set "WRAPPER_JAR=%BASEDIR%\.mvn\wrapper\maven-wrapper.jar"

if not exist "%BASEDIR%\.mvn\wrapper" (
  mkdir "%BASEDIR%\.mvn\wrapper"
)

if not exist "%WRAPPER_JAR%" (
  echo Downloading maven-wrapper.jar...
  powershell -NoProfile -Command "(New-Object System.Net.WebClient).DownloadFile('https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar', '%WRAPPER_JAR%')" 2>nul || (
    echo Warning: Failed to download maven-wrapper.jar
    exit /b 1
  )
)

set "JAVA_EXE=java.exe"
if defined JAVA_HOME (
  if exist "%JAVA_HOME%\bin\java.exe" (
    set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
  )
)

"%JAVA_EXE%" -version >nul 2>&1
if errorlevel 1 (
  echo Error: Java not found. Please install Java and set JAVA_HOME.
  exit /b 1
)

"%JAVA_EXE%" -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%BASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
exit /b %ERRORLEVEL%