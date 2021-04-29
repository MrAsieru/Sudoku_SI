package Egitura;

public class PuntuazioaEgitura {
	public String izena;
	public int zailtasuna;
	public double puntuazioa;

	public PuntuazioaEgitura() {}

	public PuntuazioaEgitura(String izena, int zailtasuna, Double puntuazioa) {
		this.izena = izena;
		this.zailtasuna = zailtasuna;
		this.puntuazioa = puntuazioa;
	}

	public double getPuntuazioa() {
		return puntuazioa;
	}

	public int getZailtasuna() {
		return zailtasuna;
	}
}
