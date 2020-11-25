package main;

import java.util.Scanner;

import formatter.Formato;

public class Prueba {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String entrada="1101011001101010010101010100011111";
		String salida="1001111111100011101101010111110110";
		System.out.println(Formato.getProbabilidadAPriori(entrada,salida));
	}

}
