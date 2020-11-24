package formatter;

import java.util.ArrayList;
import java.util.Iterator;

import funcion.Apriori;
import prob.Probabilidad;

public class Formato {
	
	public static String getProbabilidadAPriori(String texto) {
		String res="";
		ArrayList<Probabilidad> p= Apriori.getProbabilidades(texto);
		Iterator<Probabilidad> it= p.iterator();
		while (it.hasNext()) {
			Probabilidad prob= it.next();
			res+= String.format("%s    %.3f \n",prob.getSimbolo(),prob.getProbabilidad());
		}
		return res;
	}

}
