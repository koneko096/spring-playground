package com.gdn.afrizal.playground.spring.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Field("id") private String id;
    private Long programId;
    private String name;
}
