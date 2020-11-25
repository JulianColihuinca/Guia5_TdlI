package funcion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import prob.Probabilidad;

public class Apriori {
	
	private static int filas,columnas;
	private static ArrayList<String> simbolosA;
	private static ArrayList<String> simbolosB;
	
	public static ArrayList<Probabilidad> getProbabilidadesA(String texto) {
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
	
	public static double[][] getMatrizAB(String entrada,String salida) {
		ArrayList<String> conjuntoA= new ArrayList<String>();
		ArrayList<String> conjuntoB= new ArrayList<String>();
		for (int i=0;i<entrada.length();i++) {
			if (!conjuntoA.contains(Character.toString(entrada.charAt(i))))
				 conjuntoA.add(Character.toString(entrada.charAt(i)));
		}
		for (int i=0;i<salida.length();i++) {
			if (!conjuntoB.contains(Character.toString(salida.charAt(i))))
				 conjuntoB.add(Character.toString(salida.charAt(i)));
		}
		Collections.sort(conjuntoA);
		Collections.sort(conjuntoB);
		simbolosA=conjuntoA;
		simbolosB=conjuntoB;
		filas= conjuntoB.size();
		columnas=conjuntoA.size();
		
		double[][] matriz= new double[filas][columnas];
		for (int i=0;i<filas;i++) 
			for (int j=0;j<columnas;j++)
				matriz[i][j]=0;
		int fila, columna;
		for (int i=0;i<entrada.length();i++) {
			fila=conjuntoB.indexOf(Character.toString(salida.charAt(i)));
			columna= conjuntoA.indexOf(Character.toString(entrada.charAt(i)));
			matriz[fila][columna]++;
		}
		
		for (int j=0;j<columnas;j++) {
			double suma=0;
			for (int i=0;i<filas;i++)
				suma+= matriz[i][j];
			for (int i=0;i<filas;i++)
				matriz[i][j]/=suma;
		}
		
		
		return matriz;
	}
	
	public static int getFila() {
		return filas;
	}
	
	public static int getColumna() {
		return columnas;
	}
	
	public static ArrayList<Probabilidad> getProbabilidadesB(String entrada,String salida){
		double[][] matrizAB= getMatrizAB(entrada,salida);
		ArrayList<Probabilidad> pB = new ArrayList<Probabilidad>();
		ArrayList<Probabilidad> pA = getProbabilidadesA(entrada);
		for (int i=0;i<simbolosB.size();i++) {
			double p=0;
			for (int j=0;j<pA.size();j++) {
				double suma= pA.get(j).getProbabilidad() * matrizAB[i][j];
				p+=suma;
			}
			pB.add(new Probabilidad(simbolosB.get(i),p));
		}
		return pB;
	}

}
