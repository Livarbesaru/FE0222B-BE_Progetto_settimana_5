package servizi;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dati.Auto;
import dati.Infrazione;
import servizi.dao.AutoDAOImpl;
import servizi.dao.InfrazioneDAOImpl;

public class Main {
	private static Logger log=LoggerFactory.getLogger(Main.class);
	private static Scanner scan=new Scanner(System.in);
	private static String MENU="\r\n"+"=======SCEGLI======="
			+"\r\n"+"1: Per inserire un automobile"
			+ "\r\n"+ "2: Per inserire un infrazione ad un auto"
			+ "\r\n"+ "3: Vengono visualizzate tutte le auto del DB"
			+ "\r\n"+ "4: E' possibile cercare un auto inserendo la targa esatta"
			+ "\r\n"+ "5: Per visualizzare tutte le infrazioni di un auto tramite targa"
			+ "\r\n"+ "6: E' possibile eliminare una specifica Infrazione fornendo l’Id";
	

	public static void main(String[] args) {
		scelta();
	}
	
	public static void scelta() {
		while (true) {
			log.info(MENU);
			int n = Integer.parseInt(scan.nextLine());

			switch (n) {
			case 1: {
				inserisciAuto();
				break;
			}
			case 2: {
				inserisciInfrazione();
				break;
			}
			case 3: {
				getAllAuto();
				break;
			}
			case 4: {
				getAutoByTarga();
				break;
			}
			case 5: {
				getInfrazioniByTarga();
				break;
			}
			case 6: {
				deleteInfrazioniById();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + n);
			}
		}
	}
	
	public static void inserisciAuto() {
		AutoDAOImpl dao=new AutoDAOImpl();
		Auto auto=new Auto();
		log.info("Inserisci la targa");
		auto.setTarga(scan.nextLine().toUpperCase());
		log.info("Inserisci la marca");
		auto.setMarca(scan.nextLine());
		log.info("Inserisci il modello");
		auto.setModello(scan.nextLine());
		dao.inserisciAuto(auto);
	}
	public static void inserisciInfrazione() {
		InfrazioneDAOImpl dao=new InfrazioneDAOImpl();
		Infrazione i=new Infrazione();
		Date data=new Date(0, 0, 0);
		log.info("Inserisci il giorno");
		data.setDate(Integer.parseInt(scan.nextLine()));
		log.info("Inserisci il mese come numero");
		data.setMonth((Integer.parseInt(scan.nextLine()))-1);
		log.info("Inserisci l'anno");
		data.setYear((Integer.parseInt(scan.nextLine()))-1900);
		
		i.setData(data);
		log.info("Inserisci il tipo");
		i.setTipo(scan.nextLine());
		log.info("Inserisci la targa del veicolo");
		i.setTarga_Auto(scan.nextLine().toUpperCase());
		log.info("Inserisci l'importo");
		i.setImporto(Double.parseDouble(scan.nextLine()));
		dao.inserisciInfrazione(i);
	}
	public static void getAllAuto() {
		AutoDAOImpl dao=new AutoDAOImpl();
		List listaAuto=dao.getAllAuto();
		listaAuto.forEach(a->log.info(a.toString()));
		
	}
	public static void getAutoByTarga() {
		AutoDAOImpl dao=new AutoDAOImpl();
		log.info("Inserisci la targa");
		log.info(dao.cercaAuto(scan.nextLine().toUpperCase()).toString());
	}
	public static void getInfrazioniByTarga() {
		InfrazioneDAOImpl dao=new InfrazioneDAOImpl();
		log.info("Inserisci la targa");
		dao.stampaDatiInfrazioniAuto(scan.nextLine().toUpperCase());
	}
	public static void deleteInfrazioniById() {
		InfrazioneDAOImpl dao=new InfrazioneDAOImpl();
		log.info("Inserisci l'id dell'infrazione");
		dao.eliminaInfrazione(Integer.parseInt(scan.nextLine()));
	}

}
