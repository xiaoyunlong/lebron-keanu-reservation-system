for /f "tokens=5 delims= " %%a in ('netstat -ano ^| findstr "8088" ^| findstr "LISTENING"') do set pid=%%a
if not "%pid%" == "" (
  taskkill /f /PID %pid%
) else (
  rem echo Server is not running.
)

start /b java -jar d:\deploy\backend\reservation-system-0.0.1-SNAPSHOT.jar