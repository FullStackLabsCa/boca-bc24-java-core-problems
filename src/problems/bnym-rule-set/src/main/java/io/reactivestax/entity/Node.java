package io.reactivestax.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "node")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parentId")
    int parentId;
    @Column(name = "data")
    String data;
    @Column(name = "leftNode")
    int left;
    @Column(name = "rightNode")
    int right;
}
