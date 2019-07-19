package com.cafe24.shopmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.vo.CategoryVo;

@Repository
public class CategoryDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Integer insert(CategoryVo categoryVo) {
		sqlSession.insert("category.insert", categoryVo);
		return categoryVo.getNo();
	}
	
	// 상위카테고리가 존재하는지 체크
	public boolean isExistTopNo(Integer value) {
		Integer result = sqlSession.selectOne("category.isExistTopNo", value);
		return result==1;
	}

	public List<CategoryVo> getList(Integer no) {
		return sqlSession.selectList("category.getList", no);
	}

	public CategoryVo getInfo(Integer no) {
		return sqlSession.selectOne("category.getInfo", no);
	}

	public Integer update(CategoryVo categoryVo) {
		return sqlSession.update("category.update", categoryVo);
	}

	public Boolean delete(Integer no) {
		int cnt = sqlSession.delete("category.delete",no);
		return cnt > 0;
	}

}
