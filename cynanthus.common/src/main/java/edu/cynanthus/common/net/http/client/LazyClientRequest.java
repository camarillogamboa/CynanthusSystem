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
    public LazyRequest POST(String path, HttpRequest.BodyPublisher bodyPublisher) {
        return super.POST(buildPath() + path, bodyPublisher);
    }

    @Override
    public LazyRequest GET(String path) {
        return super.GET(buildPath() + path);
    }

    @Override
    public LazyRequest PUT(String path, HttpRequest.BodyPublisher bodyPublisher) {
        return super.PUT(buildPath() + path, bodyPublisher);
    }

    @Override
    public LazyRequest DELETE(String path) {
        return super.DELETE(buildPath() + path);
    }

    @Override
    public LazyRequest clone() {
        LazyClientRequest lazyClientRequest = new LazyClientRequest(httpClient, hostAddress);
        lazyClientRequest.builderConsumer = builderConsumer;
        return lazyClientRequest;
    }

}
