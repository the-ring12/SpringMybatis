package com.the_ring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lend implements Serializable {

    private long sernum;
    private long bookId;
    private int readerId;
    private Date lendDate;
    private Date backDate;
}
