set ANDROID_HOME=C:\android
set ANDROID_ZIP=http://dl.google.com/android/android-sdk_r22.0.5-linux.tgz
if not exist downloads mkdir downloads
if not exist %ANDROID_HOME% mkdir %ANDROID_HOME%
call :downloadfile %ANDROID_ZIP% downloads/SDK.tgz
echo "Finished downloading"
7z  -o%ANDROID_HOME%  x downloads\SDK.tgz

echo "Finished extracting"

set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
ls  %ANDROID_HOME%

echo y | android --silent update sdk --no-ui  --filter android-24,platform-tools,tools,build-tools-24.0.3
echo "Running gradle"

./gradlew.bat
./gradlew.bat assembleDebug

:downloadfile
:: ----------------------------------------------------------------------
:: call :downloadfile <URL> <localfile>
if not exist %2 (
  curl -f -L %1 -o %2 || exit 1
)
goto :eof