package com.gdn.afrizal.playground.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "student_account")
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
