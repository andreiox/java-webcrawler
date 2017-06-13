package persistence;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Page implements Serializable {

	private int		id;
	private int		generationNumber;
	private String	pageUrl;
	private boolean	visited;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGenerationNumber() {
		return generationNumber;
	}

	public void setGenerationNumber(int generationNumber) {
		this.generationNumber = generationNumber;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

}
