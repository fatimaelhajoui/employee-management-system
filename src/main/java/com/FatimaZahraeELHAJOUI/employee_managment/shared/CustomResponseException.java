/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author marwa
 */
@Getter
@AllArgsConstructor
public class CustomResponseException extends RuntimeException{
    private int code;
    private String message;
    
    public static CustomResponseException ResourceNotFound(String message){
        return new CustomResponseException(404, message);
    }
    
    public static CustomResponseException BadRequest(String message){
        return new CustomResponseException(400, message);
    }
    
    public static CustomResponseException BadCredentials(){
        return new CustomResponseException(401, "Bad Credentials");
    }
    
    
   
}
