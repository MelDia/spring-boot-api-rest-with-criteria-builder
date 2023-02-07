package com.meldia.restapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

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

	@Autowired
	public EntityManager em;

	// FILTERS
	@Override
	public VideogameResponse filters(VideogameRequest rq) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<VideogameModel> cq = cb.createQuery(VideogameModel.class);
		Root<VideogameModel> root = cq.from(VideogameModel.class);

		List<Predicate> filters = new ArrayList<>();

		try {
			if (StringUtils.isNotBlank(rq.getName()) && StringUtils.isNotEmpty(rq.getName())) {
				filters.add(cb.equal(root.get("name"), rq.getName()));
			}
			if (StringUtils.isNotBlank(rq.getStock()) && StringUtils.isNotEmpty(rq.getStock())) {
				filters.add(cb.equal(root.get("stock"), rq.getStock()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (filters.isEmpty()) {
			cq.select(root).orderBy(cb.asc(root.get("id")));
		} else {
			cq.select(root).where(filters.toArray(new Predicate[filters.size()])).orderBy(cb.asc(root.get("id")));
		}

		TypedQuery<VideogameModel> query = em.createQuery(cq);
		List<VideogameModel> results = query.getResultList();

		List<VideogameDTO> dataDto = new ArrayList<>();
		results.forEach(data -> {
			dataDto.add(new VideogameDTO(data));
		});

		if (dataDto.isEmpty())
			return new VideogameResponse("1", null, "The list of products wasn't delivered.");

		return new VideogameResponse("0", dataDto, "The list of products was delivered.");

	}

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

		Boolean productExist = productExist(rq);
		if (productExist == true) {
			return new VideogameResponse("1", null, "The product already exists. You must modify the data.");
		}

		if (!(StringUtils.isAllEmpty(rq.getName(), rq.getDescription(), rq.getPrice(), rq.getStock()))) {
			VideogameModel newVideogame = new VideogameModel(rq);
			repository.save(newVideogame);
			return new VideogameResponse("0", null, "The product was added.");

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

	public boolean productExist(VideogameRequest rq) {
		VideogameModel productExist = repository.productExist(rq.getName());

		if (productExist == null) {
			System.out.println("esta nulo");
			return false;
		}

		if (productExist.getName().equalsIgnoreCase(rq.getName())) {
			System.out.println("producto existe");
			return true;
		}
		System.out.println("producto no existe");
		return false;
	}

}
