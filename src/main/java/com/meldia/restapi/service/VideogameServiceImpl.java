package com.meldia.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meldia.restapi.dto.VideogameDTO;
import com.meldia.restapi.model.VideogameModel;
import com.meldia.restapi.payload.VideogameRequest;
import com.meldia.restapi.payload.VideogameResponse;
import com.meldia.restapi.repository.VideogameRepository;

@Service
public class VideogameServiceImpl implements VideogameService {

	@Autowired
	public VideogameRepository repository;

	// LIST ALL PRODUCTS
	@Override
	public VideogameResponse allProducts() {

		List<VideogameModel> data = repository.findAll();
		List<VideogameDTO> dataDto = new ArrayList<>();
		data.forEach(dato -> {
			dataDto.add(new VideogameDTO(dato));
		});

		return new VideogameResponse("0", dataDto, "The list of products was delivered.");

	}

	// ADD VIDEOGAME PRODUCT
	@Override
	public VideogameResponse addProduct(VideogameRequest rq) {

		Boolean exists = exists(rq);
		if (!(StringUtils.isAllEmpty(rq.getName(), rq.getDescription(), rq.getPrice(), rq.getStock()))) {
			if (exists) {
				return new VideogameResponse("1", null, "The product already exists.");

			} else {
				VideogameModel newVideogame = new VideogameModel(rq);
				repository.save(newVideogame);

				return new VideogameResponse("0", null, "The product was added.");
			}

		}
		return new VideogameResponse("1", null, "The product wasn't added.");

	}

	// UPDATE VIDEOGAME PRODUCT
	@Override
	public VideogameResponse updateProduct(VideogameRequest rq) {

		if (!(StringUtils.isAllEmpty(rq.getName(), rq.getDescription(), rq.getPrice(), rq.getStock()))) {

			try {
				VideogameModel updateVideogame = repository.findId(rq.getId());
				updateVideogame.Update(rq);
				repository.save(updateVideogame);
				return new VideogameResponse("0", null, "The product was updated.");

			} catch (Exception e) {
				return new VideogameResponse("1", null, "The product wasn't updated or doesn't exist.");
			}

		}
		return new VideogameResponse("1", null, "The product wasn't updated or doesn't exist.");

	}
	
	// INACTIVATE VIDEOGAME PRODUCT
	@Override
	public VideogameResponse inactivateProduct(VideogameRequest rq) {
		
		if (!(StringUtils.isAllEmpty(rq.getName(), rq.getDescription(), rq.getPrice(), rq.getStock()))) {

			try {
				VideogameModel inactivateVideogame = repository.findId(rq.getId());
				inactivateVideogame.setActive("0");
				inactivateVideogame.setStock("NO STOCK");
				repository.save(inactivateVideogame);
				return new VideogameResponse("0", null, "The product was inactivated.");

			} catch (Exception e) {
				return new VideogameResponse("1", null, "The product wasn't inactivated or doesn't exist.");
			}

		}
		return new VideogameResponse("1", null, "The product wasn't inactivated or doesn't exist.");
		
	}

	public boolean exists(VideogameRequest rq) {
		VideogameModel exists = repository.exists(rq.getName(), rq.getDescription(), rq.getPrice(), rq.getStock());
		return exists != null;
	}

}
