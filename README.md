
# Build 
```
mvn -DskipTests clean package
```

# Create trust store
```
keytool -import -noprompt -file ca.pem -alias ca_cert  -keystore ca.jks -storepass password
```

# Test application
```
java  -Djavax.net.ssl.trustStoreType=jks -Djavax.net.ssl.trustStore=ca.jks  -Djavax.net.ssl.trustStorePassword=password -Djavax.net.debug=ssl -jar target/springjedis-0.0.1-SNAPSHOT.jar <IP> <PORT> <PASSWORD>
```