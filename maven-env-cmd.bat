set java_jdk_folder=
for /F %%i in ('dir "c:\Program Files\Java" /b ^| findstr jdk') do (set java_jdk_folder=%%i)
cmd /k set JAVA_HOME=C:\Program^ Files\Java\%java_jdk_folder%