/*
 * =====================================================================================
 *
 *       Filename:  Teste.java
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  21-04-2012 18:27:37
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Anderson de França Queiroz (A.F.Q.), anderson.f.queiroz(.AT,)gmail dot com
 *        Company:  Universidade Federal do ABC - UFABC
 *
 * =====================================================================================
 */

import java.io.*;

public class Teste
{
	public static int main(String[] args) throws Exception
	{
		Leitor le;
		int[][] mundo;
		int l, c;

		le = new Leitor();
		mundo = le.LerArquivo("./teste");

		l = le.Getl();
		c = le.Getc();

		for(int i = 0; i < l; i++)
		{
			for(int j = 0; j < c; j++)
			{
				System.out.println(mundo[l][c] + " ");
			}
			System.out.printf("\n");
		}
		return 0;
	}
}
