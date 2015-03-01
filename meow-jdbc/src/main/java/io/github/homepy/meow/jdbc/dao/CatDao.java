package io.github.homepy.meow.jdbc.dao;

import io.github.homepy.meow.pojo.Cat;

public interface CatDao {

	Cat selectCat(String name);

	int insertCat(Cat obj);

	int updateCat(Cat obj);

	int deleteCat(String name);
}
