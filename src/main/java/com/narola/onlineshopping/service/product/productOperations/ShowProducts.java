package com.narola.onlineshopping.service.product.productOperations;

import com.narola.onlineshopping.service.cart.CartManager;
import com.narola.onlineshopping.service.order.OrderManager;
import com.narola.onlineshopping.service.product.ProductManager;
import com.narola.onlineshopping.service.product.productOperations.ViewProductsInOrder.SortByPrice;
import com.narola.onlineshopping.service.product.productOperations.ViewProductsInOrder.SortByPriceAsc;
import com.narola.onlineshopping.service.product.productOperations.ViewProductsInOrder.ViewProductByCategory;
import com.narola.onlineshopping.service.product.productOperations.ViewProductsInOrder.ViewProductById;
import com.narola.onlineshopping.OnlineShoppingApplication;
import com.narola.onlineshopping.dao.ProductDao;
import com.narola.onlineshopping.display.Display;
import com.narola.onlineshopping.enums.UserRoles;
import com.narola.onlineshopping.exception.DAOLayerException;
import com.narola.onlineshopping.input.InputHandler;
import com.narola.onlineshopping.system.ProgramTerminator;
import com.narola.onlineshoppingV1.session.LoggedInUser;

import static com.narola.onlineshopping.constant.AppConstant.*;

public class ShowProducts {

    public static void viewProducts() {
        try {
            if (!ProductDao.doProductsExists()) {
                System.out.println("No products are present.");
                return;
            }

            System.out.println("Select the way of viewing products: ");
            System.out.println(VIEW_ALL_PRODUCTS + ". View all products.");
            System.out.println(VIEW_PRODUCTS_BY_HIGHEST_PRICE + ". View all by highest price.");
            System.out.println(VIEW_PRODUCTS_BY_LOWEST_PRICE + ". View all by lowest price.");
            System.out.println(VIEW_PRODUCTS_BY_CATEGORY + ". View products by category.");
            System.out.println(VIEW_PRODUCT_BY_ID + ". View Product by id.");
            if (LoggedInUser.currentUser.getUserRoleId() == UserRoles.ADMIN.getValue()) {
                System.out.println(MY_CART_OR_BACK + ". Back");
            } else {
                System.out.println(MY_CART_OR_BACK + ". My Cart");
                System.out.println(MY_ORDERS + ". My Orders");
                System.out.println(CUSTOMER_LOGOUT + ". Logout");
            }
            System.out.println(EXIT + ". Exit");

            int choice = InputHandler.getIntInput();
            switch (choice) {
                case VIEW_ALL_PRODUCTS:
                    Display.printProducts(ProductDao.getALlProducts());
                    break;
                case VIEW_PRODUCTS_BY_HIGHEST_PRICE:
                    SortByPrice sortByPrice = new SortByPrice(ProductDao.getALlProducts());
                    Display.printProducts(sortByPrice.sortByPrice());
                    break;
                case VIEW_PRODUCTS_BY_LOWEST_PRICE:
                    SortByPriceAsc sortByPriceAsc = new SortByPriceAsc(ProductDao.getALlProducts());
                    Display.printProducts(sortByPriceAsc.sortByPriceAsc());
                    break;
                case VIEW_PRODUCTS_BY_CATEGORY:
                    ViewProductByCategory viewProductByCategory = new ViewProductByCategory();
                    Display.printProducts(viewProductByCategory.viewProductByCategory());
                    break;
                case VIEW_PRODUCT_BY_ID:
                    ViewProductById.getProductById();
                    break;
                case MY_CART_OR_BACK:
                    if (LoggedInUser.currentUser.getUserRoleId() == UserRoles.CUSTOMER.getValue()) {
                        CartManager.displayCartOptions();
                    } else {
                        ProductManager.handleProductManagement();
                    }
                    break;
                case MY_ORDERS:
                    if (LoggedInUser.currentUser.getUserRoleId() == UserRoles.CUSTOMER.getValue()) {
                        OrderManager.handleOrderManagement();
                    } else {
                        System.out.println("Please enter valid choice.");
                    }
                    break;
                case CUSTOMER_LOGOUT:
                    if (LoggedInUser.currentUser.getUserRoleId() == UserRoles.CUSTOMER.getValue()) {
                        OnlineShoppingApplication.main(null);
                    } else {
                        System.out.println("Please enter valid choice.");
                    }
                    break;
                case EXIT:
                    ProgramTerminator.exit();
                default:
                    System.out.println("Please enter valid choice.");
                    break;
            }
            viewProducts();
        } catch (DAOLayerException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
