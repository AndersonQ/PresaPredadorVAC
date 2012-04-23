/*
 * =====================================================================================
 *
 *       Filename: GeraMapa.java 
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  23-04-2012 19:27:37
 *       Revision:  none
 *       Compiler:  gcc
 *         Author:  Anderson de França Queiroz (A.F.Q.), contato(.AT,)andersonq dot eti dot br
 *         	    Tiago de França Queiroz, contato(.AT,)tiago dot eti dot br
 *
 *        Company:  Universidade Federal do ABC - UFABC
 *
 * =====================================================================================
 */

import java.io.*;
import java.util.*;
import java.io.FileWriter;

public class GeraMapa
{
	public static void main(String[] args)
	{
		Random r = new Random();
		int i = 0, l = 0, c = 0, lin = 0, col = 0;

		FileWriter fw = null;
		PrintWriter pw = null;

		try
		{
			fw = new FileWriter(args[0]);
			pw = new PrintWriter(fw);
			l = Integer.parseInt(args[1]);
			c = Integer.parseInt(args[2]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		/* Imprime o tamanho da matriz */
		pw.printf("%d,%d\n", l, c);


		for(lin = 0; lin < l; lin ++)
			for(col = 0; col < c; col ++)
			{
				i = r.nextInt(100);

				/* Se é o último da linha, não imprime a vírgula,
				 * mas quebra a linha.
				 */
				if(col == (c - 1))
				{
					if(i < 50) /* 50% de chances */
						pw.printf("%d\n",0);
					else if(i < 80) /* 30 % de chances */
						pw.printf("%d\n",1);
					else if(i < 90) /* 10 % de chances */
						pw.printf("%d\n",2);
					else if(i < 95) /* 5 % de chances */
						pw.printf("%d\n",3);
					else if(i < 100) /* 5% de chances */
						pw.printf("%d\n",4);
					else /* 0% de chances, mas é bom garantir */
						pw.printf("%d\n",0);
				}
				else
				{
					if(i < 50) /* 50% de chances */
						pw.printf("%d,",0);
					else if(i < 80) /* 30 % de chances */
						pw.printf("%d,",1);
					else if(i < 90) /* 10 % de chances */
						pw.printf("%d,",2);
					else if(i < 95) /* 5 % de chances */
						pw.printf("%d,",3);
					else if(i < 100) /* 5% de chances */
						pw.printf("%d,",4);
					else /* 0% de chances, mas é bom garantir */
						pw.printf("%d,",0);
				}
			}
		try
		{
			pw.close();
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
