package com.narola.onlineshopping.comparator;

import com.narola.onlineshopping.model.Category;
import java.util.Comparator;

public class SortCategoryByDate implements Comparator<Category> {
    @Override
    public int compare(Category o1, Category o2) {
        if(o1.getCreatedOn().compareTo(o2.getCreatedOn()) > 0) {
            return -1;
        }
        else if(o1.getCreatedOn().compareTo(o2.getCreatedOn()) == 0) {
            return 0;
        }
        return 1;
    }
}
