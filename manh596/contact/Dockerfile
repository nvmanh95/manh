FROM java:8-jre

VOLUME /home/atg/workspace/atg_microservices/build/libs/:/home/libs

CMD ["java", "-Xmx200m", "-jar", "/home/libs/productcatalog-0.1.0.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:3007/health || exit 1

EXPOSE 3007