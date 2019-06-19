FROM java:8-jre
ADD build/libs/flat-file-analisys-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080 
CMD java -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom -Duser.timezone=America/Sao_Paulo -jar /app.jar