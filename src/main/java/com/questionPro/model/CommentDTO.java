package com.questionPro.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDTO {

	
	private int id;
	private String by;
	
	private ArrayList<Integer> kids;
	
	private String text;
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
