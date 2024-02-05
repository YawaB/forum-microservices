FROM openjdk:20.0.1
LABEL authors="Brinda"


# Set the working directory to /app
WORKDIR /app

# Copiez le fichier JAR de l'application dans le conteneur
COPY target/authenticationMS-0.0.1-SNAPSHOT.jar /app

# Exposez le port
EXPOSE 8087

# Démarrez l'application lorsque le conteneur démarre
CMD ["java", "-jar", "userMS-0.0.1-SNAPSHOT.jar"]
