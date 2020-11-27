package main;

import java.util.Scanner;

import formatter.Formato;

public class Prueba {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String entrada="abcacaabbcacaabcacaaabcaca";
		String salida= "01010110011001000100010011";
		System.out.println(Formato.getProbabilidadAPriori(entrada,salida));
	}

}
