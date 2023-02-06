package com.meldia.restapi.service;

import com.meldia.restapi.payload.VideogameRequest;
import com.meldia.restapi.payload.VideogameResponse;

public interface VideogameService {

	VideogameResponse addProduct(VideogameRequest rq);

	VideogameResponse allProducts();

	VideogameResponse updateProduct(VideogameRequest rq);

	VideogameResponse inactivateProduct(VideogameRequest rq);

}
