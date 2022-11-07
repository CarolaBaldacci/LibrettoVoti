package it.polito.tdp.librettovoti.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.librettovoti.db.LibrettoDAO;

public class Libretto {
	private List <Voto> voti;
	
	//Libretto normale
	public Libretto() {
		this.voti =new ArrayList<Voto>();
	}
	//Libretto migliorato
	public Libretto votiMigliorati() {
		Libretto nuovo=new Libretto();
		for(Voto v:this.voti) {
			int punti=v.getPunti();
			if(punti>=24)
				punti+=2;
			else 
				punti++;
			if(punti>30)
				punti=30;
			/* v.setPunti(punti); non va bene perchè modifico 
			   nuovo.add(v);        anche il libretto vecchio*/      
			nuovo.add(new Voto(v.getNome(),punti));
		}
		return nuovo;
	}
	//Libretto con voti sopra il int
	public void cancellaVotiMinori(int punti) {
		for(Voto v: this.voti) {
			if(v.getPunti()<punti)
				this.voti.remove(v);
		}
	}

//RICHIAMO I VOTI DAL DAO ANZICHE' CREARLI NEL CONTROLLER
	/*public boolean add(Voto v) {
		if(!isDuplicato(v) && !isConflitto(v)) {
			this.voti.add(v);
			return true;
		}
		else {
			return false;
		}
	}*/
	public boolean add(Voto v) {
		LibrettoDAO dao= new LibrettoDAO();
		boolean result =dao.creaVoto(v);
		return result;
	}
	

	/*public List<Voto> getVoti(){
		return this.voti;
	}*/
	public List <Voto> getVoti(){
		LibrettoDAO dao= new LibrettoDAO();
		return dao.readAllVoto();
	}
	
	
	@Override
	public String toString() {
		return this.voti.toString();
	}
	
	public Libretto filtraPunti(int punti) {
		Libretto result= new Libretto();
		for(Voto v:this.voti) {
			if(v.getPunti()==punti) {
				result.add(v);
			}
		}
		return result;
	}
	
	public Integer puntiEsame(String nome) {
		for (Voto v: this.voti) {
			if(v.getNome().equals(nome)) {
				return v.getPunti();
			}
		}
		return null;
		//throw new IllegalArgumentException("Corso non trovato");
	}
	
	public boolean isDuplicato(Voto v) {
		for(Voto v1 :this.voti) {
			if(v1.equals(v)) {
				return true;
			}
		}
		return false;
	}
	public boolean isConflitto(Voto v) {
		Integer punti= this.puntiEsame(v.getNome());
		if(punti!=null && punti!=v.getPunti())
			return true;
		else
			return false;
		
	}
	
}
