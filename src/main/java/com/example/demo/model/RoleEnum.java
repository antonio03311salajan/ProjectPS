package com.example.demo.model;

import javax.swing.*;

public enum RoleEnum {
    ADMIN, USER, RECEPTIONIST, DOCTOR;

    public static RoleEnum getSelectedRole(JComboBox<RoleEnum> roleC) {
        Object selectedItem = roleC.getSelectedItem();
        if (selectedItem instanceof RoleEnum) {
            return (RoleEnum) selectedItem;
        }
        return null;
    }
}

