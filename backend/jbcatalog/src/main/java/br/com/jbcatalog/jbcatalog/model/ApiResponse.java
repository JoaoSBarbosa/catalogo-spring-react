package br.com.jbcatalog.jbcatalog.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
public class ApiResponse implements Serializable {
    private String message;
    private HttpStatus status;
    private Date sendDateTime;
    private Object object;

    public ApiResponse(){}

    public ApiResponse(String message, HttpStatus status, Date sendDateTime, Object object) {
        this.message = message;
        this.status = status;
        this.sendDateTime = sendDateTime;
        this.object = object;
    }
}
