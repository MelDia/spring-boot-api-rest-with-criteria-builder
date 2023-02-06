package com.meldia.restapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meldia.restapi.payload.VideogameRequest;
import com.meldia.restapi.util.DataComparatorUtil;

@Entity
@Table(name = "videogame_store")
public class VideogameModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("stock")
	private String stock;
	
	@JsonProperty("active")
	private String active;
	
	public VideogameModel() {
		
	}

	public VideogameModel(VideogameRequest rq) {
		setName(rq.getName());
		setDescription(rq.getDescription());
		setPrice(rq.getPrice());
		this.stock = rq.getStock().equals("0") ? "NO STOCK" : rq.getStock();
	}
	
	public void Update(VideogameRequest rq) {
		setName(DataComparatorUtil.comparator(rq.getName(), name));
		setDescription(DataComparatorUtil.comparator(rq.getDescription(), description));
		setPrice(DataComparatorUtil.comparator(rq.getPrice(), price));
		setStock(DataComparatorUtil.comparator(rq.getStock().equals("0") ? "NO STOCK" : rq.getStock(), stock));
		setActive(DataComparatorUtil.comparator("1", "1"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	
	
	

}
