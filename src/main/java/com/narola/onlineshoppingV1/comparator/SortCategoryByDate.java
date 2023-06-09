package com.narola.onlineshoppingV1.comparator;

import com.narola.onlineshoppingV1.model.Category;

import java.util.Comparator;

public class SortCategoryByDate implements Comparator<Category> {
    @Override
    public int compare(Category o1, Category o2) {
        if (o1.getCreatedOn().compareTo(o2.getCreatedOn()) > 0) {
            return -1;
        } else if (o1.getCreatedOn().compareTo(o2.getCreatedOn()) == 0) {
            return 0;
        }
        return 1;
    }
}
