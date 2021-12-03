/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.core.v2.controller;

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mo.analysis.AnalysisProvider;
import mo.capture.CaptureProvider;
import mo.core.plugin.Plugin;
import mo.core.plugin.PluginRegistry;
import mo.core.v2.model.Activity;
import mo.core.v2.model.ConfigurationV2;
import mo.core.v2.model.Organization;
import mo.core.v2.model.StagePluginV2;
import mo.visualization.VisualizationProvider;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class AddPluginController implements Initializable {
    private final String CONTROLLER_KEY = "controller";
    
    @FXML
    private ComboBox<String> comboCaptures;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    private int type;
    private List<CaptureProvider> auxC;
    private List<AnalysisProvider> auxA;
    private List<VisualizationProvider> auxV;
    @Inject
    public Injector injector;
    @Inject
    Organization model;
    private List<String> config;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        initComboBox();
    }

    private void init(){
        //nameConfiguration.visibleProperty().set(false);
        type = model.getType();
        //parse(model.getPlugins());
        auxC = model.getCaptures();
        auxA= model.getAnalysis();
        auxV = model.getVisualization();
        config = new ArrayList<String>();
    }
    
    public void initComboBox(){
        comboCaptures.setItems(model.getObservablePlugins());
    }

    @FXML
    private void addOption(MouseEvent event) { 
        if(type==1){
            model.getCaptures().add(addSelectedC(auxC,comboCaptures.getSelectionModel().getSelectedItem()));
            model.setPluginSelected(addSelectedC(auxC,comboCaptures.getSelectionModel().getSelectedItem()));
            
        }
        else if(type==2){
            model.getAnalysis().add(addSelectedA(auxA,comboCaptures.getSelectionModel().getSelectedItem()));
            model.setPluginSelected(addSelectedA(auxA,comboCaptures.getSelectionModel().getSelectedItem()));
        }
        else if(type==3){
            model.getVisualization().add(addSelectedV(auxV, comboCaptures.getSelectionModel().getSelectedItem()));
            model.setPluginSelected(addSelectedV(auxV,comboCaptures.getSelectionModel().getSelectedItem()));
        }
        String aux = comboCaptures.getSelectionModel().getSelectedItem();
        model.getObservablePlugins().remove(aux);
        close(event);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    
    private void parse(List<StagePluginV2> plugins){
        if(type==1){
            auxC = new ArrayList<CaptureProvider>();
            plugins.forEach((plugin) -> {
                auxC.add((CaptureProvider)plugin);
            });   
        }
        else if(type==2){
            auxA = new ArrayList<AnalysisProvider>();
            plugins.forEach((plugin) -> {
                auxA.add((AnalysisProvider)plugin);
            });
        }
        else if(type==3){
            auxV = new ArrayList<VisualizationProvider>();
            plugins.forEach((plugin) -> {
                auxV.add((VisualizationProvider)plugin);
            });   
        }        
    }
    
    private CaptureProvider addSelectedC(List<CaptureProvider> aux, String selected){
        for(CaptureProvider capture : aux){
            if(capture.getName().equals(selected)){
                return capture;
            }
        }
        return null;  
    }
    
    private AnalysisProvider addSelectedA(List<AnalysisProvider> aux, String selected){
        for(AnalysisProvider analysis : aux){
            if(analysis.getName().equals(selected)){
                return analysis;
            }
        }
        return null;  
    }
    
    private VisualizationProvider addSelectedV(List<VisualizationProvider> aux, String selected){
        for(VisualizationProvider visualization : aux){
            if(visualization.getName().equals(selected)){
                return visualization;
            }
        }
        return null;  
    }

    private void allGood(KeyEvent event) {
        addButton.disableProperty().setValue(Boolean.FALSE);
    }

    @FXML
    private void check(MouseEvent event) {
    }
    
}