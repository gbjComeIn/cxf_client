package com.jacky.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.jacky.Info;


@WebService(targetNamespace="http://jacky.com/")
public interface GetInfoService {
    @WebMethod(operationName = "add")  
    @WebResult(name = "result")  
    public int add(@WebParam(name = "number1") int number1, @WebParam(name = "number2") int number2); 
    
    @WebMethod(operationName = "getInfo")  
    @WebResult(name = "result")  
    public Info getInfo(@WebParam(name = "name") String name, @WebParam(name = "age") int age);
}
