module cynanthus.sensor {

    requires java.logging;
    requires java.net.http;
    requires cynanthus.common;
    requires cynanthus.microservice;
    requires cynanthus.bean;
    requires cynanthus.domain;

    exports edu.cynanthus.sensor;

    opens edu.cynanthus.sensor to cynanthus.microservice, com.google.gson;

}