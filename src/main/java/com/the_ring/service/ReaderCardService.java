package com.the_ring.service;

import com.the_ring.domain.ReaderCard;
import com.the_ring.domain.ReaderInfo;

public interface ReaderCardService {

    int getMatchCount(int readerId, String passwd);

    ReaderCard findReaderByReaderId(int readerId);

    int rePassword(int readerId, String newPasswd);

    int addReaderCard(ReaderInfo readerInfo);

    int updateName(int readerId, String name);
}
