package purchase.service;

import purchase.Purchase;
import request.product.ProductRequest;
import tcvreader.DetermineCategoryFromTsv;

/**
 * Класс - сервис управления покупками
 */
public class PurchaseService {
    private final Purchase purchase;
    private final DetermineCategoryFromTsv categoryFromTsv;


    public PurchaseService(Purchase purchase, DetermineCategoryFromTsv categoryFromTsv) {
        this.purchase = purchase;
        this.categoryFromTsv = categoryFromTsv;
    }

    public void addProduct(ProductRequest data) {
        String nameCategory = categoryFromTsv.getCategoryByProduct(data.getTitle());
        purchase.addPurchaseProduct(
                new ProductRequest(data.getTitle(), nameCategory, data.getDate(), data.getSum())
        );
    }
}
