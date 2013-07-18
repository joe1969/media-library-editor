package de.ravenfly.mle.modulebase.filemodel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "details")
public class Episode {

	@XmlElement(name = "id")
	private int    id;

	@XmlElement(name = "title")
	private String title;

	@XmlElement(name = "series_name")
	private String seriesName;

	@XmlElement(name = "episode_name")
	private String episodeName;

	@XmlElement(name = "season_number")
	private int    seasonNumber;

	@XmlElement(name = "episode_number")
	private int    episodeNumber;

	@XmlElement(name = "firstaired")
	private String firstaired;

	@XmlElement(name = "genre")
	private String genre;

	@XmlElement(name = "runtime")
	private int    runtime;

	@XmlElement(name = "director")
	private String director;

	@XmlElement(name = "actor")
	private String actor;

	@XmlElement(name = "overview")
	private String overview;

	@XmlElement(name = "backdrop")
	private List<Backdrop> backdrops;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getFirstaired() {
		return firstaired;
	}

	public void setFirstaired(String firstaired) {
		this.firstaired = firstaired;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public List<Backdrop> getBackdrops() {
		return backdrops;
	}

	public void setBackdrops(List<Backdrop> backdrops) {
		this.backdrops = backdrops;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actor == null) ? 0 : actor.hashCode());
		result = prime * result + ((backdrops == null) ? 0 : backdrops.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((episodeName == null) ? 0 : episodeName.hashCode());
		result = prime * result + episodeNumber;
		result = prime * result + ((firstaired == null) ? 0 : firstaired.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + ((overview == null) ? 0 : overview.hashCode());
		result = prime * result + runtime;
		result = prime * result + seasonNumber;
		result = prime * result + ((seriesName == null) ? 0 : seriesName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode other = (Episode) obj;
		if (actor == null) {
			if (other.actor != null)
				return false;
		} else if (!actor.equals(other.actor))
			return false;
		if (backdrops == null) {
			if (other.backdrops != null)
				return false;
		} else if (!backdrops.equals(other.backdrops))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (episodeName == null) {
			if (other.episodeName != null)
				return false;
		} else if (!episodeName.equals(other.episodeName))
			return false;
		if (episodeNumber != other.episodeNumber)
			return false;
		if (firstaired == null) {
			if (other.firstaired != null)
				return false;
		} else if (!firstaired.equals(other.firstaired))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (overview == null) {
			if (other.overview != null)
				return false;
		} else if (!overview.equals(other.overview))
			return false;
		if (runtime != other.runtime)
			return false;
		if (seasonNumber != other.seasonNumber)
			return false;
		if (seriesName == null) {
			if (other.seriesName != null)
				return false;
		} else if (!seriesName.equals(other.seriesName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Episode [id=" + id + ", title=" + title + ", seriesName="
				+ seriesName + ", episodeName=" + episodeName
				+ ", seasonNumber=" + seasonNumber + ", episodeNumber="
				+ episodeNumber + ", firstaired=" + firstaired + ", genre="
				+ genre + ", runtime=" + runtime + ", director=" + director
				+ ", actor=" + actor + ", overview=" + overview
				+ ", backdrops=" + backdrops + "]";
	}
}
