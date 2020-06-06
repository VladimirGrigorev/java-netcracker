package com.app.soap;

import com.app.exseption.InjectorException;
import com.app.service.RepositoryService;

import javax.xml.ws.Endpoint;

public class Publisher {

    public static void main(String[] args) throws InjectorException {
        Endpoint.publish("http://localhost:8080/wss/persons", new RepositoryService());
    }
}
