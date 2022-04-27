module cynanthus.common {

    requires java.net.http;
    requires com.google.gson;

    exports edu.cynanthus.common;
    exports edu.cynanthus.common.security;
    exports edu.cynanthus.common.net;
    exports edu.cynanthus.common.net.http;
    exports edu.cynanthus.common.net.http.client;
    exports edu.cynanthus.common.net.http.packet;
    exports edu.cynanthus.common.net.http.packet.converter;
    exports edu.cynanthus.common.net.tcp;
    exports edu.cynanthus.common.observable;
    exports edu.cynanthus.common.observable.event;
    exports edu.cynanthus.common.reflect;
    exports edu.cynanthus.common.resource;
    exports edu.cynanthus.common.json;

}