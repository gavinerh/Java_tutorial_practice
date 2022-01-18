package SectionA.Question3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args){
        Product[] productArr = {
                new Product(1, "Farm Railway Set", "Toy", 75.9),
                new Product(2, "Data Structures", "Book", 75.00),
                new Product(3, "Laptop Desks", "Office", 139.0),
                new Product(4, "Lego City", "Toy", 62.0),
                new Product(5, "Foldable Table", "Office", 47.9),
                new Product(6, "Jigsaw Puzzle", "Toy", 20.0),
                new Product(7, "Java How To Program", "Book", 80.5),
                new Product(8, "ASP.NET Core in Action", "Book", 50.5),
                new Product(9, "Office Chair", "Office", 57.7)
        };
        List<Product> productList = Arrays.asList(productArr);
        List<Product> searchResult = search(productList, "Toy", 62.0); System.out.println(searchResult);
        System.out.println(); // New line
        printByCategory(productList);
    }

    public static List<Product> search(List<Product> list, String category, double price){
        // belong to given category and < given price
        return list.stream()
                .filter(x-> x.getCategory() == category && x.getPrice() <= price)
                .collect(Collectors.toList());
    }

    public static void printByCategory(List<Product> list){
        // print each category and 2 most expensive products
        Map<String, List<Product>> groupByCategory =
                list.stream()
                        .collect(Collectors.groupingBy(Product::getCategory));
        groupByCategory.forEach((category, products)->
        {
            System.out.println(category);
            products.stream().sorted(Comparator.comparing(Product::getPrice).reversed())
                    .limit(2)
                    .forEach(product ->{
                System.out.println(String.format("\t%s %s", product.getName(), product.getPrice()));
            });
        });
    }
}
