set ANDROID_HOME=C:\android\
if not exist downloads mkdir downloads
if not exist %ANDROID_HOME% mkdir %ANDROID_HOME%
curl -o downloads/SDK.zip http://dl.google.com/android/android-sdk_r22.0.5-linux.tgz -o downloads/SDK.zip
7z x downloads\SDK.zip -o %ANDROID_HOME% > nul || exit 1
(while sleep 3; do echo "y"; done) |  %ANDROID_HOME%tools\bin>android --silent update sdk --no-ui  --filter android-24,platform-tools,tools,build-tools-24.0.3
./gradlew.bat
./gradlew.bat assembleDebug

