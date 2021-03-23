#CIQDashboard ALM Collector

#Command to Execute JAR file

-java -jar ciqdashboard-collector-alm.3.1.0.jar --server.port=<serverName> --spring.data.mongodb.uri=mongodb://<DBUername>:${spring.data.mongodb.credents}@<DBServername>:<DBPort>/<DBName> --spring.data.mongodb.credents=ENC(JasyptEncryptedDBPassword) --jasypt.encryptor.password=<Base64EncodeKey> 
