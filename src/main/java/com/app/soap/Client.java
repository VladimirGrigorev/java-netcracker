package com.app.soap;

import com.app.service.IRepositoryService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:8080/wss/persons?wsdl");

        QName qname = new QName("http://service.app.com/","RepositoryServiceService");

        Service service = Service.create(url,qname);

        IRepositoryService webserv = service.getPort(IRepositoryService.class);

        System.out.println(webserv.getUserNameById(1));
        System.out.println(webserv.getCountUsersByAge(25));
    }
}
