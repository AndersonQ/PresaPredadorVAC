/*
 * =====================================================================================
 *
 *       Filename:  Simulador.java
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

public class Simulador
{
	public static int main(Strinh[] args)
	{
		Leitor le;
		int[][] mundo;

		le = new Leitor("./teste");
		mundo = le.LerArquivo();



