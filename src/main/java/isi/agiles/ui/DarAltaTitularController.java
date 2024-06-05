package isi.agiles.ui;

import java.io.IOException;

import isi.agiles.App;
import isi.agiles.entidad.TipoDoc;
import isi.agiles.entidad.TipoFactorRH;
import isi.agiles.entidad.TipoGrupoS;
import isi.agiles.util.DatosInvalidosException;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class DarAltaTitularController{
    
    @FXML
    private AnchorPane frameDarAltaTitular;

    @FXML
    private Text tituloDarAltaTitular;

    @FXML
    private ComboBox<TipoDoc> comboTipoDocumento;

    @FXML
    private TextField nroDocumento;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textApellido;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private TextField textDireccion;

    @FXML
    private ComboBox<TipoGrupoS> comboGrupoSanguineo;

    @FXML
    private ComboBox<TipoFactorRH> comboFactorRH;

    @FXML
    private Label labelClaseSolicitada;

    @FXML
    private CheckBox choiceClaseA;

    @FXML
    private CheckBox choiceClaseB;

    @FXML
    private CheckBox choiceClaseC;

    @FXML
    private CheckBox choiceClaseD;

    @FXML
    private CheckBox choiceClaseE;

    @FXML
    private CheckBox choiceClaseF;

    @FXML
    private CheckBox choiceClaseG;
    
    @FXML
    private ComboBox<String> comboDonante;

    @FXML
    private Label tipoObligatorio;

    @FXML
    private Label numObligatorio;

    @FXML
    private Label nombreObligatorio;

    @FXML
    private Label apellidoObligatorio;

    @FXML
    private Label fechaObligatorio;

    @FXML
    private Label dirObligatorio;

    @FXML
    private Label gruposObligatorio;

    @FXML
    private Label factorRHObligatorio;

    @FXML
    private Label donanteObligatorio;

    @FXML
    private Button botonVolver;

    @FXML
    private Button botonGuardar;


    public void accionVolver(){
        try{
            Stage currentStage = (Stage) botonVolver.getScene().getWindow();
            App.cambiarVentana("MenuPrincipal.fxml", currentStage);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void accionGuardar(){
        try {
            validarDatos();
        } catch (Exception e) {
            errorDatosInvalidos(e.getMessage());
        }
    }

    private void validarDatos() throws DatosInvalidosException {
        Boolean datoInvalido = false;
        datoInvalido |= validarDocumento();
        datoInvalido |= validarNombreYapellido();
        //datoInvalido |= validarNacimiento();
        /*datoInvalido |= validarDireccion();
        datoInvalido |= validarComboboxes();*/
        if (datoInvalido){
            throw new DatosInvalidosException("Advertencia: por favor, revise los campos ingresados y vuelva a intentarlo");
        }
    }

     /*private Boolean validarNacimiento() {
        Boolean esInvalido = false;
        LocalDate date;

        if(dateFechaNacimiento.getValue() != null){
            fechaObligatorio.setVisible(false);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(dateFechaNacimiento.getValue().parse("yyyy-MM-dd")))
        } else {
            fechaObligatorio.setVisible(true);
            esInvalido=false;
        }
        return esInvalido;
    }*/

    private Boolean validarNombreYapellido() {
        Boolean esInvalido = false;
        if(!textNombre.getText().isEmpty() || !textApellido.getText().isEmpty()){
            nombreObligatorio.setVisible(false);
            apellidoObligatorio.setVisible(false);
            if(textNombre.getText().length() >32 || textApellido.getText().length() > 32){
                nombreObligatorio.setText("Máximo 32 caracteres");
                nombreObligatorio.setVisible(true);
                apellidoObligatorio.setText("Máximo 32 caracteres");
                apellidoObligatorio.setVisible(true);
                esInvalido = true;
            } else {
                nombreObligatorio.setVisible(false);
                apellidoObligatorio.setVisible(false);
                if(!textNombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ-]+")){
                    nombreObligatorio.setText("Caracteres inválidos");
                    nombreObligatorio.setVisible(true);
                    esInvalido = true;
                } 
                if(!textApellido.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ-]+")){
                    apellidoObligatorio.setText("Caracteres inválidos");
                    apellidoObligatorio.setVisible(true);
                    esInvalido = true;
                }
            }
        } else {
            esInvalido = true;
            nombreObligatorio.setText("*Campo Obligatorio*");
            apellidoObligatorio.setText("*Campo Obligatorio*");
            nombreObligatorio.setVisible(true);
            apellidoObligatorio.setVisible(true);
        }
        return esInvalido;
    }

    private Boolean validarDocumento() {
        Boolean esInvalido = false;

        if(comboTipoDocumento.getValue()!= null){
            tipoObligatorio.setVisible(false);
            if(comboTipoDocumento.getValue() == TipoDoc.DNI){
                if (nroDocumento.getText().isEmpty()) {
                    esInvalido = true;     
                    numObligatorio.setVisible(true);               
                } else {
                    numObligatorio.setVisible(false);
                    if(!nroDocumento.getText().matches("^\\d{8}$")){
                        numObligatorio.setText("Formato: 99999999");
                        numObligatorio.setVisible(true);
                        nroDocumento.setText(null);
                        esInvalido=true;
                    }
                }
            } else {
                if (nroDocumento.getText().isEmpty()) {
                    esInvalido = true;     
                    numObligatorio.setVisible(true);               
                } else {
                    numObligatorio.setVisible(false);
                    if(!nroDocumento.getText().matches("^[a-zA-Z]{3}\\d{6}$")){
                        numObligatorio.setText("Formato: AAA999999");
                        numObligatorio.setVisible(true);
                        nroDocumento.setText(null);
                        esInvalido=true;
                    }
                }
            }
        } else {
            esInvalido = true;
            tipoObligatorio.setVisible(true);
            numObligatorio.setText("*Campo Obligatorio*");
            numObligatorio.setVisible(true);
        }
        
        return esInvalido;
    }

    private void errorDatosInvalidos(String message) {
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.getDialogPane().getChildren().stream()
                .filter(node -> node instanceof Label)
                .forEach(node -> ((Label) node).setFont(Font.font("Times New Roman", 14)));
        alert.getDialogPane().lookupButton(ButtonType.OK).setCursor(Cursor.HAND);
        alert.setResizable(false);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        ocultarObligatorios();
        inicializarDesplegables();
    }

    private void inicializarDesplegables() {
        comboTipoDocumento.getItems().addAll(TipoDoc.values());
        comboGrupoSanguineo.getItems().addAll(TipoGrupoS.values());
        comboFactorRH.getItems().addAll(TipoFactorRH.values());
        comboDonante.getItems().addAll("SI", "NO");
    }

    private void ocultarObligatorios() {
        tipoObligatorio.setVisible(false);
        numObligatorio.setVisible(false);
        nombreObligatorio.setVisible(false);
        apellidoObligatorio.setVisible(false);
        fechaObligatorio.setVisible(false);
        dirObligatorio.setVisible(false);
        gruposObligatorio.setVisible(false);
        factorRHObligatorio.setVisible(false);
        donanteObligatorio.setVisible(false);
    }
}