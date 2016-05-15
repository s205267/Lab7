package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dizionario.model.Parola;


public class ParolaDAO {

	public List<Parola> readAll() {
		Connection conn = ConnectDB.getConnection();

		String sql = "SELECT id, nome FROM parola ORDER BY id ;";

		List<Parola> result = new ArrayList<>() ;
		
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) 
				{

				Parola p = new Parola(res.getInt("id"),res.getString("nome") ) ;
				
				result.add(p) ;
				}
			return result;
			
			}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Parola> searchByLength(int length){
		Connection conn = ConnectDB.getConnection();
		List <Parola> result = new ArrayList<>();
		String sql =  "SELECT id, nome FROM parola WHERE CHAR_LENGTH(nome) = ? ORDER BY id ;";
		PreparedStatement st;
		try {
			
			st = conn.prepareStatement(sql);
			st.setInt(1, length);
			ResultSet res = st.executeQuery();
			
			while(res.next())
			{
				Parola p = new Parola(res.getInt("id"),res.getString("nome"));
				result.add(p);
			}
		return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}