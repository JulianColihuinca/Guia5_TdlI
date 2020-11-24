package funcion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import prob.Probabilidad;

public class Apriori {
	
	
	public static ArrayList<Probabilidad> getProbabilidades(String texto) {
		ArrayList<Probabilidad> probabilidades= new ArrayList<Probabilidad>();
		HashMap<String,Probabilidad> aux= new HashMap<String,Probabilidad>();
		int cant=0;
		for (int i=0;i<texto.length();i++) {
			String simbolo= Character.toString(texto.charAt(i));
			if (aux.containsKey(simbolo)) {
				aux.get(simbolo).setProbabilidad(aux.get(simbolo).getProbabilidad() + 1);
			}
			else
				aux.put(simbolo, new Probabilidad(simbolo,1));
			cant++;
		}
		
		Iterator<Entry<String,Probabilidad>> it= aux.entrySet().iterator();
		while (it.hasNext()) {
			Probabilidad p= it.next().getValue();
			p.setProbabilidad(p.getProbabilidad()/cant);
			probabilidades.add(p);
		}
		return probabilidades;
	}

}
