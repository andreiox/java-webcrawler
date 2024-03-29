package persistence;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import util.URLUtil;

@Entity
@Table(name = "page")
@Cacheable(false)
public class Page implements Serializable {
	private static final long serialVersionUID = -8740428081918482555L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "generation_number")
	private int generationNumber;

	@Lob
	@Column(name = "page_url")
	private String pageUrl;

	@Column(name = "domain")
	private String domain;

	@Column(name = "visited")
	private boolean visited;

	public Page() {
	}

	public Page(int generationNumber, String pageUrl, boolean visited) {
		this.generationNumber = generationNumber;
		this.pageUrl = pageUrl;
		this.visited = visited;
		this.domain = URLUtil.extractDomainFromLink(pageUrl);
	}

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

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getDomain() {
		return domain;
	}

}
