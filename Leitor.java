/*
 * =====================================================================================
 *
 *       Filename:  Leitor.java
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  21-04-2012 17:19:40
 *       Revision:  none
 *       Compiler:  javac
 *         Author:  Anderson de França Queiroz (A.F.Q.), contato(.AT,)andersonq dot eti dot br
 *         	    Tiago de França Queiroz, contato(.AT,)tiago dot eti dot br
 *
 *        Company:  Universidade Federal do ABC - UFABC
 *
 * =====================================================================================
 */

import java.io.*;

public class Leitor
{
	int l, c;
	int[][] mundo;

	int[][] LerArquivo(String nome) throws Exception
	{
		String line, tokens[];
		BufferedReader file = null;
		int k = 0, i;

		file = new BufferedReader(new FileReader(nome));

		line = file.readLine();
		tokens = line.split(",");
		l = Integer.parseInt(tokens[0]);
		c = Integer.parseInt(tokens[1]);

		mundo = new int[l][c];

		i = 0;
		while(file.ready())
		{
			line = file.readLine();
			tokens = line.split(",");
			k = 0;
			for(int j = 0; j < c; j++)
			{
				mundo[i][j] = Integer.parseInt(new String(tokens[k++]));
			}
			i++;
		}
		return mundo;
	}

	public int Getl()
	{
		return l;
	}

	public int Getc()
	{
		return c;
	}

	public int[][] GetMundo()
	{
		return mundo;
	}
}
