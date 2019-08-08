package com.cafe24.shopmall.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.shopmall.provider.AdminProductProvider;
import com.cafe24.shopmall.provider.CategoryProvider;
import com.cafe24.shopmall.vo.CategoryVo;
import com.cafe24.shopmall.vo.ProdImgVo;
import com.cafe24.shopmall.vo.ProductToVo;
import com.cafe24.shopmall.vo.ProductVo;

@Service
public class AdminProductService {
	
	@Autowired
	private AdminProductProvider adminProductProvider;
	
	@Autowired
	private CategoryProvider categoryProvider;

	public Long add(ProductVo productVo) {
		//provider에게 전달할 객체 생성
		ProductToVo productToVo = new ProductToVo();
		
		//상품명
		productToVo.settitle(productVo.getTitle());
		//상품 가격
		productToVo.setPrice(productVo.getPrice());
		//상품 상세정보
		productToVo.setDetail(productVo.getDetail());
		
		//product 이미지 리스트 생성
		List<ProdImgVo> prodImgList = new ArrayList<ProdImgVo>();
		//대표이미지 저장
		prodImgList.add(new ProdImgVo(null,null,fileRestore(productVo.getTitleimage()),true));
		//서브 이미지 저장
		for(MultipartFile image : productVo.getImages()) {
			String imageUrl = fileRestore(image);
			if(imageUrl!=null) {
				prodImgList.add(new ProdImgVo(null, null, fileRestore(image), false));
			}
		}
		// 전달할 객체에 이미지 리스트 저장
		productToVo.setprodImgList(prodImgList);
		
		// 카테고리 저장
		if(productVo.getBottomCategoryNo()==null) {
			productToVo.setCate_no(productVo.getTopCategoryNo());
		}else {
			productToVo.setCate_no(productVo.getBottomCategoryNo());
		}
		
		// 옵션 저장
		
		System.out.println(productToVo);
		return null;
	}
	
	public List<CategoryVo> getCategory(){
		return categoryProvider.getCategory();
	}

	// 이미지의 물리적 저장
	public String fileRestore(MultipartFile logoFile) {
		final String SAVE_PATH = "/shopmall-uploads";

		String logoName = "";
		try {
			if (logoFile.isEmpty()) {
				return null;
			}

			String originalFilename = logoFile.getOriginalFilename(); // 다른 사용자와 이름이 겺칠 수 있기 때문에 다른이름으로 저장한다.
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);// 확장자
			logoName = generateSaveFileName(extName);

			byte[] fileData = logoFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + logoName);
			os.write(fileData);
			os.close();

		} catch (IOException e) {
			throw new RuntimeException("Fileupload error:" + e);
		}

		return logoName;
	}

	// 이미지 사진 이름 구하기
	private String generateSaveFileName(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}
}
