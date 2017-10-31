set ANDROID_HOME=C:\android
set ANDROID_ZIP=http://dl.google.com/android/android-sdk_r22.6.2-windows.zip
if not exist downloads mkdir downloads
if not exist %ANDROID_HOME% mkdir %ANDROID_HOME%
if not exist   %ANDROID_ZIP% call :downloadfile %ANDROID_ZIP% downloads/SDK.zip
echo "Finished downloading"
7z  -o%ANDROID_HOME%  x downloads\SDK.zip

echo "Finished extracting"
set ANDROID_HOME=C:\android\android-sdk-windows
set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
dir  %ANDROID_HOME%

echo y | android --silent update sdk --no-ui  --filter build-tools-24.0.3,android-24,extra-android-m2repository
echo "Updating liscence"
echo y| android update sdk --no-ui
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