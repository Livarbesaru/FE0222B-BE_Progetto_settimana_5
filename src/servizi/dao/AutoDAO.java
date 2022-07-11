package servizi.dao;

import java.util.List;

import dati.Auto;

public interface AutoDAO {

	boolean inserisciAuto(Auto auto);

	Auto cercaAuto(String targa);

	List getAllAuto();

}