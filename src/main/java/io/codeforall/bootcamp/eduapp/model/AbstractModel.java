package io.codeforall.bootcamp.eduapp.model;

import jakarta.persistence.*;


/**
 * A generic model domain entity to be used as a base for concrete types of models
 */
@MappedSuperclass
public abstract class  AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Version
    private Integer version;


    public int getId() {
        return id;
    }

    /**
     * Get the version
     * @return the version
     */
    public int getVersion() {
        return version;
    }



    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the version
     * @param version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }



}
