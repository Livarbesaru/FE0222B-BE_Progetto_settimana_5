package dati;

import java.sql.Date;

public class Infrazione {
	private int id;
	private Date data;
	private double importo;
	private String tipo;
	private String targa_auto;
	
	public Infrazione(int id, Date data, String tipo, String targa ,double importo) {
		super();
		this.id = id;
		this.data = data;
		this.tipo = tipo;
		this.targa_auto = targa;
		this.importo=importo;
	}
	public Infrazione(Date data, String tipo, String targa,double importo) {
		super();
		this.data = data;
		this.tipo = tipo;
		this.targa_auto = targa;
		this.importo=importo;
	}
	
	public Infrazione() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTarga_Auto() {
		return targa_auto;
	}
	public void setTarga_Auto(String targa) {
		this.targa_auto = targa;
	}
	
	public double getImporto() {
		return importo;
	}
	public void setImporto(double d) {
		this.importo = d;
	}
	
	@Override
	public String toString() {
		return ("Infrazione ->Data: "+this.data.toString()+" Tipo: "+tipo+" Targa: "+this.targa_auto);
	}
	
}
