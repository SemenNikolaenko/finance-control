package com.money.demo.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.*;

@NamedEntityGraph(name = "getCategoryWithSubcategories",
        attributeNodes = @NamedAttributeNode(value = "subcategoryList"))
@Entity
@Data
@Table(schema = "app", name = "category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 3,message = "name can't be shortly 3 characters")
    private String name;
    private String definition;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Subcategory.class)
    private List<Subcategory> subcategoryList = new ArrayList<>();
    @Transient
    private int finalAmountExpenses;

    public Category(@NotNull @Size(min = 3, message = "name can't be shortly 3 characters") String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

    public Category() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name) &&
                Objects.equals(definition, category.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, definition);
    }

    @Override
    public String toString() {
        return name;
    }
}
