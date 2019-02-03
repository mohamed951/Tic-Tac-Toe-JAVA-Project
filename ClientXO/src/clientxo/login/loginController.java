/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientxo.login;

import Network.Client;
import Network.Message;
import clientxo.ClientXO;
import clientxo.FXMLDocumentController;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author EgyJuba
 */
public class loginController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button closeBtn, minBtn;

    @FXML
    public TextField name;
    @FXML
    public PasswordField password;
    Client client;
    private double x = 0;
    private double y = 0;

    @FXML
    private void closeButtonAction() {

        // Close Window Button
        Stage closeStage = (Stage) closeBtn.getScene().getWindow();
        closeStage.close();
    }

    @FXML
    private void minButtonAction() {

        // Close Window Button
        Stage minStage = (Stage) minBtn.getScene().getWindow();
        minStage.setIconified(true);
    }

    @FXML
    private void SignUpButtonAction(ActionEvent event) throws IOException {
        // Login Butt
        Parent SignupView = FXMLLoader.load(getClass().getResource("../signup/signup.fxml"));
        Scene SignupScene = new Scene(SignupView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // to make the stage movable 
        SignupView.setOnMousePressed((MouseEvent e) -> {
            x = e.getSceneX();
            y = e.getSceneY();
        });
        SignupView.setOnMouseDragged((MouseEvent e) -> {
            window.setX(e.getScreenX() - x);
            window.setY(e.getScreenY() - y);
        });

        window.setScene(SignupScene);
        window.show();
        System.out.println("sign-up Pressed");
    }

    @FXML
    private void GameAction(ActionEvent event) throws IOException {
        // Login Butto
//         new FXMLDocumentController().signUpWindow();
        client.sendMessage(new Message("Login", new String[]{name.getText(), password.getText()}));
        Message msg = client.recieveMessage();
        if (msg.getType().equals("Login") && msg.getData()[0].equals("Accept")) { 

            Parent SignupView = FXMLLoader.load(getClass().getResource("../playerForm/playType.fxml"));
            Scene SignupScene = new Scene(SignupView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // to make the stage movable 
            SignupView.setOnMousePressed((MouseEvent e) -> {
                x = e.getSceneX();
                y = e.getSceneY();
            });
            SignupView.setOnMouseDragged((MouseEvent e) -> {
                window.setX(e.getScreenX() - x);
                window.setY(e.getScreenY() - y);
            });
            window.setScene(SignupScene);
            window.show();
        }

        System.out.println("Login game Pressed");
    }

    @FXML
    private void LoginAction(ActionEvent ev) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            client = new Client(new Socket("localhost", 8901));
            client.start();
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
