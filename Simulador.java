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
 *         Author:  Anderson de França Queiroz (A.F.Q.), contato(.AT,)andersonq dot eti dot br
 *         	    Tiago de França Queiroz, contato(.AT,)tiago dot eti dot br
 *
 *        Company:  Universidade Federal do ABC - UFABC
 *
 * =====================================================================================
 */

import java.io.*;
import java.util.*;

public class Simulador
{
	public static final int NADA = 0;
	public static final int PRESA = 1;
	public static final int PREDADOR = 2;
	public static final int DEFUNTO = 3;
	public static final int RECICLADOR = 4;

	protected Celula[][] mapa_atual, mapa_prox;
	protected int l, c;
	Random r;

	Simulador()
	{
		mapa_atual = mapa_prox = null;
		l = c = 0;
		r = new Random();
	}

	int ContaVizinhosR1(Celula[][] mapa, int tipo, int l, int c)
	{
		int cont = 0;

		if(posValida(l - 1, c - 1))
			if(mapa[l - 1][c - 1].tipo == tipo)
				cont ++;
		if(posValida(l - 1, c))
			if(mapa[l - 1][c].tipo == tipo)
				cont ++;
		if(posValida(l - 1, c + 1))
			if(mapa[l - 1][c + 1].tipo == tipo)
				cont++;
		if(posValida(l, c - 1))
			if(mapa[l][c - 1].tipo == tipo)
				cont ++;
		if(posValida(l, c + 1))
			if(mapa[l][c + 1].tipo == tipo)
				cont ++;
		if(posValida(l + 1, c - 1))
			if(mapa[l + 1][c - 1].tipo == tipo)
				cont ++;
		if(posValida(l + 1, c))
			if(mapa[l + 1][c].tipo == tipo)
				cont ++;
		if(posValida(l + 1, c + 1))
			if(mapa[l + 1][c + 1].tipo == tipo)
				cont ++;

		return cont;
	}

	int ContaVizinhosR2(Celula[][] mapa, int tipo, int l, int c)
	{
		int cont = 0;

		if(posValida(l - 2, c - 2))
			if(mapa[l - 2][c - 2].tipo == tipo)
				cont ++;
		if(posValida(l - 2, c - 1))
			if(mapa[l - 2][c - 1].tipo == tipo)
				cont ++;
		if(posValida(l - 2, c))
			if(mapa[l - 2][c].tipo == tipo)
				cont ++;
		if(posValida(l - 2, c + 1))
			if(mapa[l - 2][c + 1].tipo == tipo)
				cont ++;
		if(posValida(l - 2, c + 2))
			if(mapa[l - 2][c + 2].tipo == tipo)
				cont ++;
		if(posValida(l - 1, c - 2))
			if(mapa[l - 1][c - 2].tipo == tipo)
				cont ++;
		if(posValida(l - 1, c + 2))
			if(mapa[l - 1][c + 2].tipo == tipo)
				cont ++;
		if(posValida(l, c - 2))
			if(mapa[l][c - 2].tipo == tipo)
				cont ++;
		if(posValida(l, c + 2))
			if(mapa[l][c + 2].tipo == tipo)
				cont ++;
		if(posValida(l + 1, c - 2))
			if(mapa[l + 1][c - 2].tipo == tipo)
				cont ++;
		if(posValida(l + 1, c + 2))
			if(mapa[l + 1][c + 2].tipo == tipo)
				cont ++;
		if(posValida(l + 2, c - 2))
			if(mapa[l + 2][c - 2].tipo == tipo)
				cont ++;
		if(posValida(l + 2, c - 1))
			if(mapa[l + 2][c - 1].tipo == tipo)
				cont ++;
		if(posValida(l + 2, c))
			if(mapa[l + 2][c].tipo == tipo)
				cont ++;
		if(posValida(l + 2, c + 1))
			if(mapa[l + 2][c + 1].tipo == tipo)
				cont ++;
		if(posValida(l + 2, c + 2))
			if(mapa[l + 2][c + 2].tipo == tipo)
				cont ++;

		return cont;
	}

	Celula[][] mIntTomCelula(int[][] mapa, int l, int c)
	{
		int i, j;
		Celula[][] m;

		m = new Celula[l][c];

		for(i = 0; i < l; i++)
			for(j = 0; j < c; j++)
				m[i][j] = new Celula(mapa[i][j], 20);
		return m;
	}

	/*
	 * Morre: 4 ou mais vizinhos são presas (morre de fome)
	 * Morre: Predadores > presas
	 * Anda : vPresas >= vPredadores 
	 */
	void processaPresas()
	{
		int i, j;
		int vPresa, vPredador, x, y;

		for(i = 0; i < l; i++)
			for(j = 0; j < c; j++)
			{
				if(mapa_atual[i][j].tipo == PRESA)
				{
					if(--mapa_atual[i][j].vida == 0)
					{
						mapa_prox[i][j] = new Celula(DEFUNTO, 0);
					}
					else
					{
						vPresa = ContaVizinhosR1(mapa_atual, PRESA, i, j);
						vPredador = ContaVizinhosR1(mapa_atual, PREDADOR, i, j);

						/* Morre */
						if((vPresa >= 4) || (vPredador > vPresa))
							mapa_prox[i][j] = new Celula(DEFUNTO, 0);

						/* Anda */
						if(vPredador >= vPresa)
						{
							x = r.nextInt(2);
							y = r.nextInt(2);

							/* Calcula o sinal */
							if(r.nextBoolean())
								x = -x;
							if(r.nextBoolean())
								y = -y;

							if(posValida(i + x, j + y))
								if(mapa_prox[i + x][j + y].tipo == NADA)
									mapa_prox[i + x][j + y] = new Celula(PRESA, mapa_atual[i][j].vida);
								else
									mapa_prox[i][j] = new Celula(PRESA, mapa_atual[i][j].vida);
						}
					}
				}
			}
	}

	void ProcessaPredador()
	{
		int vPresa, vPredador;
		int i, j, px, py, k, nx, ny;
		int[] presas;
		boolean ok = false;

		for(i = 0; i < l; i++)
			for(j = 0; j < c; j++)
				if(mapa_atual[i][j].tipo == PREDADOR)
				{
					ok = false;
					if(--mapa_atual[i][j].vida == 0)
						mapa_prox[i][j] = new Celula(DEFUNTO, 0);
					else
					{
						/* Procura presa em R = 2 */
						vPresa = ContaVizinhosR2(mapa_atual, PRESA, i, j);
						if (vPresa > 0)
						{
							presas = EncontraVizinho(i, j, PRESA, 2, mapa_atual);
							k = 0;
							while(k < presas.length)
							{
								px = presas[k++];
								py = presas[k++];

								if(px > i)
									nx = i + 1;
								else if(px < i)
									nx = i - 1;
								else
									nx = i;

								if(py > j)
									ny = j + 1;
								else if (py < j)
									ny = j - 1;
								else
									ny = j;

								if(posValida(nx, ny))
									if(mapa_prox[nx][ny].tipo == NADA)
									{
										mapa_prox[nx][ny] = new Celula(PREDADOR, mapa_atual[i][j].vida);
										ok = true;
										break;
									}

							}
							if(ok == false)
							{
								mapa_prox[i][j] = new Celula(PREDADOR, mapa_atual[i][j].vida);
								ok = true;
							}
						}

						/* Procura presa em R = 1 */
						vPresa = ContaVizinhosR1(mapa_atual, PRESA, i, j);
						if((vPresa > 0) && (ok == false))
						{
							presas = EncontraVizinho(i, j, PRESA, 2, mapa_atual);
							k = 0;
							while(k < presas.length)
							{
								px = presas[k++];
								py = presas[k++];

								if(px > i)
									nx = i + 1;
								else if(px < i)
									nx = i - 1;
								else
									nx = i;

								if(py > j)
									ny = j + 1;
								else if (py < j)
									ny = j - 1;
								else
									ny = j;

								if(posValida(nx, ny))
								{
									mapa_atual[nx][ny].vida = 0;
									mapa_prox[nx][ny] = new Celula(PREDADOR, 20);
									ok = true;
									break;
								}
							}
						}

						/* Anda aleatório */
						if(ok = false)
						{
							/* Anda */
							nx = r.nextInt(2);
							ny = r.nextInt(2);

							/* Calcula o sinal */
							if(r.nextBoolean())
								nx = -nx;
							if(r.nextBoolean())
								ny = -ny;

							if(posValida(i + nx, j + ny))
								if(mapa_prox[i + nx][j + ny].tipo == NADA)
									mapa_prox[i + nx][j + ny] = new Celula(PREDADOR, mapa_prox[i][j].vida);
								else
									mapa_prox[i][j] = new Celula(PREDADOR, mapa_atual[i][j].vida);
						}

					}
				}
	}

	void ProcessaReciclador()
	{
		int vPresa, vPredador, vReciclador;

		for(int i = 0; i < l; i++)
			for(int j = 0; j < c; j++)
			{
				if(mapa_atual[i][j].tipo == RECICLADOR)
				{
					int nVizinhosR1, nVizinhosR2, vizinhosR1[], vizinhosR2[], cel;

					vizinhosR1 = EncontraVizinho(i, j, RECICLADOR, 1, mapa_atual);
					nVizinhosR1 = vizinhosR1.length;

					vizinhosR2 = EncontraVizinho(i, j, RECICLADOR, 2, mapa_atual);
					nVizinhosR2 = vizinhosR2.length;

					if(nVizinhosR1 != 0)
					{
						cel = r.nextInt(nVizinhosR1);
						mapa_prox[vizinhosR1[cel*2]][vizinhosR1[cel*2+1]].tipo = NADA;

						return;
					}

					if(nVizinhosR2 != 0)
					{
						int x, y, andaX, andaY;
						
						cel = r.nextInt(nVizinhosR2);
						x = vizinhosR2[cel*2];
						y = vizinhosR2[cel * 2 + 1];

						if(x > l)
							andaX = l + 1;
						else if(x < l)
							andaX = l - 1;
						else if(x == l)
							andaX = 0;

						if(y > c)
							andaY = c + 1;
						else if(y < c)
							andaY = c - 1;
						else if(y == c)
							andaY = 0;

						mapa_prox[l + andaX][c + andaY] = new Celula(RECICLADOR, mapa_atual[i][j].vida);
					}
				}
			}
	}

	void processaNada()
	{
		int vPresa, vPredador, vReciclador;
		int i, j;

		for(i = 0; i < l; i++)
			for(j = 0; j < c; j++)
				if(mapa_atual[i][j].tipo == NADA)
				{
					vPresa = ContaVizinhosR1(mapa_atual, PRESA, i, j);
					vPredador = ContaVizinhosR1(mapa_atual, PREDADOR, i, j);
					vReciclador = ContaVizinhosR1(mapa_atual, RECICLADOR, i, j);

					if( (vPresa == 3) && (vPredador == 0))
						mapa_prox[i][j] = new Celula(PRESA, 20);

					else if( (vPredador == 3) && (vPresa == 0) )
						mapa_prox[i][j] = new Celula(PREDADOR, 20);

					if(vReciclador == 3)
						mapa_prox[i][j] = new Celula(RECICLADOR, 20);
				}
	}

	/*
	 * Recebe uma célula, um tipo de célula e sua posição,
	 * então retorna o primeiro vizinho -- do tipo recebido --
	 * que foi encontrado
	 */
	int[] EncontraVizinho(int l, int c, int tipo, int raio, Celula maps[][])
	{	
		ArrayList<int> lista = new ArrayList<int>();
		if(raio == 1)
		{

			//Raio 1
			if(posValida(l - 1, c - 1))
				if(maps[l - 1][c - 1].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l - 1, c))
				if(maps[l - 1][c].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l - 1, c + 1))
				if(maps[l - 1][c + 1].tipo == tipo)
					cont++;
			if(posValida(l, c - 1))
				if(maps[l][c - 1].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l, c + 1))
				if(maps[l][c + 1].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 1, c - 1))
				if(maps[l + 1][c - 1].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 1, c))
				if(maps[l + 1][c].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 1, c + 1))
				if(maps[l + 1][c + 1].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
		}
		else if(raio == 2)
		{
			// Raio 2
			if(posValida(l - 2, c - 2))
				if(maps[l - 2][c - 2].tipo == tipo)
				{
				lista.add(l);
				lista.add(c);
				}
			if(posValida(l - 2, c - 1))
				if(maps[l - 2][c - 1].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l - 2, c))
				if(maps[l - 2][c].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l - 2, c + 1))
				if(maps[l - 2][c + 1].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l - 2, c + 2))
				if(maps[l - 2][c + 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l - 1, c - 2))
				if(maps[l - 1][c - 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l - 1, c + 2))
				if(maps[l - 1][c + 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l, c - 2))
				if(maps[l][c - 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l, c + 2))
				if(maps[l][c + 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 1, c - 2))
				if(maps[l + 1][c - 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 1, c + 2))
				if(maps[l + 1][c + 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 2, c - 2))
				if(maps[l + 2][c - 2].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 2, c - 1))
				if(maps[l + 2][c - 1].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 2, c))
				if(maps[l + 2][c].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 2, c + 1))
				if(maps[l + 2][c + 1].tipo == tipo)
					{
					lista.add(l);
					lista.add(c);
				}
			if(posValida(l + 2, c + 2))
				if(maps[l + 2][c + 2].tipo == tipo)
				{
					lista.add(l);
					lista.add(c);
				}
		}
		return lista.toArray();
	}


	boolean posValida(int lin, int col)
	{
		if( ((lin >= 0 ) && (col >= 0)) && ((lin < l) && (col < c)) )
			return true;
		return false;
	}

}
