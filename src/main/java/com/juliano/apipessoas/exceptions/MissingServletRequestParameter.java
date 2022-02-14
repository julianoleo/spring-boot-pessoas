package com.juliano.apipessoas.exceptions;

public class MissingServletRequestParameter extends RuntimeException{
    public MissingServletRequestParameter(String message) { super(message);}
}
