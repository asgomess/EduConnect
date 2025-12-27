package io.codeforall.bootcamp.eduapp.persistence.daos.jpa;

import java.util.List;

public interface Dao<T> {

    /**
     * Get a set of the model type
     * @return the model list
     *
     */
    List<T> findAll();

    /**
     * Get the model
     * @param id the model id
     * @return the model
     */
    T findById(Integer id);

    /**
     * Save or updates the model
     * @param modelObject the model to be saved or updated
     * @return the saved or updated model
     */
    T saveOrUpdate(T modelObject);

    /**
     * Delete the model
     * @param id the id of the model to be deleted
     */
    void delete(Integer id);


}
