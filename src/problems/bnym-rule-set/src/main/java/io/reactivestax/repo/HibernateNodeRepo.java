package io.reactivestax.repo;

import io.reactivestax.service.Node;
import io.reactivestax.utilities.database.hibernate.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

public class HibernateNodeRepo {
    public void insertToNodeTable(Node node) {
//        HibernateUtil.startTransaction();
//        Session session = HibernateUtil.getInstance().getConnection();
//
//        Node nodeEntity = new Node();
//        nodeEntity.setData(node.getData());
//        nodeEntity.setLeft(node.getLeft());
//        nodeEntity.setRight(node.getRight());
//
//        session.persist(nodeEntity);
//        HibernateUtil.commitTransaction();
    }

    public void updateNodeTable(Node node) {
//        try {
//            HibernateUtil.startTransaction();
//            Session session = HibernateUtil.getInstance().getConnection();
//
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaUpdate<io.reactivestax.service.Node> query = builder.createCriteriaUpdate(io.reactivestax.service.Node.class);
//            Root<io.reactivestax.service.Node> root = query.from(io.reactivestax.service.Node.class);
//
//            Predicate nodePredict = builder.equal(root.get("parentId"), node.getParentId());
//
//            query.set("right", node.getRight()).where(nodePredict);
//
//            session.createQuery(query).executeUpdate();
//            HibernateUtil.commitTransaction();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}