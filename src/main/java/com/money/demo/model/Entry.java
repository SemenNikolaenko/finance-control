package com.money.demo.model;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(schema = "app")
@Data
public class Entry {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    @Column(name = "amount_money")
    @NotNull
    private int amountOfMoney;
    @Column(name = "date")
    @CreationTimestamp
    private Date dateOfCreate;
    @NotNull
    @ManyToOne()
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;


    @Override
    public String toString() {
        return "Entry created "+ dateOfCreate+" when "+text+" and pay "+amountOfMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return amountOfMoney == entry.amountOfMoney &&
                this.text.equals(entry.getText()) &&
                this.getSubcategory().getId()==entry.getSubcategory().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, amountOfMoney, subcategory);
    }
}
