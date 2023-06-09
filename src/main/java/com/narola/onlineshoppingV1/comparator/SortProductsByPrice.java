package com.narola.onlineshoppingV1.comparator;

import com.narola.onlineshoppingV1.model.Product;

import java.util.Comparator;

public class SortProductsByPrice implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (Float.valueOf(o1.getProductPrice()).compareTo(Float.valueOf(o2.getProductPrice())));
    }
}
