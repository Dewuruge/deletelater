package lk.ijse.depp11.app.controller;


import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.depp11.app.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class MainFormController {

    public StackPane root;

    @FXML
    private Button btnAlreadyAccount;

    @FXML
    private AnchorPane apLoginForm;

    @FXML
    private AnchorPane apSideForm;

    @FXML
    private AnchorPane apSignUp;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private ComboBox<?> cbQuestions;

    @FXML
    private Hyperlink linkForgotPassword;

    @FXML
    private PasswordField txtAnswer;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRAPassword;

    @FXML
    private TextField txtRAUserName;

    @FXML
    private TextField txtUserName;

    static  List<Customer> customers = new LinkedList<>();

//    static List<String> test = new ArrayList<>();

    static {
        customers.add(new Customer("Nishada","kalpadeep1"));
        customers.add(new Customer("shalina","kalpadeep1"));
        customers.add(new Customer("kalpadeep","kalpadeep1"));
        customers.add(new Customer("dewuruge","kalpadeep1"));

//        customers.get(0).getQAndS().put("ytrewq","ytrewq");`

    }


    //    public String[][] customer = new String[0][2];

//    public String[][] customer = {{"NISHADA","Kalpadeep1"},
//            {"SHALINA","123456"},
//            {"KALPADEEP","234567"}};

    public void initialize(){
        Platform.runLater(() -> txtUserName.requestFocus());

    }

    public void switchForm(ActionEvent e){
        TranslateTransition slider = new TranslateTransition();
        if(e.getSource()== btnCreateAccount){
            slider.setNode(apSideForm);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(0.2));

            slider.setOnFinished((ActionEvent e1) ->{
                btnAlreadyAccount.setVisible(true);
                btnCreateAccount.setVisible(false);
                Platform.runLater(() -> txtRAUserName.requestFocus());


            });


            slider.play();
        }

        else if(e.getSource()==btnAlreadyAccount){
            slider.setNode(apSideForm);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(0.2));
            slider.setOnFinished((ActionEvent e1) ->{
                btnAlreadyAccount.setVisible(false);
                btnCreateAccount.setVisible(true);
                Platform.runLater(() -> txtUserName.requestFocus());


            });
            slider.play();


        }


    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String enteredUserName = txtUserName.getText();
        String enteredPassword = txtPassword.getText();

        if (enteredUserName.isBlank() || enteredPassword.isBlank()) {
            if (enteredUserName.isBlank()) {
                txtUserName.requestFocus();
            } else {
                txtPassword.requestFocus();
            }
            return;
        }

        boolean found = customers.stream()
                .anyMatch(customer -> enteredUserName.equals(customer.getUserName()) && enteredPassword.equals(customer.getPassword()));

        if (found) {
            // Valid user, open the Account
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Account.fxml"));
            Scene mainScene = new Scene(fxmlLoader.load());
            // Get the correct controller for the Account.fxml
            AccountController controller = fxmlLoader.getController();
            stage.setScene(mainScene);
            stage.setTitle("nishada");
            stage.show();
            stage.centerOnScreen();
            ((Stage) (root.getScene().getWindow())).close();
        } else {
            // Invalid username or password
            new Alert(Alert.AlertType.ERROR, "Invalid User Name or Password").show();
        }
    }




    public MainFormController() {
    }


    public void btnSignUpOnAction(ActionEvent actionEvent) {

        String newUserName = txtRAUserName.getText();
        String  newPassword = txtRAPassword.getText();

//        for question validation check video 1-> 42.23
//        TODO
        if(txtRAUserName.getText().isEmpty()|| txtRAPassword.getText().isEmpty()){
            txtRAUserName.requestFocus();
            new Alert(Alert.AlertType.ERROR, "Please fill all blank field").show();

        }

        boolean found = customers.stream().anyMatch(customer -> newUserName.equals(customer.getUserName()));
        if(found){
            new Alert(Alert.AlertType.ERROR, "User Name has already taken").show();
            txtRAUserName.selectAll();
            txtRAUserName.requestFocus();
            return;
        }
        customers.add(new Customer(newUserName,newPassword));
        new Alert(Alert.AlertType.INFORMATION, "Successfully Add.").show();
        btnAlreadyAccount.fire();


    }
}
