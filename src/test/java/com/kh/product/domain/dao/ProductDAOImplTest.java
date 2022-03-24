package com.kh.product.domain.dao;

import com.kh.product.domain.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    @DisplayName("등록")
    void save() {
        Product product = new Product();
        product.setPname("제품2");
        product.setAmount(2L);
        product.setPrice(2222L);
        productDAO.save(product);
        Assertions.assertThat(productDAO.listAll().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("조회")
    void findById() {
        Product product = productDAO.findById(3L);
        Assertions.assertThat(product.getPid()).isEqualTo(3);
    }

    @Test
    @DisplayName("수정")
    void update() {
        //when
        Long id = 3L;
        Product product1 = productDAO.findById(id);
        product1.setPname("제품3 수정");
        product1.setAmount(4L);
        product1.setPrice(4444L);

        //try
        Product product2 = productDAO.update(id, product1);

        //then
        Assertions.assertThat(product2.getPname()).isEqualTo(product1.getPname());
        Assertions.assertThat(product2.getAmount()).isEqualTo(product1.getAmount());
        Assertions.assertThat(product2.getPrice()).isEqualTo(product1.getPrice());
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        Long p_id = 4L;
        productDAO.delete(p_id);
        Assertions.assertThat(productDAO.findById(p_id)).isNull();
    }

    @Test
    @DisplayName("전체조회")
    void listAll() {
        Assertions.assertThat(productDAO.listAll().size()).isNotNull();
    }
}