package request.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Класс - продукт по запросу от клиента
 */
@JsonIgnoreProperties(value = {"category"})
public class ProductRequest {
    public String title;
    public String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    public Date date;
    public double sum;

    public ProductRequest(String title, String category, Date date, double sum) {
        this.title = title;
        this.category = category;
        this.date = date;
        this.sum = sum;
    }

    public ProductRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
