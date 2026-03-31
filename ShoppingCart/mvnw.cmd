@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.8.1
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JAVA_HOME ie c:\jdk1.8.0_9
@REM
@REM Optional ENV vars
@REM M2_HOME - location of maven2 installation folder (defaults to this script dir\..\)
@REM MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
@REM MAVEN_BATCH_PAUSE - set to 'on' to wait for a keystroke before ending
@REM MAVEN_OPTS - parameters passed to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM MAVEN_SKIP_RC - flag to disable loading of mavenrc files
@REM ----------------------------------------------------------------------------

@setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@REM Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@REM Add default JVM options here. You can also use MAVEN_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@REM Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >nul 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@REM Increase the heap size if needed
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@REM Execute Maven
if not "%MAVEN_SKIP_RC%" == "" goto skipRcPre
@REM check for pre script, once with legacy .bat ending and once with .cmd ending
if exist "%USERPROFILE%\mavenrc_pre.bat" call "%USERPROFILE%\mavenrc_pre.bat" %*
if exist "%USERPROFILE%\mavenrc_pre.cmd" call "%USERPROFILE%\mavenrc_pre.cmd" %*
:skipRcPre

@setlocal

set ERROR_CODE=0

@REM To isolate internal variables from possible post scripts, we use another setlocal
@setlocal

@REM ==== START VALIDATION ====
if not exist "%JAVA_HOME%\bin\java.exe" (
  echo.
  echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
  echo.
  echo Please set the JAVA_HOME variable in your environment to match the
  echo location of your Java installation.
  echo.
  goto fail
)

@REM ==== END VALIDATION ====

:init

@REM Find the project base dir, i.e. the directory that contains the folder ".mvn".
@REM Fallback to current directory if .mvn not found.

set MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%
if "%MAVEN_PROJECTBASEDIR%"=="" goto findBaseDirLoop
goto findBaseDirEnd

:findBaseDirLoop
if exist "%CD%\.mvn" goto findBaseDirEnd
cd ..
if "%CD%"=="%DRIVE%" goto findBaseDirFail
goto findBaseDirLoop

:findBaseDirFail
cd /d "%MAVEN_PROJECTBASEDIR%"
echo.
echo ERROR: Could not locate the Maven project base directory, active directory is %CD%.
echo.
goto fail

:findBaseDirEnd
if "%MAVEN_PROJECTBASEDIR%"=="" (
  set MAVEN_PROJECTBASEDIR=%CD%
)
if not "%MAVEN_HOME%"=="" (
  set MAVEN_HOME=%MAVEN_HOME:"=%
)

set MAVEN_JAVA_EXE=%JAVA_HOME%\bin\java.exe

set WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

set DOWNLOAD_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.1.0/maven-wrapper-3.1.0.jar"

for /F "tokens=1,2 delims==" %%A in ("%MAVEN_JAVA_EXE% -version 2>nul") do (
    for /F "tokens=1.*" %%a in ("%%~2") do (
        set JAVA_VERSION=%%a
    )
)

if exist "%WRAPPER_JAR%" (
    if "%MVNW_VERBOSE%" == "true" (
        echo Found %WRAPPER_JAR%
    )
) else (
    if not "%MVNW_REPOURL%" == "" (
        set DOWNLOAD_URL="%MVNW_REPOURL%/org/apache/maven/wrapper/maven-wrapper/3.1.0/maven-wrapper-3.1.0.jar"
    )
    if "%MVNW_VERBOSE%" == "true" (
        echo Couldn't find %WRAPPER_JAR%, downloading it ...
        echo Downloading from: %DOWNLOAD_URL%
    )

    powershell -Command "&{"^
	"$webclient = new-object System.Net.WebClient;"^
	"if (-not ([string]::IsNullOrEmpty('%MVNW_USERNAME%') -and [string]::IsNullOrEmpty('%MVNW_PASSWORD%'))) {"^
	"$webclient.Credentials = new-object System.Net.NetworkCredential('%MVNW_USERNAME%', '%MVNW_PASSWORD%');"^
	"}"^
	"[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $webclient.DownloadFile('%DOWNLOAD_URL%', '%WRAPPER_JAR%')"^
	"}"
    if "%ERRORLEVEL%" == "0" (
        if "%MVNW_VERBOSE%" == "true" (
            echo Successfully downloaded %WRAPPER_JAR%
        )
    ) else (
        echo.
        echo ERROR: Failed to download %WRAPPER_JAR%
        echo.
        echo For more information about the Maven Wrapper, please visit
        echo https://maven.apache.org/wrapper/
        echo.
        goto error
    )
)
@REM End of extension

@REM Provide a "standardized" way to retrieve the CLI args that will
@REM work with both Windows and non-Windows executions.
set MAVEN_CMD_LINE_ARGS=%*

%MAVEN_JAVA_EXE% %JVM_CONFIG_MAVEN_PROPS% %MAVEN_OPTS% %DEFAULT_JVM_OPTS% "-Dclassworlds.conf=%MAVEN_HOME%\bin\m2.conf" "-Dmaven.home=%MAVEN_HOME%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" %WRAPPER_LAUNCHER% %MAVEN_CMD_LINE_ARGS%
if "%ERRORLEVEL%" == "0" goto end

:error
set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%

if not "%MAVEN_SKIP_RC%"=="" goto skipRcPost
@REM check for post script, once with legacy .bat ending and once with .cmd ending
if exist "%USERPROFILE%\mavenrc_post.bat" call "%USERPROFILE%\mavenrc_post.bat"
if exist "%USERPROFILE%\mavenrc_post.cmd" call "%USERPROFILE%\mavenrc_post.cmd"
:skipRcPost

@endlocal & exit /b %ERROR_CODE%

