package com.example.cms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
//import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button cancelButton ;
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField projectname;
    @FXML
    private TextField budgetField;
    @FXML
    private TextField itemidfield;
    @FXML
    private TextField pricefield;
    @FXML
    private TextField qntyfield;
    @FXML
    private Label messageDisplay;
    @FXML
    private Label messageDisplay2;
    @FXML
    private Label messageDisplay3;
    @FXML
    private Label messageDisplay4;
    @FXML
    private Label messageDisplay5;
    @FXML
    private Button backButton;
    @FXML
    private Button backButton2;
    @FXML
    private Button createbutton;
    @FXML
    private Button continueButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button showtotalButton;
    int totality =0;
    /*
        @FXML
        private Label welcomeText;
    */
    @FXML
    protected void onHelloButtonClick() {
        // welcomeText.setText("LOGIN SUCCESS");
    }

    @FXML
    public void cancelButtonExit(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

        //       Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
    }

    @FXML
    public void loginButton(ActionEvent event) throws IOException {

        sqlConnection connection = new sqlConnection();
        Connection connectDB = connection.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username.getText() + "' AND password ='" + password.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (username.getText().isBlank() == false && password.getText().isBlank() == false)
            {
                while (queryResult.next())
                {
                    if (queryResult.getInt(1) == 1) {
                        messageDisplay.setText("CONGRATULATIONS");
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene1.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                        Stage stage = new Stage();
                        stage.setTitle("Cost Management System");
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        messageDisplay.setText("WRONG USERNAME or PASSWORD");
                    }
                }
                //  messageDisplay.setText("YOU ARE TRYING TO LOG IN");
            } else {
                messageDisplay.setText("Please enter Login details!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }



/*
            while(queryResult.next())
            {
                if (queryResult.getInt(1) == 1)
                {
                    messageDisplay.setText("CONGRATULATIONS");
                } else {
                    messageDisplay.setText("WRONG USERNAME or PASSWORD");
                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }*//*
        if(username.getText().isBlank() == false && password.getText().isBlank() == false)
        {
        while(queryResult.next())
            {
                if (queryResult.getInt(1) == 1)
                {
                    messageDisplay.setText("CONGRATULATIONS");
                     FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
           Stage stage = new Stage();
           stage.setTitle("Cost Management System");
           stage.setScene(scene);
           stage.show();
                } else {
                    messageDisplay.setText("WRONG USERNAME or PASSWORD");
                }
            }
          //  messageDisplay.setText("YOU ARE TRYING TO LOG IN");
        }
        else
        {
            messageDisplay.setText("Please enter Login details!");
        }*/


    @FXML
    public void backButtonAction(ActionEvent event) throws IOException {
            //messageDisplay.setText("YOU ARE TRYING TO LOG IN");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Cost Management System");
            stage.setScene(scene);
            stage.show();

        }


    @FXML
    public void nextButtonAction(ActionEvent event) throws IOException {
        //messageDisplay.setText("YOU ARE TRYING TO LOG IN");

        int itemtotalprice=0;
        int itemid = 0;
        itemid = Integer.parseInt(itemidfield.getText());
        int price = 0;
        price = Integer.parseInt(pricefield.getText());
        int qnty = 0;
        qnty = Integer.parseInt(qntyfield.getText());
        itemtotalprice=price*qnty;
        System.out.println(itemtotalprice);
        totality = totality +itemtotalprice;
        System.out.println(totality);
        itemidfield.clear();
        pricefield.clear();
        qntyfield.clear();
    }

    @FXML
    public void createButtonAction(ActionEvent event) throws IOException {
        //messageDisplay.setText("YOU ARE TRYING TO LOG IN");
        boolean check = true;
        int budget = 0;
        String projectName;
        projectName=projectname.getText();
     //   System.out.println(projectName);
        try {
           check=true;
            budget=0;
            budget = Integer.parseInt(budgetField.getText());
            if (budget<100000)
            {
                throw new NegativeException("Budget cannot be less than 100000");
            }
        } catch (NegativeException e) {
           check=false;
           System.out.println(e.getMessage());
           // System.out.println("Please Enter Numbers Only!");
        }
        //       System.out.println(budget);


        File file = new File("newFile.txt");
        try {
            FileWriter myWriter = new FileWriter("newFile.txt");
            myWriter.write(projectName);
            myWriter.write("  b");
            myWriter.write(budget);
            myWriter.close();
            System.out.println("\nSuccessfully wrote to the file.");
            boolean createFile = file.createNewFile();
            if (createFile) {
                System.out.println("New File created");
            }else {
                System.out.println("This File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    if(check==true)
         {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Cost Management System");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void backButtonAction2(ActionEvent event) throws IOException {
        //messageDisplay.setText("YOU ARE TRYING TO LOG IN");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Cost Management System");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void continueButtonAction(ActionEvent event) throws IOException {
        //messageDisplay.setText("YOU ARE TRYING TO LOG IN");
        //messageDisplay.setText("Your total expense will be: " + totality);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene3.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Cost Management System");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void showTotalButtonAction(ActionEvent event) throws IOException {
        //messageDisplay.setText("YOU ARE TRYING TO LOG IN");
       // System.out.println(totality);
       // messageDisplay.setText("Your total expense will be: " + totality);
        messageDisplay.setText("Your total expense will be less than your budget");

    }

/*
    @FXML
    public void continueButton(ActionEvent event) throws IOException {
        if(projectName.getText().isBlank() == false && password.getText().isBlank() == false)
        {
            messageDisplay.setText("YOU ARE TRYING TO LOG IN");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Cost Management System");
            stage.setScene(scene);
            stage.show();

        }
        else
        {
            messageDisplay.setText("Please enter Login details!");
        }
    }*/
}
