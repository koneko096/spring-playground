package com.gdn.afrizal.playground.spring.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Field("id") private Long id;
    private Long nationalId;
    private Boolean active;
    private Long facultyId;
    private Long programId;
    private Date entryDate;
}
