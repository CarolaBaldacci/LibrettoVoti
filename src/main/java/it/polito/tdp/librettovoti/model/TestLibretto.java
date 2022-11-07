package it.polito.tdp.librettovoti.model;

public class TestLibretto {

	public static void main(String[] args) {
		Libretto lib= new Libretto();
		lib.add(new Voto("Analisi1",30));
		lib.add(new Voto("Fisica1",25));
		lib.add(new Voto("Algebra",20));
		System.out.println(lib);
		
		Libretto lib1= new Libretto();
		lib1.add(new Voto("Analisi1",20));
		lib1.add(new Voto("Fisica1",22));
		lib1.add(new Voto("Algebra",23));
		
		//stampa tutti i voti pari a 25
		System.out.println("Voti pari a 25");
		Libretto lib25=lib.filtraPunti(25);
		System.out.println(lib25);
		
		//Stampa libretto migliorato 
		System.out.println("Libretto Migliorato");
		Libretto libMigliorato= lib.votiMigliorati();
		System.out.println(libMigliorato);
		
		//Stampa libretto cancellato di voti minori di...
		System.out.println("Libretto senza voti minori di 23");
		lib.cancellaVotiMinori(23);
		System.out.println(lib);
		
		//Ricerca voto dal nome del corso
		System.out.print("Ricerca voto di Algebra:");
		int p=lib.puntiEsame("Algebra");
		System.out.println(p);
		//Ricerca voto di un corso non esistente
		System.out.print("Ricerca voto di Analisi2:");
		int pnull=lib.puntiEsame("Analisi2");
		System.out.println(pnull);
		
		
		
		
		
		

	}

}
