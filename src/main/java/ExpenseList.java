/**
 * Created by valerijszemlanikins on 30/05/16.
 */
public class ExpenseList {
    private int userid;
    private double cost;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ExpenseList(int userid, double cost) {
        this.userid = userid;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ExpenceList{" +
                "userid=" + userid +
                ", cost=" + cost +
                '}';
    }
}
