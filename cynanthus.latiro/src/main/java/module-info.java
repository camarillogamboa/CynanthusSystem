module cynanthus.latiro {

    requires java.logging;
    requires java.net.http;

    requires cynanthus.common;
    requires cynanthus.microservice;
    requires cynanthus.bean;
    requires cynanthus.domain;

    opens edu.cynanthus.latiro to cynanthus.microservice, com.google.gson;

}