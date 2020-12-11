## Prepare a local test environment
Pull a docker image with active mq (this is an example, others work as well)

```docker pull rmohr/activemq```

Run the image with activeMQ port open on 61616 and web console on 8161

```docker run -p 61616:61616 -p 8161:8161 rmohr/activemq```

## How to use this example
- Configure your MQ Endpoint using the variables defined in application.properties
- They default to the values if you use above mentioned docker  
  - mq.topic.name=Q.TEST.TOPIC
  - mq.user=admin
  - mq.password:password
  - mq.host:tcp://localhost:61616
  
- Launch the service  
- Open http://localhost:8161/ to see the web console of active MQ
- Open http://localhost:8080/add?message=yourmessagetext to add a new message
- The service will write the message to the LOG

## How to make it 'real world'
- Deploy the service with real credentials and endpoint
- Implement a custom *MessageListener* which replaces the provided *LogMessageListener* and do 
whatever business logic you want to perform inside that MessageListener.  