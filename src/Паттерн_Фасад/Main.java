// 7 ВАРИАНТ. Реализуйте паттерн "Фасад" для системы управления заказами в интернет-магазине,
// позволяющий покупателям просматривать товары, добавлять их в корзину и оформлять заказ.
package Паттерн_Фасад;

import java.awt.*;
import java.util.ArrayList;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Класс, представляющий корзину покупок
class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    // Метод для добавления товара в корзину
    public void addProduct(Product product) {
        products.add(product);
    }

    // Метод для вычисления общей стоимости товаров в корзине
    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }
}

class Order {
    private ShoppingCart cart;
    private String customerName;
    private String address;

    public Order(ShoppingCart cart, String customerName, String address) {
        this.cart = cart;
        this.customerName = customerName;
        this.address = address;
    }

    // Метод для оформления заказа
    public void placeOrder() {
        System.out.println("Заказ для клиента: " + customerName);
        System.out.println("Адрес доставки: " + address);
        System.out.println("Товары в заказе:");
        for (Product product : cart.getProducts()) {
            System.out.println("- " + product.getName() + " за " + product.getPrice() + " руб.");
        }
        System.out.println("Общая стоимость заказа: " + cart.getTotal() + " руб.");
    }
}

// Фасад, предоставляющий упрощенный интерфейс для работы с заказами
class OrderFacade {
    private ShoppingCart cart;
    private List<Product> availableProducts;

    public OrderFacade() {
        cart = new ShoppingCart();
        availableProducts = new ArrayList<>();
        availableProducts.add(new Product("Телефон", 15000));
        availableProducts.add(new Product("Ноутбук", 50000));
        availableProducts.add(new Product("Наушники", 3000));
    }

    public void displayProducts() {
        System.out.println("Доступные товары:");
        for (Product product : availableProducts) {
            System.out.println("- " + product.getName() + " за " + product.getPrice() + " руб.");
        }
    }

    public void addToCart(String productName) {
        for (Product product : availableProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                cart.addProduct(product);
                System.out.println("Товар добавлен в корзину: " + product.getName());
                return;
            }
        }
        System.out.println("Товар не найден: " + productName);
    }

    public void placeOrder(String customerName, String address) {
        Order order = new Order(cart, customerName, address);
        order.placeOrder();
    }
}

public class Main {
    public static void main(String[] args) {

        OrderFacade orderFacade = new OrderFacade();

        orderFacade.displayProducts();

        orderFacade.addToCart("Телефон");
        orderFacade.addToCart("Ноутбук");

        orderFacade.placeOrder("Иван Иванов", "Москва, ул. Ленина, 10");
    }
}

