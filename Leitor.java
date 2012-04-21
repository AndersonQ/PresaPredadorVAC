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
 *
 *         Author:  Anderson de França Queiroz (A.F.Q.), anderson.f.queiroz(.AT,)gmail dot com
 *        Company:  Universidade Federal do ABC - UFABC
 *
 * =====================================================================================
 */

public class Leitor
{
	int[][] LerArquivo(String nome)
	{
		int l, c;
		String line, tokens[];
		BufferedReader file = new BufferedReader(new FileReader(nome));

		line = file.readLine();
		tokens = line.split(";");
		l = Integer.parseInt(tokens[0]);
		c = Integer.parseInt(tokens[2]);

		char mundo[][] = new char[l][c];

		while(line.ready())
		{
			tokens = line.split(";");
			for(int i = 0; i < l; i++)
			{
				for(int j = 0; j < c; j++)
				{
					mindo[i][j] = tokens[j];
				}
			}
		}
	}
}
