package com.questionPro.model;

import java.util.ArrayList;

import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;


@Entity
@Table(name="Story_Details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoriesDTO {

	@Id
	private int id;
	private int score;
	private String time;
	private String url;
	@Column(name="`by`")
	private String by;
	
	@Transient
	@JsonProperty
	private ArrayList<Integer> kids;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public ArrayList<Integer> getKids() {
		return kids;
	}
	public void setKids(ArrayList<Integer> kids) {
		this.kids = kids;
	}
	
	
	
	
}
