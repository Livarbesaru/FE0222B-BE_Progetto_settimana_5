package dati;

public class Auto {
	private int id;
	private String targa;
	private String marca;
	private String modello;
	
	public Auto(String targa, String marca, String modello) {
		super();
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
	}
	public Auto(int id, String targa, String marca, String modello) {
		super();
		this.id = id;
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
	}
	
	public Auto() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	@Override
	public String toString() {
		return ("Auto ->Targa: "+this.targa+" Marca: "+this.marca+" Modello: "+this.modello);
	}
	
}
