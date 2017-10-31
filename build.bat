set ANDROID_HOME=C:\android
set ANDROID_ZIP=http://dl.google.com/android/android-sdk_r24.3.3-windows.zip           
if not exist downloads mkdir downloads
if not exist %ANDROID_HOME% mkdir %ANDROID_HOME%
if not exist   %ANDROID_ZIP% call :downloadfile %ANDROID_ZIP% downloads/SDK.zip
echo "Finished downloading"
7z  -o%ANDROID_HOME%  x downloads\SDK.zip
 
echo "Finished extracting"

set ANDROID_HOME=C:\android\android-sdk-windows
set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
dir  %ANDROID_HOME%
echo "Updating Required components"
echo y | android --silent update sdk -u -a -t 11
echo y | android --silent update sdk -u -a -t 40
echo y | android --silent update sdk -u -a -t 41
echo y | android --silent update sdk -u -a -t 179
echo "Running gradle"

 
echo "Updating packages in app"
call  gradlew
echo "Building apk"
./gradlew assembleDebug


:downloadfile
:: ----------------------------------------------------------------------
:: call :downloadfile <URL> <localfile>
if not exist %2 (
  curl -f -L %1 -o %2 || exit 1
)
goto :eof
