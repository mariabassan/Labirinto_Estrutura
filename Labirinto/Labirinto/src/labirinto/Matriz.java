package labirinto;

import java.io.BufferedReader;
import java.io.FileReader;

public class Matriz {
	
	public void Valida(String labirinto) throws Exception 
	{
		int valorLinha = 0;
		int valorColuna = 0;
		int aux = 0;
		int aux2 = 2;
		int entradas = 0;
		int saidas = 0;
		char[][] vetor = null;
		int index = 0;
		int indice = 0;
		
		BufferedReader reader = new BufferedReader(new FileReader(labirinto));
		
		String linha;
		
		linha = reader.readLine();
		
		try 
		{
			valorLinha = Integer.parseInt(linha);
		}
		catch (Exception e) 
		{
			throw new Exception("Valor indicado de linhas invalido");
		}
		
		while ((linha=reader.readLine()) != null)
		{
		
			if(aux == 0)
				valorColuna = linha.length();
			
			if(valorColuna!=linha.length())
				throw new Exception ("Erro no arquivo");
			
			if(linha.contains("E"))
				entradas++;
			
			if(linha.contains("S"))
				saidas++;
			
			if (aux2 == 2)
				vetor = new char [valorLinha][valorColuna];
			
			if(indice>0 && indice<valorLinha-1)
			{
				int pos =linha.indexOf('E');
				if(pos!=0 && pos!=valorColuna-1 && pos!=-1)
				{
					throw new Exception ("Apenas entradas pelas bordas são válidas!");
				}
				
				int pos1=linha.indexOf('S');
				if(pos1!=0 && pos1!=valorColuna-1 && pos1!=-1)
				{
					throw new Exception ("Apenas saídas pelas bordas são válidas!");
				}
			}
			
			if(indice>0 && indice<valorLinha-1)
			{
				int posicao = linha.indexOf(' ');
				int posicao2 = linha.lastIndexOf(' ');
				
				if(posicao == 0 && posicao2 != -1)
					throw new Exception ("Não pode ter espaços na borda");
				
				if(posicao2 == valorColuna-1 && posicao != -1)
					throw new Exception ("Não pode ter espaços na borda");	
			}
			else if (linha.indexOf(' ')!= -1)
					throw new Exception ("Não pode ter espaços na borda");
			
			index = 0;
			aux2 = 99;
			
			for(int y=0;y<valorColuna;y++)
			{
				vetor[indice][y]+=linha.charAt(index);	
				index++;
			}
			indice++;
			aux=99;
		}
		
		if (entradas>1)
			throw new Exception("Arquivo possui mais de uma entrada");
		if (saidas>1)
			throw new Exception("Arquivo possui mais de uma saida");
		if (entradas<=0)
			throw new Exception("Não possui entrada");
		if (saidas<=0)
			throw new Exception("Não possui saida");
		
		/*for(int i = 0; i < valorLinha; i++)
		{
			for(int j = 0; j < valorColuna; j++)
			{
				System.out.print(vetor[i][j]);
			}
			System.out.print("\n");
		}*/
		Coordenada fim = new Coordenada(0,0);
		Percorrer andar = new Percorrer();
		fim = andar.andar(valorLinha,valorColuna,vetor);
		
		if(vetor[fim.getX()][fim.getY()] == 'S')
			System.out.println("Parabéns você achou a saída!!!");
	}
}
