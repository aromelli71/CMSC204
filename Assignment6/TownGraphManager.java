import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph graph = new Graph();
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		return (graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null);			
	}

	@Override
	public String getRoad(String town1, String town2) {
		return (graph.getEdge(new Town(town1), new Town(town2)).getName());
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}
	
	@Override
	public Town getTown(String name) {
		if(graph.containsVertex(new Town(name))) {
			return new Town(name);
		} else return null;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<Road> roads = new ArrayList<Road>(graph.edgeSet());
		ArrayList<String> output = new ArrayList<String>();
		for(Road r : roads) {
			output.add(r.getName());
		}
		Collections.sort(output);
		return output;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		return (graph.removeEdge(new Town(town1), new Town(town2), -1, road) != null);
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<Town> towns = new ArrayList<Town>(graph.vertexSet());
		ArrayList<String> output = new ArrayList<String>();
		for(Town t: towns) {
			output.add(t.getName());
		}
		Collections.sort(output);
		return output;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException, java.io.IOException {
		Scanner fin = new Scanner(selectedFile);
		while (fin.hasNextLine()) {
			try {
			String[] line = fin.nextLine().split(",|\\;");
			graph.addVertex(new Town(line[2]));
			graph.addVertex(new Town(line[3]));
			graph.addEdge(new Town(line[2]), new Town(line[3]), Integer.parseInt(line[1]), line[0]);
			}
			catch (Exception e) {
				fin.close();
				throw new java.io.IOException();
			}
		}
		fin.close();
	}

}
