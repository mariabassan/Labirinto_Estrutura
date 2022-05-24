package labirinto;

public class Percorrer {
	
	//Atributos
	Pilha<Coordenada> caminho;
	//Pilha<Pilha<Coordenada>> possibilidades;
	Pilha<Fila<Coordenada>> possibilidades; //adicionar fila no lugar da segunda pilha
	//Pilha<Coordenada> pilhaDeAdjacentes;
	Fila<Coordenada> filaDeAdjacentes; //adicionar fila no lugar
	
	public Coordenada andar(int valorLinha, int valorColuna, char [][] matriz) throws Exception
	{
		int tamanho = valorLinha * valorColuna;
		boolean andarPraFrente = true;
		//inverso = andarPraTras
		boolean inverso = false;
		Coordenada atual;
		Coordenada cordPossi = new Coordenada(0,0);
		Coordenada adjacente = new Coordenada(0,0);
		Coordenada entrada = new Coordenada(0,0);
		Coordenada anterior = new Coordenada (0,0);
		int aux = 0;
		int linha=0;
		int coluna=0;
		//pilhaDeAdjacentes = new Pilha<Coordenada> (tamanho); //agora é fila
		filaDeAdjacentes = new Fila<Coordenada> (tamanho); //new
		possibilidades = new Pilha<Fila<Coordenada>> (tamanho); //era Pilha<Pilha<Coordenada>> possibilidades
		caminho = new Pilha<Coordenada> (tamanho);
		
		//Percorrer a matriz para achar a entrada e guardar em sua respectiva variavel
		for(int i = 0; i < valorLinha; i++)
		{
			for(int j = 0; j < valorColuna; j++)
			{
				if(matriz[i][j] == 'E')
				{
					entrada.setX(i);
					entrada.setY(j);
					break;
				}
			}
		}
		
		atual = new Coordenada(entrada.getX(),entrada.getY());
		
		//Procura enquanto nao acha uma parede
		while(andarPraFrente == true)
		{
			//Verifica se a variavel atual nao está na entrada
			if ((atual.getX() == entrada.getX()) && (atual.getY() == entrada.getY()))
				{
					// Entrada a esquerda > entao sempre andara para frente
					if (entrada.getY() == 0)
					{
						//se o atual estiver em branco > mudá-la e avancar para direita uma coluna colocando o * no lugar
						if(matriz[entrada.getX()][entrada.getY()+1] == ' ')
						{
							atual.setX(entrada.getX());
							atual.setY(entrada.getY()+1);
							caminho.guardeUmItem(new Coordenada(atual.getX(),atual.getY()));
							matriz[entrada.getX()][entrada.getY()+1] = '*';
						}
					}
					//Entrada a direita > entao sempre andara para esquerda
					else if (entrada.getY() == valorColuna-1)
					{
						//se o atual estiver em branco > mudá-la e avancar para esquerda uma coluna colocando o * no lugar
						if(matriz[entrada.getX()][entrada.getY()-1] == ' ')
						{
							atual.setX(entrada.getX());
							atual.setY(entrada.getY()-1);
							caminho.guardeUmItem(new Coordenada(atual.getX(),atual.getY()));
							matriz[entrada.getX()][entrada.getY()-1] = '*';
						}
					}
					//Entrada na primeira linha > sempre andara para baixo
					else if (entrada.getX() == 0)
					{
						//se o atual estiver em branco > mudá-la e avancar para baixo uma coluna colocando o * no lugar
						if(matriz[entrada.getX()+1][entrada.getY()] == ' ')
						{
							atual.setX(entrada.getX()+1);
							atual.setY(entrada.getY());
							caminho.guardeUmItem(new Coordenada(atual.getX(),atual.getY()));
							matriz[entrada.getX()+1][entrada.getY()] = '*';
						}
					}
					// Entrada na ultima linha > entao sempre andara para cima
					else if (entrada.getX() == valorLinha-1)
					{
						//se o atual estiver em branco > mudá-la e avancar para cima uma coluna colocando o * no lugar
						if(matriz[entrada.getX()-1][entrada.getY()] == ' ')
						{
							atual.setX(entrada.getX()-1);
							atual.setY(entrada.getY());
							caminho.guardeUmItem(new Coordenada(atual.getX(),atual.getY()));
							matriz[entrada.getX()-1][entrada.getY()] = '*';
						}
					}
					else 
						return atual;
					
				}
			
			//Quando ele nao estiver na entrada
			else
			{
								
				linha = atual.getX();
				coluna = atual.getY();			

				
				//posicao acima
				if (matriz[linha-1][coluna]== ' '|| matriz[linha-1][coluna]== 'S')
				{
					anterior.setX((linha)-1);
					anterior.setY(coluna);
					
					//checando se a posicao acima ja foi percorrida (ou seja se tem *)
					if(anterior != caminho.recupereUmItem())
					{
						adjacente.setX((linha)-1);
						adjacente.setY((coluna));
						
						if(matriz[linha-1][coluna] == 'S')
						{
							return adjacente;
						}
					
						filaDeAdjacentes.guardeUmItem(new Coordenada(adjacente.getX(),(adjacente.getY())));
						andarPraFrente = true;
						aux = 1;
						
					}
					System.out.println(adjacente+"Adjacente");
					
				}
				
				//posicao a frente
				if (matriz[linha][coluna+1]==' '|| matriz[linha][coluna+1]== 'S')
				{
					anterior.setX(linha);
					anterior.setY(coluna+1);
					
					//checando se posicao a frente foi percorrida
					if(anterior != caminho.recupereUmItem())
					{
						adjacente.setX((linha));
						adjacente.setY((coluna)+1);
					
							if(matriz[linha][coluna+1] == 'S')
							{
								return adjacente;
							}
						
						 
						filaDeAdjacentes.guardeUmItem(new Coordenada(adjacente.getX(),(adjacente.getY())));
						andarPraFrente = true;
						aux = 1;
					}
					
					System.out.println(adjacente+"Adjacente");
				}
				
				//posicao a baixo
				if (matriz[linha+1][coluna]== ' ' || matriz[linha+1][coluna]== 'S')
				{
					anterior.setX(linha+1);
					anterior.setY(coluna);
					
					//checando se posicaoo a baixo foi percorrida
					if(anterior != caminho.recupereUmItem())
					{
						adjacente.setX((linha+1));
						adjacente.setY((coluna));
					
						if(matriz[linha+1][coluna] == 'S')
						{
							return adjacente;
						}
					
						filaDeAdjacentes.guardeUmItem(new Coordenada(adjacente.getX(),(adjacente.getY())));
						andarPraFrente = true;
						aux = 1;
					}
					
					System.out.println(adjacente+"Adjacente");
				}
				
				//posicao atras
				if (matriz[linha][coluna-1]== ' '|| matriz[linha][coluna-1]== 'S')
				{
					anterior.setX(linha);
					anterior.setY(coluna-1);
					
					//checando se posicao atras foi percorrida (ou seja, se tem *)
					if(anterior != caminho.recupereUmItem())
					{
						adjacente.setX((linha));
						adjacente.setY((coluna-1));
					
							if(matriz[linha][coluna-1] == 'S')
							{
								return adjacente;
							}
					
						
						filaDeAdjacentes.guardeUmItem(new Coordenada(adjacente.getX(),(adjacente.getY())));
						andarPraFrente = true;
						aux = 1;
					}
					System.out.println(adjacente+"Adjacente");
				}
				
				//Se possui uma adjacente nova
				if(aux == 1)
				{
					//pega a adjacente e coloca na atual
					atual = new Coordenada(filaDeAdjacentes.recupereUmItem().getX(),filaDeAdjacentes.recupereUmItem().getY());
					
					//descarte da adjacente
					filaDeAdjacentes.removaUmItem();
	
					//coloco o *
					matriz[atual.getX()][atual.getY()]='*';
					
					//Guardo as adjacentes restantes na pilha de possibilidades
					if(!(filaDeAdjacentes.isVazia()))
					{
						
						this.possibilidades.guardeUmItem(this.filaDeAdjacentes);
					}
										
					aux = 0;
					// coloco a atual no caminho percorrido
					caminho.guardeUmItem(new Coordenada(atual.getX(),atual.getY()));
				}
				
				//quando encontra somente parede e o caminho comeca ser regredido
				else if(aux == 0)
				{
					System.out.println(atual+"atual antes da regressao");
					matriz[atual.getX()][atual.getY()]=' ';
					inverso = true;
					andarPraFrente = false;
				}
				
				
			}
			
			//printar os caminhos
			for(int i = 0; i < valorLinha; i++)
			{
				for(int j = 0; j < valorColuna; j++)
				{
					System.out.print(matriz[i][j]);
				}
				System.out.print("\n");
			}
			
			Thread.sleep(350);
		
			//Andando para tras
			while (inverso == true)
			{
				
				aux = 0;
				matriz [atual.getX()][atual.getY()] = ' ';
				
				//refazendo o caminho
				caminho.removaUmItem();
				
				//pega uma coordenada do caminho para o atual
				atual = new Coordenada(caminho.recupereUmItem().getX(),caminho.recupereUmItem().getY());
				
				matriz [atual.getX()][atual.getY()] = '*';
				
				cordPossi = new Coordenada(possibilidades.recupereUmItem().recupereUmItem().getX(),possibilidades.recupereUmItem().recupereUmItem().getY());
				
				//verifica se alguma adjacente da atual a igual a coordenada retirada da possibilidade (a frente, a cima, atras e a baixo)
				if ((atual.getX()+1 == cordPossi.getX()) && (atual.getY() == cordPossi.getY()))
				{
					atual = new Coordenada(cordPossi.getX(),cordPossi.getY());
					caminho.guardeUmItem( new Coordenada(cordPossi.getX(),cordPossi.getY()));
					matriz[atual.getX()][atual.getY()] = '*';
					possibilidades.recupereUmItem().removaUmItem();
					
					inverso = false;
					andarPraFrente = true;
				}
				
				if ((atual.getX() == cordPossi.getX()) && (atual.getY()+1 == cordPossi.getY()))
				{
					atual = new Coordenada(cordPossi.getX(),cordPossi.getY());
					caminho.guardeUmItem( new Coordenada(cordPossi.getX(),cordPossi.getY()));
					matriz[atual.getX()][atual.getY()] = '*';
					possibilidades.recupereUmItem().removaUmItem();
					
					inverso = false;
					andarPraFrente = true;
				}
				
				if ((atual.getX()-1 == cordPossi.getX()) && (atual.getY()+1 == cordPossi.getY()))
				{
					atual = new Coordenada(cordPossi.getX(),cordPossi.getY());
					caminho.guardeUmItem( new Coordenada(cordPossi.getX(),cordPossi.getY()));
					matriz[atual.getX()][atual.getY()] = '*';
					possibilidades.recupereUmItem().removaUmItem();
					
					inverso = false;
					andarPraFrente = true;
				}
				
				if ((atual.getX() == cordPossi.getX()) && (atual.getY()-1 == cordPossi.getY()))
				{
					atual = new Coordenada(cordPossi.getX(),cordPossi.getY());
					caminho.guardeUmItem( new Coordenada(cordPossi.getX(),cordPossi.getY()));
					matriz[atual.getX()][atual.getY()] = '*';
					possibilidades.recupereUmItem().removaUmItem();
					
					inverso = false;
					andarPraFrente = true;
				}
				
				//printa o caminho 
				for(int i = 0; i < valorLinha; i++)
				{
					for(int j = 0; j < valorColuna; j++)
					{
						System.out.print(matriz[i][j]);
					}
					System.out.print("\n");
				}
				
				Thread.sleep(350);
			}
			
		}
		return atual;
	}
}
