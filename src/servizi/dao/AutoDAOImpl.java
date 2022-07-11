package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dati.Auto;
import servizi.ConnectionFactory;

public class AutoDAOImpl implements AutoDAO {
	private static final String SELECT_AUTO_BY_TARGA = "SELECT * FROM multe.auto Where targa=?";
	private static final String SELECT_ALL_AUTO = "SELECT * FROM multe.auto";
	private static final String INSERT_INTO_AUTO = "INSERT INTO multe.auto(targa, marca, modello)	VALUES (?, ?, ?);";
	private static Logger log=LoggerFactory.getLogger(AutoDAOImpl.class);
	

	@Override
	public boolean inserisciAuto(Auto auto) {
		Connection conn=ConnectionFactory.getConnection();
		int i=0;
		PreparedStatement statement=null;
		try {
			statement=conn.prepareStatement(INSERT_INTO_AUTO);
			statement.setString(1, auto.getTarga());
			statement.setString(2, auto.getMarca());
			statement.setString(3, auto.getModello());
			i=statement.executeUpdate();
			log.info("Inseriti"+" n."+i+" auto");
		} catch (Exception e) {
			log.error("Errore nell'aggiunta",e);
		}
		try {
			conn.close();
			statement.close();
		} catch (Exception e) {
			log.error("Errore nella chiusura di rete");
		}	
		return i>0;
	}

	@Override
	public Auto cercaAuto(String targa) {
		Connection conn=ConnectionFactory.getConnection();
		
		PreparedStatement statement=null;
		ResultSet res=null;
		Auto auto=null;
		try {
			statement=conn.prepareStatement(SELECT_AUTO_BY_TARGA);
			statement.setString(1, targa);
			res=statement.executeQuery();
			if(res.next()) {
				auto=new Auto(res.getInt("id"),res.getString("targa"),res.getString("marca"),res.getString("modello"));
				log.info("Auto preso con successo");
			}
		} catch (Exception e) {
			log.error("Errore nella cattura");
		}
		try {
			conn.close();
			res.close();
			statement.close();
		} catch (Exception e) {
			log.error("Errore nella chiusura di rete");
		}
		
		return auto;
	
	}

	@Override
	public List getAllAuto() {
		Connection conn=ConnectionFactory.getConnection();

		PreparedStatement statement=null;
		ResultSet res=null;
		List<Auto> listAuto=null;
		try {
			listAuto=new ArrayList<Auto>();
			statement=conn.prepareStatement(SELECT_ALL_AUTO);
			res=statement.executeQuery();
			while(res.next()) {
				listAuto.add(new Auto(res.getInt("id"),res.getString("targa"),res.getString("marca"),res.getString("modello")));
			}
		} catch (Exception e) {
			log.error("Errore nella cattura");
		}
		try {
			conn.close();
			res.close();
			statement.close();
		} catch (Exception e) {
			log.error("Errore nella chiusura di rete");
		}
		
		return listAuto.stream().collect(Collectors.toList());
	
	}

}
