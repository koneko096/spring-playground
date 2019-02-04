package com.gdn.afrizal.playground.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAccount {
    private Long id;
    private Long studentId;
    private Boolean active;
    private String username;
    private String password;
    private Date createdAt;
    private Date modifiedAt;
}
