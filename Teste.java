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
		int turnos = 0;

		try
		{
			turnos = Integer.parseInt(args[1]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		s = new Simulador();
		s.Faz_Tudo(args[0], turnos);

	}
}
