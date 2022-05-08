module cynanthus.microservice {

    requires java.logging;
    requires java.net.http;
    requires jdk.httpserver;
    requires com.google.gson;
    requires java.validation;
    requires cynanthus.common;
    requires cynanthus.bean;

    exports edu.cynanthus.microservice;
    exports edu.cynanthus.microservice.net.http.server;
    exports edu.cynanthus.microservice.net.http.server.engine;
    exports edu.cynanthus.microservice.property;
    exports edu.cynanthus.microservice.nanoservice;

}