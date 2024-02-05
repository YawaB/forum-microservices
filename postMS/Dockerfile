FROM openjdk:20.0.1
LABEL authors="Brinda"


# Set the working directory to /app
WORKDIR /app

# Copiez le fichier JAR de l'application dans le conteneur
COPY target/ /app

# Exposez le port
EXPOSE 8089

# Démarrez l'application lorsque le conteneur démarre
CMD ["java", "-jar", "eureka-0.0.1-SNAPSHOT.jar"]

