package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dati.Auto;
import dati.Infrazione;
import servizi.ConnectionFactory;

public class InfrazioneDAOImpl implements InfrazioneDAO {
	private static final String DELETE_INFRAZIONE = "delete from multe.infrazione where id = ?";
	private static final String SELECT_INFRAZIONE_TARGA = "SELECT a.id auto_id,a.targa, a.modello,a.marca,i.id inf_id, i.tipo,i.importo, i.data "
			+ "FROM multe.infrazione as i inner "
			+ "join multe.auto as a "
			+ "on a.auto_id=i.id_auto where a.targa=?;";
	private static final String INSERT_INFRAZIONE = "INSERT INTO multe.infrazione(data, tipo, importo, id_auto) VALUES (?, ?, ?, ?);";
	private static Logger log=LoggerFactory.getLogger(InfrazioneDAOImpl.class);
	
	@Override
	public boolean inserisciInfrazione(Infrazione infrazione) {
		Connection conn=ConnectionFactory.getConnection();
		int i=0;

		PreparedStatement statement=null;
		PreparedStatement statement2=null;
		ResultSet res=null;
		try {
			statement2=conn.prepareStatement("Select a.id from multe.auto as a where a.targa=?");
			statement2.setString(1, infrazione.getTarga_Auto());
			
			res=statement2.executeQuery();
			if(res.next()) {
			statement=conn.prepareStatement(INSERT_INFRAZIONE);
			statement.setDate(1, infrazione.getData());
			statement.setString(2, infrazione.getTipo());
			statement.setDouble(3, infrazione.getImporto());
			statement.setInt(4, res.getInt("id"));
			i=statement.executeUpdate();
			log.info("Inseriti"+" n."+i+" infrazioni");
			}
			
		} catch (Exception e) {
			log.error("Errore nell'aggiunta",e);
		}
		try {
			conn.close();
			statement.close();
			res.close();
		} catch (Exception e) {
			log.error("Errore nella chiusura di rete");
		}
		return i>0;
	}

	@Override
	public void stampaDatiInfrazioniAuto(String targa) {
		Connection conn=ConnectionFactory.getConnection();
		
		PreparedStatement statement=null;
		ResultSet res=null;
		try {
			statement=conn.prepareStatement(SELECT_INFRAZIONE_TARGA);
			statement.setString(1, targa);
			res=statement.executeQuery();
			
				while(res.next()) {
					Infrazione i=new Infrazione(res.getDate("data"),res.getString("tipo"),res.getString("targa"),res.getDouble("importo"));
					Auto a= new Auto(res.getString("targa"),res.getString("marca"),res.getString("modello"));
					log.info(i.toString()+"-->"+a.toString());
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
	
	}

	@Override
	public boolean eliminaInfrazione(int id) {
	        Connection conn = ConnectionFactory.getConnection();
	        PreparedStatement st = null;
	        int i = 0;

	        try {
	            st = conn.prepareStatement(DELETE_INFRAZIONE);
	            st.setInt(1, id);
	            i = st.executeUpdate();
	            System.out.println("Cancellazione riga avvenuta");
	        } catch (SQLException e) {
	            System.out.println("Errore cancellazione");
	            e.printStackTrace();
	        }

	        try {conn.close();} catch (Exception e) {}
	        return i>0;
	    }
		
	}
