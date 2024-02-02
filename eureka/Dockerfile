FROM openjdk:20.0.1
LABEL authors="Brinda"


# Set the working directory to /app
WORKDIR /app

# Copiez le fichier JAR de l'application dans le conteneur
COPY target/ /app

# Exposez le port 8088
EXPOSE 8088

# Démarrez l'application lorsque le conteneur démarre
