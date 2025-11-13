/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.abstracts;


/**
 *
 * @author marwa
 */
public interface EmailService {
    void sendAccountCreationEmail(String to, String token);
    void sendPasswordResetEmail(String to, String token);
}
