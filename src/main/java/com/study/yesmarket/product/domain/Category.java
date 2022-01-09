package com.study.yesmarket.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
@Entity
public class Category {

    @Id
    private String categoryCode;

    private String Name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    @Builder
    public Category(String categoryCode, String name) {
        this.categoryCode = categoryCode;
        this.Name = name;
    }
}
