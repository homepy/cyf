package io.github.homepy.meow.jdbc.dao;

import io.github.homepy.meow.pojo.Cat;

public interface CatDao {

	Cat get(String name);

	int insert(Cat obj);

	int update(Cat obj);

	int delete(String name);
}
