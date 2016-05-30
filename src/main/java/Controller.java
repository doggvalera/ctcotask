import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * Created by valerijszemlanikins on 30/05/16.
 */
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
    public void onButtonUserAddClicked() {

        if(!nameField.getText().trim().isEmpty() && !surnameField.getText().trim().isEmpty()) {
            User user = new User(nameField.getText(), surnameField.getText());
            Main.setUsers(user);

            nameField.clear();
            surnameField.clear();
            userAddField.setText("User added");
        }
        else {
            userAddField.setText("Name or surname field empty! ");

        }


        System.out.println(Main.getUsers().toString());
        initialize();

    }
    public void initialize(){
        userList.getItems().setAll(Main.getUsers());
    }



    @FXML
    public void handleClickListView() {
        User item = userList.getSelectionModel().getSelectedItem();


        String allex = "";

        System.out.println("The selected item is " + item);
        for(int i=0;i<item.getExp().size();i++){
            allex += item.getExp().get(i).toString() + "\n";
            System.out.println(item.getExp().get(i).toString());


        }
        itemDetailsTextArea.setText(allex);

    }

    @FXML
    public void onButtonExpenseAddClicked() {

        if(!idUserField.getText().trim().isEmpty() && !expenseField.getText().trim().isEmpty() && !costField.getText().trim().isEmpty()) {

            for (int i = 0; i < Main.getUsers().size(); i++) {

                if (Main.getUsers().get(i).getId() == Integer.parseInt(idUserField.getText())) {
                    Main.getUsers().get(i).getExp().add(new Expense(expenseField.getText(), Double.parseDouble(costField.getText())));
                    expenseField.clear();
                    costField.clear();
                }
            }

            expenseAddLabel.setText("\t\n" +
                    "Expense add");
        }
        else {
            expenseAddLabel.setText("\t\n" + "You have empty field");

        }

        System.out.println(Main.getUsers().toString());
        initialize();

    }

    public void onButtonFindMinExpAddClicked(){
       Main.makeExpenseList();
       Main.findSmalltransact();
      smalTransactArea.setText(Main.finalS);
        Main.finalS = "";
    }

    public void onButtonRemoveUser(){

        User item = userList.getSelectionModel().getSelectedItem();
        Main.getUsers().remove(item);
        userList.getItems().setAll(Main.getUsers());

    }
}
