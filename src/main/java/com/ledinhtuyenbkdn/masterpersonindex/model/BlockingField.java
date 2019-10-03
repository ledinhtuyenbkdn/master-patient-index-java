package com.ledinhtuyenbkdn.masterpersonindex.model;

import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Field;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class BlockingField {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Field field;

    @ManyToOne
    private BlockingRound blockingRound;

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

    public BlockingRound getBlockingRound() {
        return blockingRound;
    }

    public void setBlockingRound(BlockingRound blockingRound) {
        this.blockingRound = blockingRound;
    }

    @Override
    public String toString() {
        return "BlockingField{" +
                "id=" + id +
                ", field=" + field +
                '}';
    }
}
