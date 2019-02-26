package com.gdn.afrizal.playground.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "faculty")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Field("id") private Long id;
    private String name;
}
