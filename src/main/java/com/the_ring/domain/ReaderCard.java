package com.the_ring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderCard implements Serializable{

    private int readerId;
    private String name;
    private String passwd;
    private int cardState;

}
