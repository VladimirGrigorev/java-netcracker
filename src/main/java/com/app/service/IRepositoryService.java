package com.app.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IRepositoryService {

    @WebMethod
    String getUserNameById(int id);

    @WebMethod
    long getCountUsersByAge(int age);
}
