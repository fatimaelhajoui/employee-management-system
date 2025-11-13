/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.shared;

import java.util.List;
import lombok.Getter;

/**
 *
 * @author marwa
 */
@Getter
public class GlobalResponse<T> {
    public final static String Success ="success";
    public final static String Error ="error";
    
    private final String status;
    private final T data;
    private final List<ErrorItem> errors;
    
    //error
    public GlobalResponse(List<ErrorItem> errors){
        this.status=Error;
        this.data=null;
        this.errors=errors;    
    }
    
    //Success
    public GlobalResponse(T data){
        this.status=Success;
        this.data=data;
        this.errors=null;    
    }
    
    //use record for define ErrorItem and the list of errors 
    public record ErrorItem(String message){}
    
    

}
