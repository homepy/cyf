package com.github.homepy.meow.mapper;

import com.github.homepy.meow.pojo.Cat;

public interface CatMapper {

	Cat selectCat(String name);

	int insertCat(Cat obj);

	int updateCat(Cat obj);

	int deleteCat(String name);
}
