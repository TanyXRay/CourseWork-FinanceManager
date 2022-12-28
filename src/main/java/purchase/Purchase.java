package purchase;

import request.product.ProductRequest;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private final List<ProductRequest> purchases = new ArrayList<>();

    public void addPurchaseProduct(ProductRequest productRequest) {
        purchases.add(productRequest);
    }

    public List<ProductRequest> getPurchases() {
        return purchases;
    }
}
