import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.LinkedList;

public class Main extends Application {

    private static LinkedList<User> users = new LinkedList<User>();
    private static LinkedList<ExpenseList> allexpense = new LinkedList<ExpenseList>();
    public static String finalS = new String();
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

    private static void addUser(String name, String surname) {
        User user = new User(name, surname);
        users.add(user);

    }

    private static double findAllExpense() {
        double allexpence = 0;

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getExp().size(); j++) {
                allexpence += users.get(i).getExp().get(j).getPrice();
            }
        }
        return allexpence;
    }

    public static double findAverageExpense() {
        double allexpence = 0;
        double avarageExpence = 0;

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getExp().size(); j++) {
                allexpence += users.get(i).getExp().get(j).getPrice();
            }
        }
        avarageExpence = allexpence / users.size();
        return avarageExpence;
    }

    private static double findDifferentExpense(int id) {
        if (userContains(id) != null) {
            double differentSumm = 0;
            double summ = 0;
            for (int i = 0; i < userContains(id).getExp().size(); i++) {
                summ += userContains(id).getExp().get(i).getPrice();
                differentSumm = summ - findAverageExpense();
            }
            return differentSumm;
        }
        return 0;
    }

    public static User userContains(int id) {
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

    public static void findSmalltransact() {
        double min = 99999999;
        double max = 0;
        double result = 0;
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

            finalS += nameGive + " give money -->> " + (max) + " to " +  " "  + nameTake + "\n";
            System.out.println(nameGive + "give money " + min + "and " + max + " "  +  nameTake);
            allexpense.get(max_id).setCost(result);
            allexpense.remove(min_id);
        }
        if (min * min > max * max)
        {
            result = max + min;
            finalS += nameGive + " give money -->> " + (max) +  " to " +  " " + nameTake + "\n";
            System.out.println(nameGive + "give money " + min +  " and  " + max  + nameTake);
            allexpense.get(min_id).setCost(result);
            allexpense.remove(max_id);
        }
        try {
            if (min * min == max * max) {
                finalS += nameGive + " give money -->> " + (max) + " to " + " " + nameTake + "\n";
                System.out.println(nameGive + "give money " + min + " and  " + max + nameTake);
                allexpense.remove(min_id);
                allexpense.remove(max_id);
            }
        }catch (Exception ignored){

        }

        if (allexpense.size() > 1) {
            findSmalltransact();
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


