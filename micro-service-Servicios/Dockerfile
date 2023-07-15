FROM openjdk:11 
VOLUME /tmp 
EXPOSE 8009
ADD ./target/micro-servico-servicios-1.0.0.jar servicio-servicios.jar
ENTRYPOINT ["java","-jar","/servicio-servicios.jar"]