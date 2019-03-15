# practice-microservice

Just practicing repository for micro-service using spring cloud with netflix oss.

(Eureka, ConfigServer, Cloud Bus, Zuul, Hystrix, Feign with Rest)


ConfigurationRepo to  https://github.com/ggree1/config-repo

======================================================================

8095/demo-service1/demo1/users/{name} ---> demo-service2의 /users/{name}/items 호출 (hystrix test)
8095/demo1/users/{name}

8095/rest-service/api/feign/{name} ----> demo-service2의 /users/{name}/items 호출 (feign test)
8095/api/feign/{name}

8095/demo-service2/demo2/call/customers (/{id})  ----> rest서비스의 /api/v1/customers 호출 (LoadBalanced RestTemplate test)
8095/demo2/call/customers

======================================================================

http://localhost:8095/actuator/routes
http://localhost:8888/actuator/bus-refresh


http://localhost:8081/hystrix  -->  
    (url) http://localhost:8081/actuator/hystrix.stream 
        -> monitor stream