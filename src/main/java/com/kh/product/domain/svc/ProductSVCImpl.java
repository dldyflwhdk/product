package com.kh.product.domain.svc;

import com.kh.product.domain.dao.ProductDAO;
import com.kh.product.domain.dto.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

    private final ProductDAO productDAO;

    /**
     * 등록
     * @param product
     * @return
     */
    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    /**
     * 조회
     * @param id
     * @return
     */
    @Override
    public Product findById(Long id) {
        return productDAO.findById(id);
    }

    /**
     * 수정
     * @param id, product
     * @return
     */
    @Override
    public Product update(Long id, Product product) {
        return productDAO.update(id, product);
    }

    /**
     * 삭제
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return productDAO.delete(id);
    }

    /**
     * 전체조회
     * @return
     */
    @Override
    public List<Product> listAll() {
        return productDAO.listAll();
    }
}
