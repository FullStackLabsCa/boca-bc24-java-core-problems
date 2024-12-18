package io.reactivestax.service;

import java.util.List;

public interface NodeInsertService {
    void insertToNodeTable();

    List<io.reactivestax.entity.Node> getData();
}
