package com.ledinhtuyenbkdn.masterpersonindex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class BlockingRound {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "blockingRound")
    private List<BlockingField> blockingFields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BlockingField> getBlockingFields() {
        return blockingFields;
    }

    public void setBlockingFields(List<BlockingField> blockingFields) {
        this.blockingFields = blockingFields;
    }

    @Override
    public String toString() {
        return "BlockingRound{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blockingFields=" + blockingFields +
                '}';
    }
}
