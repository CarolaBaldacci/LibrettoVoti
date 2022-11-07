package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//CONTROLLER
public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbPunti;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtVoti;
    @FXML
    private Label txtStatus;
    
    
    
    //quando clicco Aggiungi mi collego ai metodi delle classi Voto e Libretto
    @FXML
    void handleNuovoVoto(ActionEvent event) {
    	//1.acquisizione dati
    	String nome=txtNome.getText();
    	Integer punti=cmbPunti.getValue();
    	
    	//controlli validità
    	if(nome.equals("")|| punti==null) {
    		txtStatus.setText("ERRORE: inserire nome e voto!");
    		return;
    	}
    	
    	//2.esecuzione operazione delegando al Model
    	boolean ok = model.add(new Voto(nome, punti));
    	//3. visualizzo/aggiornamento risultato
    	if(ok) {
    		List<Voto> voti=model.getVoti();
    	    txtVoti.clear();
    	    txtVoti.appendText("Hai superato "+voti.size()+" esami \n");
    	    for(Voto v:voti) {
    	    	txtVoti.appendText(v.toString()+"\n");
    	    }
    	    //4.ripulisco i valori
    	    txtNome.clear();
        	cmbPunti.setValue(null);	
        	txtStatus.setText("");
    	}
    	else {
    		txtStatus.setText("ERRORE: esame già esistente!");
    	}
    	
    	
    }
    
    //Quando clicco su aggiungi devo chiamare la funzione add() della classe Libretto
    private Libretto model;
    public void setModel(Libretto model) {
    	this.model=model;
    	List<Voto> voti=model.getVoti();
	    txtVoti.clear();
	    txtVoti.appendText("Hai superato "+voti.size()+" esami \n");
	    for(Voto v:voti) {
	    	txtVoti.appendText(v.toString()+"\n");
	    }
    }


    
    @FXML
    void initialize() {
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";

        //inizializzo il combobox
        cmbPunti.getItems().clear(); 
        for(int p=18; p<=30;p++) {
        	cmbPunti.getItems().add(p);
        }
    }
    
    

}