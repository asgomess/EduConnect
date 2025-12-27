package io.codeforall.bootcamp.eduapp.persistence.daos.jpa;


import io.codeforall.bootcamp.eduapp.model.Appointment;
import io.codeforall.bootcamp.eduapp.persistence.managers.jpa.JpaSessionManager;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class JpaGenericDao<T> implements Dao<T> {

    private JpaSessionManager sm;
    private Class<T> modelType;

    public JpaGenericDao(Class<T> modelType) {
        this.modelType = modelType;
    }


    @Autowired
    public void setSm(JpaSessionManager sm) {
        this.sm = sm;
    }

    /**
     * @see Dao#findAll()
     */
    @Override
    public List<T> findAll() {

        System.out.println("called abstract generic findAll() - got new session manager");

        // Using criteria query
        /* CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(modelType);
        Root<T> root = criteriaQuery.from(modelType);
        return em.createQuery(criteriaQuery).getResultList();
         */

        return sm.getCurrentSession()
                .createQuery("FROM " + modelType.getSimpleName(), modelType)
                .getResultList();

    }


    /**
     * @see Dao#findById(Integer)
     */


    @Override
    public T findById(Integer id) {
        EntityManager em = sm.getCurrentSession();

        return em.find(modelType, id);
    }


    public T findByEmail(String email) {
        EntityManager em = sm.getCurrentSession();

        return em.createQuery("SELECT t FROM " + modelType.getSimpleName() + " t WHERE t.email = :email", modelType)
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<Appointment> findAllByTeacher(Integer teacherId) {
        return sm.getCurrentSession()
                .createQuery("SELECT a FROM Appointment a WHERE a.teacher.id = :teacherId", Appointment.class)
                .setParameter("teacherId", teacherId)
                .getResultList();
    }

    public T saveOrUpdate(T modelObject) {
        EntityManager em = sm.getCurrentSession();
        return em.merge(modelObject);
    }

    /**
     * @see Dao#delete(Integer)
     */


    public void delete(Integer id) {
        EntityManager em = sm.getCurrentSession();

        em.remove(em.find(modelType, id));
    }



}
