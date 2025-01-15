import java.util.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(1L, "John Doe", 1);
        Customer customer1 = new Customer(1L, "Alice", 2);

        List<Product> products = Arrays.asList(
                new Product(1L, "Harry Potter", "Books", 100.0),
                new Product(1L, "Jobless Reincarnation", "Books", 120.0),
                new Product(2L, "Mario", "Toys", 200.0),
                new Product(3L, "Keyboard", "IT", 300.0),
                new Product(3L, "Diaper", "Baby", 20.0),
                new Product(3L, "Diaper of GOD", "Baby", 399.89),
                new Product(3L, "Diaper", "Boys", 20.0),
                new Product(3L, "Lego NinjaGO", "Boys", 399.89)
        );

        List<Product> products2 = Arrays.asList(
                new Product(1L, "Harry Potter", "Books", 100.0),
                new Product(1L, "Jobless Reincarnation", "Books", 120.0),
                new Product(2L, "Mario", "Toys", 200.0),
                new Product(3L, "Keyboard", "IT", 300.0)
        );

        List<Order> orders = Arrays.asList(
                new Order(
                        "Pending",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        products,
                        customer
                ),
                new Order(
                        "Pending",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        products,
                        customer1
                ),
                new Order(
                        "Pending",
                        LocalDate.now(),
                        LocalDate.now().plusDays(5),
                        products2,
                        customer
                ),
                new Order(
                        "Pending",
                        LocalDate.of(2021, 3, 1),
                        LocalDate.of(2021, 3, 5),
                        products2,
                        customer1
                )
        );
        List<Product> es1 = products.stream()
                .filter(product -> product.category.equals("books") && product.price > 100).toList();

        List<Order> es2 = orders.stream()
                .filter(order -> order.products.stream().anyMatch(product -> product.category.equals("baby"))).toList();

        List<Product> es3 = products.stream()
                .filter(product -> product.category.equals("Boys"))
                .map(product -> new Product(product.id, product.name, product.category, product.price * 0.9)).toList();

        List<Product> es4 = orders.stream()
                .filter(order -> order.customer.tier==2&&
                        order.orderDate.isAfter(LocalDate.of(2021,2,1))&&
                                order.orderDate.isBefore(LocalDate.of(2021,4,1))
                        ).flatMap(order -> order.products.stream()).toList();
    }
}