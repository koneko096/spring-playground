package com.gdn.afrizal.playground.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long nationalId;
    private Long id;
    private Boolean active;
    private Long facultyId;
    private Long programId;
    private Date entryDate;
}
