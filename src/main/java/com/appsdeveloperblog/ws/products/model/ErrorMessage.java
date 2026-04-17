package com.appsdeveloperblog.ws.products.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
public class ErrorMessage {
    private Date timeStamp;
    private String message;
    private String details;
}
