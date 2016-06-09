import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private Label userAddField;
    @FXML
    private ListView<User> userList;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private TextField idUserField;
    @FXML
    private TextField expenseField;
    @FXML
    private TextField costField;
    @FXML
    private Label expenseAddLabel;
    @FXML
    private TextArea smalTransactArea;
    @FXML
    private Label averageLabel;
    @FXML
    private Label allExpense;
    @FXML
    public void onButtonUserAddClicked() {
        // Add user
        if (!nameField.getText().trim().isEmpty() && !surnameField.getText().trim().isEmpty()) {
            User user = new User(nameField.getText(), surnameField.getText());
            Main.setUsers(user);
            nameField.clear();
            surnameField.clear();
            userAddField.setText("User added");
        } else {
            userAddField.setText("Name or surname field - empty! ");
        }
        initialize();
    }

    public void initialize() {
        userList.getItems().setAll(Main.getUsers());
    }

    @FXML
    public void handleClickListView() {
        // Choose user in list view
        User item = userList.getSelectionModel().getSelectedItem();
        String allExp = "";
        System.out.println("The selected item is " + item);
        for (int i = 0; i < item.getExp().size(); i++) {
            allExp += item.getExp().get(i).toString() + "\n";
            System.out.println(item.getExp().get(i).toString());
        }
        itemDetailsTextArea.setText(allExp);
        idUserField.setText(String.valueOf(item.getId()));
    }

    @FXML
    public void onButtonExpenseAddClicked() {
        // Add expense
        if (!idUserField.getText().trim().isEmpty() && !expenseField.getText().trim().isEmpty() && !costField.getText().trim().isEmpty()) {
            for (int i = 0; i < Main.getUsers().size(); i++) {
                if (Main.getUsers().get(i).getId() == Integer.parseInt(idUserField.getText())) {
                    Main.getUsers().get(i).getExp().add(new Expense(expenseField.getText(), Double.parseDouble(costField.getText())));
                    expenseField.clear();
                    costField.clear();
                }
            }
            expenseAddLabel.setText("\t\n" +
                    "Expense add");
        } else {
            expenseAddLabel.setText("\t\n" + "You have empty field");
        }
        System.out.println(Main.getUsers().toString());
        allExpense.setText(Main.findAllExpense().toString());
        initialize();
    }

    public void onButtonFindMinExpAddClicked() {
        //Find transacts between users
        Main.makeExpenseList();
        Main.findSmallTransact();
        smalTransactArea.setText(Main.finalS);
        Main.finalS = "";
        averageLabel.setText(String.valueOf("Average expense: " + Main.findAverageExpense()));
    }

    public void onButtonRemoveUser() {
        //Remove user
        User item = userList.getSelectionModel().getSelectedItem();
        Main.getUsers().remove(item);
        userList.getItems().setAll(Main.getUsers());
    }
}
