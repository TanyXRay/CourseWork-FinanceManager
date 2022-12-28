package tcvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetermineCategoryFromTsv {
    private final String tsvFile = "categories.tsv";
    private final HashMap<String, List<String>> categoryProducts = new HashMap<>();

    public DetermineCategoryFromTsv() {
        try {
            fillCategoriesFromTsv(tsvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, List<String>> getCategoryProducts() {
        return categoryProducts;
    }

    private void fillCategoriesFromTsv(String tcvFile) throws IOException {
        try (BufferedReader TSVFile = new BufferedReader(new FileReader(tcvFile))) {
            String dataRaw;
            while ((dataRaw = TSVFile.readLine()) != null) {
                String[] categoryProduct = dataRaw.split("\\t");
                String productItem = categoryProduct[0];
                String categoryItem = categoryProduct[1];

                if (categoryProducts.containsKey(categoryItem)) {
                    List<String> products = categoryProducts.get(categoryItem);
                    products.add(productItem);
                } else {
                    List<String> products = new ArrayList<>();
                    products.add(productItem);
                    categoryProducts.put(categoryItem, products);
                }
            }
        }
    }

    public String getCategoryByProduct(String product) {
        for (Map.Entry<String, List<String>> item : categoryProducts.entrySet()) {
            String category = item.getKey();
            List<String> products = item.getValue();

            for (String productName : products) {
                if (productName.equals(product)) {
                    return category;
                }
            }
        }
        return "другое";
    }
}


