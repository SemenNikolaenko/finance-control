package com.money.demo.model;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
@NamedEntityGraph(name = "getSubcategoryWithEntry",
        attributeNodes = @NamedAttributeNode(value = "listOfEntry"))
@Entity
@Data
@Table(schema = "app")
public class Subcategory {
    @Id
    @GeneratedValue
    @Getter
    private Long id;
    @NotNull
    @Size(min = 3,message = "name can't be shortly 3 characters")
    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "subcategory",fetch = FetchType.LAZY,targetEntity = Entry.class,cascade = CascadeType.ALL)
    private List<Entry> listOfEntry = new ArrayList<>();
    @Transient
    private int totalExpenses;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subcategory that = (Subcategory) o;
        return name.equals(that.name) &&
                category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    @Override
    public String toString() {
        return name;
    }
}
