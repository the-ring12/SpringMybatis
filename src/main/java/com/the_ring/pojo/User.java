package com.the_ring.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private Integer sex;
}
