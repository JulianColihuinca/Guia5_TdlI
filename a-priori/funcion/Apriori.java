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
	
	public static ArrayList<Probabilidad> getProbabilidadesA(String entrada) {
		simbolosA= new ArrayList<String>();
		HashMap<String,Probabilidad> hash= new HashMap<String,Probabilidad>();
		for (int i=0;i<entrada.length();i++) {
			String simbolo= Character.toString(entrada.charAt(i));
			if (simbolosA.contains(simbolo)) 
				hash.get(simbolo).setProbabilidad(hash.get(simbolo).getProbabilidad()+1);
			else {
				simbolosA.add(simbolo);
				hash.put(simbolo, new Probabilidad(simbolo,1));
			}
		}
		filas=hash.size();
		Collections.sort(simbolosA);
		Iterator<Entry<String,Probabilidad>> it= hash.entrySet().iterator();
		ArrayList<Probabilidad> probabilidades= new ArrayList<Probabilidad>();
		while(it.hasNext()) {
			Probabilidad p= it.next().getValue();
			p.setProbabilidad(p.getProbabilidad()/entrada.length());
			probabilidades.add(p);
		}
		return probabilidades;
	}
	
	public static double[][] getMatrizBA(String entrada,String salida) {
		simbolosB= new ArrayList<String>();
		HashMap<String,Probabilidad> hash= new HashMap<String,Probabilidad>();
		for (int i=0;i<salida.length();i++) {
			String simbolo= Character.toString(salida.charAt(i));
			if (simbolosB.contains(simbolo)) 
				hash.get(simbolo).setProbabilidad(hash.get(simbolo).getProbabilidad()+1);
			else {
				simbolosB.add(simbolo);
				hash.put(simbolo, new Probabilidad(simbolo,1));
			}
		}
		
		Collections.sort(simbolosB);
		columnas= hash.size();
		double[][] matriz= new double[filas][columnas];
		for (int i=0;i<filas;i++) 
			for (int j=0;j<columnas;j++)
				matriz[i][j]=0;
		int fila, columna;
		for (int i=0;i<entrada.length();i++) {
			fila= simbolosA.indexOf(Character.toString(entrada.charAt(i)));
			columna= simbolosB.indexOf(Character.toString(salida.charAt(i)));
			matriz[fila][columna]= matriz[fila][columna]+1;
		}	
		
		for (int i=0;i<filas;i++) {
			int suma= 0;
			for (int j=0;j<columnas;j++)
				suma+=matriz[i][j];
			for (int j=0;j<columnas;j++)
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
		double[][] matrizBA=getMatrizBA(entrada,salida);
		ArrayList<Probabilidad> pA= getProbabilidadesA(entrada);
		ArrayList<Probabilidad> pB= new ArrayList<Probabilidad>();
		for (int j=0;j<columnas;j++) {
			double probabilidad=0;
			for (int i=0;i<filas;i++) 
				probabilidad+= matrizBA[i][j] * pA.get(i).getProbabilidad();
			pB.add(new Probabilidad(simbolosB.get(j),probabilidad));
		}
		return pB;
	}

}
