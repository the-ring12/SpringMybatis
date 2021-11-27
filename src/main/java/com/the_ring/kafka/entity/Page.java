package com.the_ring.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private String IP;
    private String URL;
    private Date date;
}
