package com.meldia.restapi.dto;

import com.meldia.restapi.model.VideogameModel;

public class VideogameDTO {

	private String name;
	private String description;
	private String price;
	private String stock;

	public VideogameDTO() {

	}

	public VideogameDTO(VideogameModel dato) {
		setName(dato.getName());
		setDescription(dato.getDescription());
		setPrice(dato.getPrice());
		setStock(dato.getStock());
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
	
	

}
