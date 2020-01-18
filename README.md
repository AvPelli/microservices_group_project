# Gokkeeuuuh
## Project runnen via docker:

### Uitvoeren in Home folder:
```
bash buildall.sh
docker-compose up --build
```

### Hiervoor moet:
- Alle localhosts vervangen naar de corresponderende dockernaam bv: _membermanagement_:
    `http://localhost:2226 -> http://bettingmanagement:2226`

- Kafka, Zookeeper en Mongodb properties op corresponderende dockernaam zetten:
    ```
    In plaats van localhost:
        spring.data.mongodb.host=gamblingdb
        spring.cloud.stream.kafka.binder.zkNodes=zookeeper
        spring.cloud.stream.kafka.binder.brokers=kafka
    ```
- Dit is momenteel alles waar ik op kan komen :)