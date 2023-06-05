# EnigmaGame

Paradigmas de programacion, Juego Arcade, Politecnico grancolombiano

diseño https://www.figma.com/file/rxSm0ccZm74kicFtIY3PSV/Dise%C3%B1o-Juego-preguntados?type=design&node-id=0-1&t=XHrbLI4D5Hxslail-0

para ejecutar el proyecto se requiere Java instalado y el jdk de JAVA FX 

https://openjfx.io/openjfx-docs/#install-java

Luego de descargar se puede dirigir a la carpeta EnigmaGame/Target abrir el CMD y correr el comando


java --module-path "path\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar EnigmaGame.jar

Ejemplo:


java --module-path "C:\Program Files\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar EnigmaGame.jar


--module-path "C:\Program Files\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml

#Informacion util para generar .exe o hacer que el .Jar se ejecute

"C:\Program Files\Java\jdk-18.0.1.1\bin\jpackage.exe" --input target --name Enigma --main-jar EnigmaGame.jar --main-class com.poli.quizz.App  --module-path "C:\Program Files\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml  --type exe

https://www.youtube.com/watch?v=EyYb0GmtEX4&ab_channel=Randomcode

https://www.youtube.com/watch?v=IXeBEV50Xas&ab_channel=Ken

Wix tool para generar exe o msi (requiere de .net framework 3.5) https://github.com/wixtoolset/wix3/releases/tag/wix3112rtm

generar .jar correctamente "C:\Program Files\Java\apache-maven-3.9.2\bin\mvn.cmd" clean package solucionar problema al ejecutar .jar con dependencias Java FX https://www.youtube.com/watch?v=7c30N9MFJcQ&ab_channel=TramoTech

"C:\Program Files\Java\jdk-18.0.1.1\bin\jpackage.exe" --input target --name Enigma --main-jar EnigmaGame.jar --main-class com.poli.quizz.App  --module-path "C:\Program Files\Java\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml  --type exe

Se usa launch4j-3.50-win32 y se logra crear .exe con exito

