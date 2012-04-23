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
	public static void main(String[] args)
	{
		Simulador s = null;
		Leitor le = null;
		int[][] mundo = null;
		int l, c;

		try
		{
			le = new Leitor();
			mundo = le.LerArquivo(args[0]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		l = le.Getl();
		c = le.Getc();
		System.out.printf("mundo[%d][%d]\n", l, c);

		for(int i = 0; i < l; i++)
		{
			for(int j = 0; j < c; j++)
			{
				System.out.print(mundo[i][j] + " ");
			}
			System.out.printf("\n");
		}


		System.out.printf("\n\nCOMEÇANDO O TESTE SÉRIO!!\n\n");
		s = new Simulador();
		s.Faz_Tudo(args[0]);

	}
}
