package com.cafe24.shopmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.CategoryDAO;
import com.cafe24.shopmall.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDao;
	
	public Integer add(CategoryVo categoryVo) {
		// 최상위 카테고리 이면
		if("".equals(categoryVo.getCatg_top_no())) {
			categoryVo.setCatg_top_no(null);
		}
		return categoryDao.insert(categoryVo);
	}

}
