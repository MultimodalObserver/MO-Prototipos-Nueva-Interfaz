/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.core.v2.controller;

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.util.Callback;
import mo.core.v2.model.Organization;
import mo.core.v2.model.OrganizationV2;
import mo.organization.ProjectOrganization;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class ProjectMenuController implements Initializable {
    private final String CONTROLLER_KEY = "controller";
    
    public ProjectOrganization projectOrg;
    @FXML
    private BorderPane projectMenuPane;
    @FXML
    private ImageView closeButton;
    @FXML
    private Text nameProjectText;
    @FXML
    private Button participantsButton;
    @FXML
    private Button captureButton;
    @FXML
    private Button analysisButton;
    @FXML
    private Button visualizationButton;
    @FXML
    private AnchorPane centerPane;
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
    }    
    
    @FXML
    private void closeButton(MouseEvent event) {
    }

    @FXML
    private void clickParticipants(MouseEvent event) {
        try{
            System.out.println(model.getFileProject());
            centerPane.getChildren().clear();
            final JavaFXBuilderFactory builderFactory = new JavaFXBuilderFactory();
            final Callback<Class<?>, Object> callback = (clazz) -> injector.getInstance(clazz);
            FXMLLoader loaderParticipants = new FXMLLoader(MainWindowsController.class
                .getResource("/fxml/core/ui/ProjectParticipants.fxml"), null,
                builderFactory, callback);

            Parent participantParent = loaderParticipants.load();

            participantParent.getProperties()
                .put(CONTROLLER_KEY, loaderParticipants.getController());
            centerPane.getChildren().add(participantParent);
        }
        catch (IOException ex) {
            Logger.getLogger(MainWindowsController.class
          .getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void clickCaptures(MouseEvent event) {
        try{
            centerPane.getChildren().clear();
            final JavaFXBuilderFactory builderFactory = new JavaFXBuilderFactory();
            final Callback<Class<?>, Object> callback = (clazz) -> injector.getInstance(clazz);
            FXMLLoader loaderCaptures = new FXMLLoader(MainWindowsController.class
                    .getResource("/fxml/core/ui/ProjectCaptures.fxml"), null,
                    builderFactory, callback);
            Parent captureParent = loaderCaptures.load();

            captureParent.getProperties()
                    .put(CONTROLLER_KEY, loaderCaptures.getController());
            centerPane.getChildren().add(captureParent);
        }
        catch(IOException ex){
            Logger.getLogger(MainWindowsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void clickAnalysis(MouseEvent event) {
        try{
            centerPane.getChildren().clear();
            final JavaFXBuilderFactory builderFactory = new JavaFXBuilderFactory();
            final Callback<Class<?>, Object> callback = (clazz) -> injector.getInstance(clazz);
            FXMLLoader loaderAnalysis = new FXMLLoader(MainWindowsController.class
                    .getResource("/fxml/core/ui/ProjectAnalysis.fxml"), null,
                    builderFactory, callback);
            Parent analysisParent = loaderAnalysis.load();
            
            analysisParent.getProperties()
                    .put(CONTROLLER_KEY, loaderAnalysis.getController());
            centerPane.getChildren().add(analysisParent);
        }
        catch(IOException ex){
            Logger.getLogger(MainWindowsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickVisualizations(MouseEvent event) {
        try{
            centerPane.getChildren().clear();
            final JavaFXBuilderFactory builderFactory = new JavaFXBuilderFactory();
            final Callback<Class<?>, Object> callback = (clazz) -> injector.getInstance(clazz);
            FXMLLoader loaderVisualization = new FXMLLoader(MainWindowsController.class
                    .getResource("/fxml/core/ui/ProjectVisualization.fxml"), null,
                    builderFactory, callback);
            Parent visualizationParent = loaderVisualization.load();
            
            visualizationParent.getProperties()
                    .put(CONTROLLER_KEY, loaderVisualization.getController());
            centerPane.getChildren().add(visualizationParent);
        }
        catch(IOException ex){
            Logger.getLogger(MainWindowsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadPlugins(){
        
    }

    
}