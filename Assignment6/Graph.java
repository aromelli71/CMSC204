import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Graph implements GraphInterface<Town,Road> {
	private ArrayList<Town> towns = new ArrayList<>();
	private ArrayList<Road> roads = new ArrayList<>();
	private int[] distance;
	private String[] previous;

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null)
			return null;
		Road target = new Road(sourceVertex,destinationVertex,"");
		for (Road r : roads) {
			if(r.equals(target))
				return r;
		}
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		if(!towns.contains(sourceVertex) || !towns.contains(destinationVertex))
			throw new IllegalArgumentException();
		Road add = new Road(sourceVertex,destinationVertex,weight,description);
		for (Road r : roads) {
			if(r.equals(add))
				return null;
		}
		roads.add(add);
		return add;
	}

	@Override
	public boolean addVertex(Town v) {
		if(v == null)
			throw new NullPointerException();
		for (Town t : towns) {
			if(t.equals(v))
				return false;
		}
		towns.add(v);
		return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null)
			return false;
		Road target = new Road(sourceVertex,destinationVertex,"");
		for (Road r : roads) {
			if(r.equals(target))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		if(v == null)
			return false;
		for (Town t : towns) {
			if(t.equals(v))
				return true;
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		Set<Road> output = new HashSet<>();
		for (Road r : roads) {
			output.add(r);
		}
		return output;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null)
			throw new NullPointerException();
		if(!towns.contains(vertex))
			throw new IllegalArgumentException();
		Set<Road> output = new HashSet<>();
		for(Road r : roads)
			if(r.getSource().equals(vertex) || r.getDestination().equals(vertex))
				output.add(r);
		return output;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road output, target = new Road(sourceVertex,destinationVertex,weight,description);
		if (weight > -1 && description != null)
			for(Road r : roads)
				if(r.equals(target) && r.getWeight() == target.getWeight() && r.getName().equals(target.getName())) {
					output = r;
					roads.remove(r);
					return output;
				}
		if (weight > -1 && description == null)
			for(Road r : roads)
				if(r.equals(target) && r.getWeight() == target.getWeight()) {
					output = r;
					roads.remove(roads.indexOf(r));
					return output;
				}
		if (weight <= -1 && description != null)
			for(Road r : roads)
				if(r.equals(target) && r.getName().equals(target.getName())) {
					output = r;
					roads.remove(roads.indexOf(r));
					return output;
				}
		if (weight <= -1 && description == null)
			for(Road r : roads)
				if(r.equals(target)) {
					output = r;
					roads.remove(roads.indexOf(r));
					return output;
				}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		if(v == null || !towns.contains(v))
			return false;
		roads.removeAll(edgesOf(v));
		towns.remove(v);
		return true;
	}

	@Override
	public Set<Town> vertexSet() {
		Set<Town> output = new HashSet<Town>();
		for (Town t : towns) {
			output.add(t);
		}
		return output;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> pathway = new ArrayList<String>();
		ArrayList<Integer> degree = new ArrayList<Integer>();
		ArrayList<String> townNames = new ArrayList<>();
		for(Town t : vertexSet()) {
			townNames.add(t.getName());
		}
		
		int index = townNames.indexOf(destinationVertex.getName());
		pathway.add(destinationVertex.getName());
		while (previous[index] != null) {
			degree.add(distance[index]);
			pathway.add(previous[index]);
			index = townNames.indexOf(previous[index]);

		}
		Collections.reverse(degree);
		Collections.reverse(pathway);
		ArrayList<String> output = new ArrayList<String>();
		int total = 0;
		for (int i=0;i<pathway.size()-1;i++) {
			output.add(pathway.get(i) + " via "
					+ ((getEdge(new Town(pathway.get(i)), new Town(pathway.get(i+1)))).getName()) + 
					" to " + pathway.get(i+1) + " " + (degree.get(i)-total) + " mi");
			total += degree.get(i)-total;
		}
		return output;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		String sourceName = sourceVertex.getName();
		ArrayList<String> townNames = new ArrayList<String>();
		ArrayList<String> unreached = new ArrayList<String>();
		for (Town t : vertexSet()) {
			townNames.add(t.getName());
			unreached.add(t.getName());
		}
		distance = new int[townNames.size()];
		previous = new String[townNames.size()];

		Arrays.fill(distance, 999);
		distance[townNames.indexOf(sourceName)] = 0;

		while (!unreached.isEmpty()) {
			HashMap<String, Road> adjacent = getAdjacentTowns(new Town(sourceName));
			for (String s : adjacent.keySet())
				if (adjacent.get(s) != null && unreached.indexOf(s) != -1) {
					int weight = adjacent.get(s).getWeight();
					int curr_ind = townNames.indexOf(sourceName);
					int ind = townNames.indexOf(s);
					if (distance[ind] > distance[curr_ind] + weight) {
						previous[ind] = sourceName;
						distance[ind] = weight + distance[curr_ind];
					}
				}
			unreached.remove(unreached.indexOf(sourceName));
			if(unreached.isEmpty())
				break;
			int shortest_ind = -1;
			int shortest = 999;
			for (String s : unreached) {
				int ind = townNames.indexOf(s);
				if (shortest > distance[ind]) {
					shortest = distance[ind];
					shortest_ind = ind;
				}
			}
			if(shortest_ind == -1)
				break;
			sourceName = townNames.get(shortest_ind);
		}
	}
	
	public HashMap<String,Road> getAdjacentTowns(Town t){
		HashMap<String,Road> output = new HashMap<String,Road>();
		for(Road r : edgesOf(t)) {
			if (r.getSource().equals(t))
				output.put(r.getDestination().getName(), r);
			else output.put(r.getSource().getName(), r);
		}
		return output;
	}
}