package main;

import java.util.Scanner;

import formatter.Formato;

public class Prueba {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String texto="abcacaabbcacaabcacaaabcaca";
		System.out.println(Formato.getProbabilidadAPriori(texto));
	}

}
