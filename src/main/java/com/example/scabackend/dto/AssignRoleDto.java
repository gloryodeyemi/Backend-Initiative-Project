package com.example.scabackend.dto;

import lombok.Data;

@Data
public class AssignRoleDto {
    private String userEmail;
    private Long roleId;
}
