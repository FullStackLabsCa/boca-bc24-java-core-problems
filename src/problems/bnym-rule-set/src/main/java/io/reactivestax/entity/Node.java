package io.reactivestax.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "node")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "keyId")
    String parentId;
    @Column(name = "value")
    String data;
    @Column(name = "leftNode")
    int left;
    @Column(name = "rightNode")
    int right;
}
