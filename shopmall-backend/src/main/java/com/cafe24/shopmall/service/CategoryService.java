package com.cafe24.shopmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shopmall.repository.CategoryDAO;
import com.cafe24.shopmall.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDao;
	
	@Transactional
	public Integer add(CategoryVo categoryVo) {
		// 최상위 카테고리 이면
		if(categoryVo.getCatg_top_no()== null) {
			System.out.println("들어옴");
			categoryVo.setCatg_top_no(null);
			categoryVo.setLevel(0);
		}
		return categoryDao.insert(categoryVo);
	}

	public List<CategoryVo> list() {
		
		return categoryDao.getList();
	}

	public CategoryVo getCategoryInfo(Integer no) {
		return categoryDao.getInfo(no);
	}
	@Transactional
	public CategoryVo update(CategoryVo categoryVo) {
		
		if(categoryVo.getCatg_top_no()==null) {
			categoryVo.setLevel(0);
		}
		System.out.println(categoryVo);
		
		categoryDao.update(categoryVo);
		
		return categoryDao.getInfo(categoryVo.getNo());
	}
	@Transactional
	public Boolean delete(Integer no) {
		return categoryDao.delete(no);
	}

}
