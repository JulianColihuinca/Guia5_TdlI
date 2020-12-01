package formatter;

import java.util.ArrayList;
import java.util.Iterator;

import funcion.Aposteriori;
import funcion.Apriori;
import prob.Probabilidad;

public class Formato {
	
	public static String getProbabilidadAPriori(String entrada,String salida) {
		String res="";
		ArrayList<Probabilidad> p= Apriori.getProbabilidadesA(entrada);
		Iterator<Probabilidad> it= p.iterator();
		res+="Probabilidad a priori:\n\n";
		int i=1;
		res+="Ai       Simbolo       Probabilidad\n";
		while (it.hasNext()) {
			Probabilidad prob= it.next();
			res+= String.format("A%d         %s              %.3f \n",i,prob.getSimbolo(),prob.getProbabilidad());
			i++;
		}
		double[][] matrizAB= Apriori.getMatrizBA(entrada, salida);
		res+= "\n\nMatriz B/A:\n";
		for (i=0;i<Apriori.getFila();i++) {
			for (int j=0;j<Apriori.getColumna();j++) {
				res+= String.format("%.3f ", matrizAB[i][j]);
			}
			res+="\n";
		}	
		
		ArrayList<Probabilidad> pB= Apriori.getProbabilidadesB(entrada,salida);
		Iterator<Probabilidad> it2= pB.iterator();
		i=1;
		res+="\n\nBi       Simbolo       Probabilidad\n";
		while (it2.hasNext()) {
			Probabilidad prob= it2.next();
			res+= String.format("B%d         %s              %.3f \n",i,prob.getSimbolo(),prob.getProbabilidad());
			i++;
		}
		
		return res;
	}
	
	public static String getMatrizAB(String entrada,String salida) {
		String res="";
		double[][] matrizAB=Aposteriori.getMatrizAB(entrada, salida);
		res+= "\n\nMatriz A/B:\n";
		for (int i=0;i<Aposteriori.getFilas();i++) {
			for (int j=0;j<Aposteriori.getColumnas();j++) {
				res+= String.format("%.3f ", matrizAB[i][j]);
			}
			res+="\n";
		}
		return res;
	}
	
	public static String getMatrizSimultanea(String entrada,String salida) {
		String res="";
		double[][] matriz=Aposteriori.getProbabilidadSimultanea(entrada, salida);
		res+= "\n\nMatriz de Eventos Simultaneos:\n";
		for (int i=0;i<Aposteriori.getFilas();i++) {
			for (int j=0;j<Aposteriori.getColumnas();j++) {
				res+= String.format("%.3f ", matriz[i][j]);
			}
			res+="\n";
		}
		return res;
	}

}
