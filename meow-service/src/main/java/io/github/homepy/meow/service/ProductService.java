package io.github.homepy.meow.service;

import io.github.homepy.meow.pojo.Product;

public interface ProductService {
	Product add(Product product);

	Product get(long id);
}
