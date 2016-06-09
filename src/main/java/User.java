import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by valerijszemlanikins on 30/05/16.
 */
public class User {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String name;
    private String surname;
    private LinkedList<Expense> exp;


    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.id = count.incrementAndGet();
        this.exp = new LinkedList<Expense>();
    }

    public void addExpence(String title, double price) {
        this.exp.add(new Expense(title, price));

    }

    private Expense findExpence(int id) {
        for (Expense checkExpense : this.exp) {
            if (checkExpense.getId() == id) {
                return checkExpense;
            }
        }
        return null;
    }

    public LinkedList<Expense> getExp() {
        return exp;
    }

    public void setExp(LinkedList<Expense> exp) {
        this.exp = exp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return this.name + "  " + this.surname + " id: " + this.id;
    }

}
