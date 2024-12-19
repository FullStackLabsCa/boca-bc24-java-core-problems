package io.reactivestax.repo.hibernate;

import io.reactivestax.model.Node;
import io.reactivestax.utilities.database.hibernate.HibernateUtil;
import org.hibernate.Session;

import static io.reactivestax.service.LineHandler.nodeList;

public class HibernateInsertToNodeRepo {

    public void insertToNodeTable() {
        while (!nodeList.isEmpty()) {

            HibernateUtil.startTransaction();
            Session session = HibernateUtil.getConnection();

            int batchSize = 50;
            int count = 0;

            for (Node node : nodeList) {
                io.reactivestax.entity.Node nodeEntity = new io.reactivestax.entity.Node();
                nodeEntity.setParentId(node.getParentId());
                nodeEntity.setData(node.getData());
                nodeEntity.setLeft(node.getLeft());
                nodeEntity.setRight(node.getRight());

                session.persist(nodeEntity);

                if (++count % batchSize == 0) {
                    session.flush();
                    session.clear();
                }
            }
            HibernateUtil.commitTransaction();

            nodeList.clear();
        }
    }
}