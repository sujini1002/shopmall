package com.cafe24.shopmall.service;

import java.util.List;

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

	public List<CategoryVo> list(Integer no) {
		
		return categoryDao.getList(no);
	}

	public CategoryVo getCategoryInfo(Integer no) {
		return categoryDao.getInfo(no);
	}

	public CategoryVo update(CategoryVo categoryVo) {
		
		categoryDao.update(categoryVo);
		
		return categoryDao.getInfo(categoryVo.getNo());
	}

	public Boolean delete(Integer no) {
		return categoryDao.delete(no);
	}

}
