for /f "tokens=5 delims= " %%a in ('netstat -ano ^| findstr "8004" ^| findstr "LISTENING"') do set pid=%%a
if not "%pid%" == "" (
  taskkill /f /PID %pid%
) else (
  rem echo Server is not running.
)

start /b java -jar d:\deploy\spring-boot-employee-0.0.1-SNAPSHOT.jar