package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Videostate entity. @author MyEclipse Persistence Tools
 */
public class Videostate implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer order;
	private String cssstyle;
	private String remark;
	private Set<Video> videos = new HashSet<Video>(0);

	// Constructors

	/** default constructor */
	public Videostate() {
	}

	/** minimal constructor */
	public Videostate(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Videostate(Integer id, String name, Integer order, String cssstyle,
			String remark, Set<Video> videos) {
		this.id = id;
		this.name = name;
		this.order = order;
		this.cssstyle = cssstyle;
		this.remark = remark;
		this.videos = videos;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getCssstyle() {
		return this.cssstyle;
	}

	public void setCssstyle(String cssstyle) {
		this.cssstyle = cssstyle;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

}