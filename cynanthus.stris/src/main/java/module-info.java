module cynanthus.stris {

    requires java.logging;
    requires cynanthus.common;
    requires cynanthus.microservice;
    requires cynanthus.bean;
    requires cynanthus.domain;

    opens edu.cynanthus.stris to cynanthus.microservice, com.google.gson;

}