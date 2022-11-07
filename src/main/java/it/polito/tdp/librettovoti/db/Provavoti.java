package it.polito.tdp.librettovoti.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

///SOLUZIONE NON OTTIMALE 
public class Provavoti {
	
	public void aggiungiVoto(String nome, int punti) {
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=Carola2806";
		try {
			Connection conn = DriverManager.getConnection(url);
			//questa soluzione può portare ad una SQLinjection
			//Statement st = conn.createStatement();
			//meglio usare prepareStatement(); con i dati inseriti a parte e '?' al posto dei dati nella query
			
			String sql = "INSERT INTO voti (nome, punti) VALUES (?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,  nome);
			st.setInt(2, punti);
			
			int res = st.executeUpdate();
			
			if(res ==1) {
				System.out.println("Dato registrato correttamente");
			}
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	


	public static void main(String[] args) {
		
		Provavoti provaVoti= new Provavoti();
		provaVoti.aggiungiVoto("Economia",25);
		
		String url = "jdbc:mysql://localhost:3306/libretto?user=root&password=Carola2806";
		try {
			Connection conn = DriverManager.getConnection(url);
			
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM voti";
			//executeQuery() per SELECT
			//executeUpdate() per INSERT,UPDATE E DELETE
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) {
				String nome = res.getString("nome");
				int voto = res.getInt("punti");
				System.out.println(nome+" "+voto);
			}
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
