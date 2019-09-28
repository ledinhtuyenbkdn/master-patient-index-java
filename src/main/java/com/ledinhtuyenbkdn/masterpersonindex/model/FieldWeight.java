package com.ledinhtuyenbkdn.masterpersonindex.model;

import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Field;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FieldWeight {

    @Id
    @GeneratedValue
    private Long id;

    private Field field;

    @ManyToOne
    private MatchingMethod matchingMethod;

    private Integer weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public MatchingMethod getMatchingMethod() {
        return matchingMethod;
    }

    public void setMatchingMethod(MatchingMethod matchingMethod) {
        this.matchingMethod = matchingMethod;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "FieldWeight{" +
                "id=" + id +
                ", field=" + field +
                ", matchingMethod=" + matchingMethod +
                ", weight=" + weight +
                '}';
    }
}
