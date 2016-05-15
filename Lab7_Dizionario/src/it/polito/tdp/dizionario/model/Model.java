package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.dizionario.db.ParolaDAO;
import javafx.scene.control.TextField;

public class Model {

	private List<Parola> diz = new ArrayList<>();
	private int length;

	private UndirectedGraph<Parola, DefaultEdge> graph;
	
	public List<Parola> caricaParole(int length){
		
		this.length=length;
		ParolaDAO dao = new ParolaDAO();
		diz = dao.searchByLength(length);
		return diz;
	}

	public int getLength() {
		return length;
	}
	public List<Parola> getDiz() {
		return diz;
	}
	
	public void generaGrafo(){
		graph = new SimpleGraph<Parola,DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(graph, diz);
		for (Parola p1 : this.diz) {
			for (Parola p2 : this.diz) {
				if (simili(p1, p2)) {
					graph.addEdge(p1, p2);
				}
			}
		
		
		}
	}

	private boolean simili(Parola p1, Parola p2) {
		String s1 = p1.getNome();
		String s2 = p2.getNome();
		int diff=0;
		
		for(int i=0;i<s1.length();i++)
		{
			if(s1.charAt(i) != s2.charAt(i))
				diff++;
		}

		if(diff==1)
			return true;
		else return false;
	}

	public List<Parola> trovaVicini(Parola parola) {
		
		
			return Graphs.neighborListOf(graph, parola);
			
	
		
	}

	public Parola findParola(String parola) {
		for(Parola p : diz)
		{
			if(p.getNome().equals(parola))
				return p;
				
		}
		return null;
			
	}

	public List<Parola> trovaTutti(Parola parola) {
		
		List<Parola> tutti = new ArrayList<>();
		
			
		BreadthFirstIterator<Parola, DefaultEdge> bfv = new BreadthFirstIterator<>(graph,parola);
		
		while(bfv.hasNext())
		{
			tutti.add(bfv.next());
		}
		return tutti;
	}
}
