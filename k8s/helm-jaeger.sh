helm install --name jaeger jaegertracing/jaeger  \
 --set provisionDataStore.cassandra=false   \
 --set storage.type=elasticsearch   \
 --set storage.elasticsearch.host=elasticsearch   \
 --set storage.elasticsearch.port=9200