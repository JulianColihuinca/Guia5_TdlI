package funcion;

import java.util.ArrayList;

import prob.Probabilidad;

public class Aposteriori {
	
	private  static int filas,columnas;
	
	public static double[][] getMatrizAB(String entrada,String salida) {
		ArrayList<Probabilidad> pA= Apriori.getProbabilidadesA(entrada);
		double[][] matrizBA= Apriori.getMatrizBA(entrada, salida);
		ArrayList<Probabilidad> pB= Apriori.getProbabilidadesB(entrada, salida);
		filas=Apriori.getColumna();
		columnas= Apriori.getFila();
		double[][] matrizAB= new double[filas][columnas];
		for (int i=0;i<Apriori.getFila();i++)
			for (int j=0;j<Apriori.getColumna();j++)
				matrizAB[j][i]= (matrizBA[i][j]*pA.get(i).getProbabilidad())/ pB.get(j).getProbabilidad();
		return matrizAB;
	}
	
	public static double[][] getProbabilidadSimultanea(String entrada, String salida){
		double[][] matrizBA=getMatrizAB(entrada, salida);
		ArrayList<Probabilidad> pB= Apriori.getProbabilidadesB(entrada, salida);
		double[][] matriz= new double[filas][columnas];
		for (int i=0;i<filas;i++) 
			for (int j=0;j<columnas;j++) {
				matriz[i][j]= matrizBA[i][j]* pB.get(i).getProbabilidad();
			}
		return matriz;
	}

	public static int getFilas() {
		return filas;
	}

	public static int getColumnas() {
		return columnas;
	}
	
	

}
