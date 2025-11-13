/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.dtos;

import lombok.Getter;

/**
 *
 * @author marwa
 */
@Getter
public class ResetPasswordRequest {
    String token;
    String newPassword;
}
