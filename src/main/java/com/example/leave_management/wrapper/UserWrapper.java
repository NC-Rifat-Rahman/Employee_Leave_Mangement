package com.example.leave_management.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper
{
    private Integer id;
    private String name;
    private String email;
    private String status;
}

