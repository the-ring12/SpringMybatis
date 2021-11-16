package com.the_ring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderInfo implements Serializable{

    private int readerId;
    private String name;
    private String sex;
    private Date birth;
    private String address;
    private String telcode;
}
