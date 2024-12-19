package io.reactivestax.repo.hibernate;

import io.reactivestax.entity.Node;
import io.reactivestax.utilities.database.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateReadFromNodeRepo {
    public List<Node> getData() {
        Session session = HibernateUtil.getInstance().getConnection();

        Query query = session.createQuery("from Node");
        return query.getResultList();
    }
}
