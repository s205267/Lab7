package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import it.polito.tdp.dizionario.model.Parola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button bttGenera;

    @FXML
    private Button bttTrovaVicini;

    @FXML
    private Button bttTrovaTutti;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button bttReset;

    @FXML
    void doGrafo(ActionEvent event) {
    	
    	txtNumLettere.setEditable(false);
    	

    	
    	String lunghezza = txtNumLettere.getText();
    	int num;
    	try 
    	{
    	num = Integer.parseInt(lunghezza);
    	}
    	catch(NumberFormatException n)
    	{
    		txtRisultato.appendText("Inserire un numero nel campo lunghezza!\n");
    		return;
    	}
    	bttTrovaTutti.setDisable(false);
        bttTrovaVicini.setDisable(false);
    	
    	
    	model.caricaParole(num);
    	txtRisultato.appendText("Parole caricate: " + model.getDiz().size() + "\n");
    	model.generaGrafo();
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	this.statoIniziale();
    	
    }

    @FXML
    void doTrovaTutti(ActionEvent event) {
    	txtParola.setEditable(false);
    	String parola =txtParola.getText(); 
    	if((parola=="") || (parola.length()!= model.getLength()))
    	{
    		txtRisultato.appendText("Inserire una parola nel formato/lunghezza corretto\n");
    		return;
    	}
    	Parola p = model.findParola(parola);
    	if(p == null)
    	{
    		txtRisultato.appendText("La parola non esiste nel dizionario");
    		return;
    	}
    	
    	List<Parola> tutti = model.trovaTutti(p);
    	txtRisultato.appendText( "\n****"+txtParola.getText()+"( parole totali:"+tutti.size()+")****\n");
    	for(Parola p1: tutti)
    	{
    		txtRisultato.appendText(p1.getNome()+"\n");
    	}
    	
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	txtParola.setEditable(false);

    	String parola =txtParola.getText(); 
    	if((parola=="") || (parola.length()!= model.getLength()))
    	{
    		txtRisultato.appendText("Inserire una parola nel formato/lunghezza corretto\n");
    		return;
    	}
    	Parola p = model.findParola(parola);
    	if(p == null)
    	{
    		txtRisultato.appendText("La parola non esiste nel dizionario");
    		return;
    	}
    	 List<Parola> vicini = model.trovaVicini(p);
    	 
    	 if(vicini!= null)
    	 {
    		 txtRisultato.appendText(" ***Vicini trovati: "+vicini.size()+"***\n");
    		 for(Parola p1:vicini)
        	 {
        		 txtRisultato.appendText(p1.getNome()+"\n");
        	 }
    	 }
    	 else
    	 {
    		 txtRisultato.appendText("Nessuna parola che differisce di una sola lettera\n");
    	 }
    	
    	
    }
    
    public void statoIniziale()
    {
    	
    	bttTrovaTutti.setDisable(true);
        bttTrovaVicini.setDisable(true);
        txtRisultato.setEditable(false);
        txtRisultato.clear();
    	txtNumLettere.clear();
    	txtParola.clear();
    	txtParola.setEditable(true);
    	txtNumLettere.setEditable(true);

    }

    @FXML
    void initialize() {
        assert txtNumLettere != null : "fx:id=\"txtNumLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert bttGenera != null : "fx:id=\"bttGenera\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert bttTrovaVicini != null : "fx:id=\"bttTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert bttTrovaTutti != null : "fx:id=\"bttTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert bttReset != null : "fx:id=\"bttReset\" was not injected: check your FXML file 'Dizionario.fxml'.";
        this.statoIniziale();
    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
	}
}
