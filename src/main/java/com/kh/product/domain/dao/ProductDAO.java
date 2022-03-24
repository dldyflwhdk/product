package com.kh.product.domain.dao;

import com.kh.product.domain.dto.Product;
import java.util.List;

public interface ProductDAO {

    /**
     * 등록
     * @param product
     * @return
     */
    Product save(Product product);

    /**
     * 조회
     * @param id
     * @return
     */
    Product findById(Long id);

    /**
     * 수정
     * @param id, product
     * @return
     */
    Product update(Long id, Product product);

    /**
     * 삭제
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 전체조회
     * @return
     */
    List<Product> listAll();
}
