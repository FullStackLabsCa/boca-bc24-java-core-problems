package io.reactivestax.repo.hibernate;

import io.reactivestax.model.Node;
import io.reactivestax.utilities.database.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static io.reactivestax.service.LineHandler.nodeList;

public class HibernateNodeRepo {
    public void insertToNodeTable() {
        while (!nodeList.isEmpty()) {

            HibernateUtil.startTransaction();
            Session session = HibernateUtil.getConnection();

            for (Node node : nodeList) {
                io.reactivestax.entity.Node nodeEntity = new io.reactivestax.entity.Node();
                nodeEntity.setParentId(node.getParentId());
                nodeEntity.setData(node.getData());
                nodeEntity.setLeft(node.getLeft());
                nodeEntity.setRight(node.getRight());

                session.persist(nodeEntity);
            }
            HibernateUtil.commitTransaction();

            nodeList.clear();
        }
    }

    public List getData() {
        HibernateUtil.startTransaction();
        Session session = HibernateUtil.getInstance().getConnection();

        Query query = session.createQuery("from Node");
        return query.list();
//        for (io.reactivestax.entity.Node node : nodeList) {
//            System.out.println(node.getParentId() + node.getData());
//        }
    }
}