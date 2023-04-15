
cd Downloads/springjedis

./mvnw -DskipTests clean package
java -jar target/springjedis-0.0.1-SNAPSHOT.jar


cd Dev/Development3/redis/redis-6.2.6
./src/redis-cli --tls  --cert ./tests/tls/client.crt  --key ./tests/tls/client.key --cacert ./tests/tls/ca.crt
./src/redis-server --tls-port 6379 --port 0  --tls-cert-file ./tests/tls/server.crt --tls-key-file ./tests/tls/server.key  --tls-ca-cert-file ./tests/tls/ca.crt


./src/redis-cli --insecure --tls monitor
./src/redis-server --tls-port 6379 --port 0  --tls-cert-file ./tests/tls/server.crt --tls-key-file ./tests/tls/server.key --tls-auth-clients no 


export DIR=~/Dev/Development3/redis/redis-6.2.6/tests/tls
java -Djavax.net.ssl.keyStoreType=pkcs12 \
-Djavax.net.ssl.keyStore="${DIR}"/client.pfx \
-Djavax.net.ssl.keyStorePassword=password \
-Djavax.net.ssl.trustStoreType=jks \
-Djavax.net.ssl.trustStore="${DIR}"/ca.jks \
-Djavax.net.ssl.trustStorePassword=password \
-jar target/springjedis-0.0.1-SNAPSHOT.jar


cd Dev/Development3/redis/redis-6.2.6/tests/tls
openssl pkcs12 -export -in client.crt  -inkey client.key -certfile ca.crt -passout pass:password -out client.pfx
keytool -import -noprompt -file ca.crt -alias ca_cert  -keystore ca.jks -storepass password