package io.reactivestax.repo.hibernate;

import io.reactivestax.model.Node;
import io.reactivestax.utilities.database.hibernate.HibernateUtil;
import org.hibernate.Session;

import static io.reactivestax.service.LineHandler.nodeList;

public class HibernateNodeRepo {
    public void insertToNodeTable() {
        while (!nodeList.isEmpty()) {

            HibernateUtil.startTransaction();
            Session session = HibernateUtil.getConnection();

            for (Node node : nodeList) {
                io.reactivestax.entity.Node nodeEntity = new io.reactivestax.entity.Node();
                nodeEntity.setData(node.getData());
                nodeEntity.setLeft(node.getLeft());
                nodeEntity.setRight(node.getRight());

                session.persist(nodeEntity);
            }
            HibernateUtil.commitTransaction();

            nodeList.clear();
        }
    }
}