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
									mapa_prox[i + x][j + y] = new Celula(PRESA, mapa_prox[i][j].vida - 1);
						}
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

	boolean posValida(int lin, int col)
	{
		if( ((lin >= 0 ) && (col >= 0)) && ((lin < l) && (col < c)) )
			return true;
		return false;
	}

}
