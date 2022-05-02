package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.HostAddress;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

class LazyClientRequest extends BasicLazyRequest {

    final HostAddress hostAddress;

    LazyClientRequest(HttpClient httpClient, HostAddress hostAddress) {
        super(httpClient);
        this.hostAddress = hostAddress;
    }

    String buildPath() {
        return "http://" + hostAddress.getHostName() + ":" + hostAddress.getHostPort();
    }

    @Override
    public LazyRequest POST(String uri, HttpRequest.BodyPublisher bodyPublisher) {
        return super.POST(buildPath() + uri, bodyPublisher);
    }

    @Override
    public LazyRequest GET(String uri) {
        return super.GET(buildPath() + uri);
    }

    @Override
    public LazyRequest PUT(String uri, HttpRequest.BodyPublisher bodyPublisher) {
        return super.PUT(buildPath() + uri, bodyPublisher);
    }

    @Override
    public LazyRequest DELETE(String uri) {
        return super.DELETE(buildPath() + uri);
    }

    @Override
    public LazyRequest clone() {
        LazyClientRequest lazyClientRequest = new LazyClientRequest(httpClient, hostAddress);
        lazyClientRequest.builderConsumer = builderConsumer;
        return lazyClientRequest;
    }

}
