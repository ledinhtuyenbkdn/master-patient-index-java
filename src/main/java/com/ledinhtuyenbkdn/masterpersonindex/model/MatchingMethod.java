package com.ledinhtuyenbkdn.masterpersonindex.model;

import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Algorithm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class MatchingMethod {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Algorithm algorithm;

    @OneToMany(mappedBy = "matchingMethod")
    private List<FieldWeight> fieldWeights;

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

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public List<FieldWeight> getFieldWeights() {
        return fieldWeights;
    }

    public void setFieldWeights(List<FieldWeight> fieldWeights) {
        this.fieldWeights = fieldWeights;
    }

    @Override
    public String toString() {
        return "MatchingMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", algorithm=" + algorithm +
                ", fieldWeights=" + fieldWeights +
                '}';
    }
}
