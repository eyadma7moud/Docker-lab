# 1. Start from an official image that has Java 21 installed
FROM eclipse-temurin:21-jdk

# 2. Set the working folder inside the image
WORKDIR /app

# 3. Copy your code into the image
COPY App.java .

# 4. Compile it (runs once, at build time)
RUN javac App.java

# 5. Document the port and set the start command
EXPOSE 5000
CMD ["java", "App"]
