import java.util.concurrent.atomic.AtomicInteger;

public class Expense {
    private String title;
    private Double price;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;

    public Expense(String title, Double price) {
        this.title = title;
        this.price = price;
        this.id = count.incrementAndGet();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" +
                "title='" + title + '\'' +
                ", price=" + price;
    }
}
