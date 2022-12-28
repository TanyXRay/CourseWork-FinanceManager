package report.category;

public class ReportCategory {
    private String category;
    private Long sum;

    public ReportCategory(String category, Long sum) {
        this.category = category;
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}

