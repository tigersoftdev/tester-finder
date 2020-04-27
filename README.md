# Tester finder

### Setup guide

##### To run application ensure you have Java Development Kit (at least 1.8) installed on your machine and JAVA_HOME environment variable set to point your JDK installation folder.

1. Install [Maven](https://maven.apache.org/install.html).
2. Clone this repository to your local disk.
3. Enter project root folder (`tester-finder` by default).
4. Run `mvn spring-boot:run -DskipTests` in the terminal (may take a while). If you don't have Maven installed you can alternatively run `mvnw spring-boot:run -DskipTests` (Windows) or `./mvnw spring-boot:run -DskipTests` (Unix). 
5. Run `java -jar target/tester-finder-1.0.0-SNAPSHOT.jar`.
6. Open your browser and go to the address [http://localhost:8090](http://localhost:8090). 
