package report.service;

import manager.FinanceManager;
import request.product.ProductRequest;
import report.category.ReportMaxCategory;
import purchase.service.PurchaseService;

/**
 * Класс управления отправки ответа серверу максимальной категории продуктов
 */
public class ReportService {
    private final FinanceManager financeManager;
    private final PurchaseService purchaseService;

    public ReportService(FinanceManager financeManager, PurchaseService purchaseService) {
        this.financeManager = financeManager;
        this.purchaseService = purchaseService;
    }

    public ReportMaxCategory buyItem(ProductRequest productRequest) {
        purchaseService.addProduct(productRequest);
        return financeManager.fillReport();
    }
}
