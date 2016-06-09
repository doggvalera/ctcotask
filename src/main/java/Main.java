import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main extends Application {

    private static LinkedList<User> users = new LinkedList<User>();
    private static LinkedList<ExpenseList> allexpense = new LinkedList<ExpenseList>();
    public static String finalS = new String(); // Save all transacts between users;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Expense finder");
        primaryStage.setScene(new Scene(root, 1000, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ArrayList<String> findAllExpense() {
        // Find sum of the full amount of each person's expenses
        ArrayList<String> allExpense = new ArrayList<String>();
        double findMiddleExp = 0;
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getExp().size(); j++) {
                findMiddleExp += users.get(i).getExp().get(j).getPrice();
            }
            allExpense.add(users.get(i).toString() + "  = " + String.valueOf(findMiddleExp));
            findMiddleExp = 0;
        }
        return allExpense;
    }

    public static double findAverageExpense() {
        // Find average expense
        double allExpense = 0;
        double averageExpense = 0;
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getExp().size(); j++) {
                allExpense += users.get(i).getExp().get(j).getPrice();
            }
        }
        averageExpense = allExpense / users.size();
        return averageExpense;
    }

    private static double findDifferentExpense(int id) {
        // Find all difference between user and average expense
        if (userContains(id) != null) {
            double differentSum = 0;
            double sum = 0;
            for (int i = 0; i < userContains(id).getExp().size(); i++) {
                sum += userContains(id).getExp().get(i).getPrice();
                differentSum = sum - findAverageExpense();
            }
            return differentSum;
        }
        return 0;
    }

    public static User userContains(int id) {
        // Does user exist with ID
        if (users != null) {
            User useCont = null;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == id) {
                    useCont = users.get(i);
                    break;
                }
            }
            return useCont;
        }
        return null;
    }

    public static void makeExpenseList() {
        allexpense.clear();
        int user = 0;
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i).getId();
            ExpenseList explist = new ExpenseList(user, findDifferentExpense(user));
            allexpense.add(explist);
        }
    }

    public static void findSmallTransact() {
        // Find the greatest and the lowest difference between average value
        double min = 99999999; //min difference
        double max = 0; // max difference
        double result = 0; // difference between greatest and lowest values
        int min_id = 0;
        int max_id = 0;
        String nameGive = null;
        String nameTake = null;
        for (int i = 0; i < allexpense.size(); i++) {
            if (allexpense.get(i).getCost() < min) {
                min = allexpense.get(i).getCost();
                min_id = i;
            }
            if (allexpense.get(i).getCost() > max) {
                max = allexpense.get(i).getCost();
                max_id = i;
            }
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == allexpense.get(min_id).getUserid()) {
                nameGive = users.get(i).toString();
            }
            if (users.get(i).getId() == allexpense.get(max_id).getUserid()) {
                nameTake = users.get(i).toString();
            }
        }
        if (min * min < max * max) {
            result = max + min;
            if (min < 0) {
                finalS += nameGive + " give money -->> " + (-min) + " to " + " " + nameTake + "\n";
            } else {
                finalS += nameGive + " give money -->> " + (max) + " to " + " " + nameTake + "\n";
            }
            allexpense.get(max_id).setCost(result);
            allexpense.remove(min_id);
        }
        if (min * min > max * max) {
            result = max + min;
            finalS += nameGive + " give money -->> " + (max) + " to " + " " + nameTake + "\n";
            allexpense.get(min_id).setCost(result);
            allexpense.remove(max_id);
        }
        try {
            if (min * min == max * max) {
                finalS += nameGive + " give money -->> " + (max) + " to " + " " + nameTake + "\n";
                allexpense.remove(min_id);
                allexpense.remove(max_id);
            }
        } catch (Exception ignored) {

        }
        if (allexpense.size() > 1) {
            findSmallTransact();
        }
    }

    public static LinkedList<User> getUsers() {
        return users;
    }

    public static void setUsers(User user) {

        Main.users.add(user);
    }

    public static LinkedList<ExpenseList> getAllexpense() {
        return allexpense;
    }

    public static void setAllexpense(LinkedList<ExpenseList> allexpense) {
        Main.allexpense = allexpense;
    }
}


