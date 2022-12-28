package manager;

import request.product.ProductRequest;
import purchase.Purchase;
import report.category.ReportCategory;
import report.category.ReportMaxCategory;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

/**
 * Класс - финансовый менеджер, где происходит расчет максимальной категории, исходя из покупок
 */
public class FinanceManager {
    private final Purchase purchase;

    public FinanceManager(Purchase purchase) {
        this.purchase = purchase;
    }

    /**
     * Метод заполнения объекта максимальной категории для передачи ответа серверу
     *
     * @return
     */
    public ReportMaxCategory fillReport() {
        ReportMaxCategory summaryReportCategory = new ReportMaxCategory();
        List<ProductRequest> purchaseProducts = purchase.getPurchases();
        summaryReportCategory.setMaxCategory(fillCategory(purchaseProducts));
        return summaryReportCategory;
    }

    /**
     * Метод расчета основной максимальной категории
     *
     * @param productByTime
     * @return
     */
    private ReportCategory fillCategory(List<ProductRequest> productByTime) {
        if (productByTime != null && productByTime.size() == 0) {
            return null;
        }
        Map<String, Double> categorySummary = productByTime.stream()
                .collect(groupingBy(ProductRequest::getCategory, summingDouble(ProductRequest::getSum)));

        Map.Entry<String, Double> maxCategorySum = categorySummary.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();
        return new ReportCategory(maxCategorySum.getKey(), maxCategorySum.getValue().longValue());
    }
}
