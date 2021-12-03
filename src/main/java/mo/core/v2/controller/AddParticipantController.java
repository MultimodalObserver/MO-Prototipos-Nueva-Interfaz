/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.core.v2.controller;

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mo.core.v2.model.Organization;
import mo.organization.Participant;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class AddParticipantController implements Initializable {

    @FXML
    private Text addParticipantText;
    @FXML
    private Text idLabel;
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private Text nameLabel;
    @FXML
    private DatePicker calendar;
    @FXML
    private Text dateLabel;
    @FXML
    private Text noteLabel;
    @FXML
    private TextArea noteText;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Text alert;
    @FXML
    private Text alertLabel;
    private int numParticipant;
    @Inject
    public Injector injector;
    @Inject
    Organization model;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
    }    
    
    public void init(){
        alert.setText("");
        numParticipant = model.getParticipants().size();
        int numAux = numParticipant + 1;
        idText.setText(String.valueOf(numAux));
        calendar.setValue(LocalDate.now());
    }    

    @FXML
    private void addClick(MouseEvent event) {
        System.out.println("1");
        if(!model.getParticipants().isEmpty()){
            System.out.println("2");
            for(Participant p : model.getParticipants()){
                System.out.println("3");
                if(p.id.equals(idText.getText())){
                    System.out.println("3.1");
                    alertLabel.setText("The id must be unique");
                    break;
                }
                else {
                    System.out.println("3.2");
                    if(idText.getText().toCharArray().length<=0){
                        System.out.println("3.2.1");
                        //alert.setText("*");
                        alertLabel.setText("you must write a name");
                        break;
                    }
                    else{
                        System.out.println("3.2.2");
                        createParticipant();
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("4");
            if(idText.getText().toCharArray().length<=0){
                System.out.println("4.1");
                alertLabel.setText("you must write a name");
            }
            else{
                System.out.println("4.2");
                createParticipant(); 
            }
        }
        cancelClick(event);
    }

    @FXML
    private void cancelClick(MouseEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    private void createParticipant(){
        Participant p = new Participant();
        p.id = idText.getText();
        p.name = nameText.getText();
        p.notes = noteText.getText();
        p.isLocked = false;
        Instant instant = Instant.from(calendar.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date dateAux = Date.from(instant);
        p.date = dateAux;
        p.folder = "/participant-"+p.id;
        model.getParticipants().add(p);
    }
}