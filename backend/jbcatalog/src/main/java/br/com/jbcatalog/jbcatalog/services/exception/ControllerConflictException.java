package br.com.jbcatalog.jbcatalog.services.exception;

public class ControllerConflictException extends RuntimeException{
    public ControllerConflictException(String message){
        super(message);
    }
}
