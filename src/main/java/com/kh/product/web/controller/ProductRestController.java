package com.kh.product.web.controller;

import com.kh.product.domain.dto.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductSVC productSVC;

    //등록
    @PostMapping
    public ApiResult<Object> save(@RequestBody Product product){
        log.info("product1={}",product);
        Product saveProduct = productSVC.save(product);
        return new ApiResult<>("00", "save", saveProduct);
    }

    //수정
    @PatchMapping("/{id}")
    public ApiResult<Object> update(@PathVariable Long id, @RequestBody Product product){
        Product updateProduct = productSVC.update(id, product);
        return new ApiResult<>("00", "update", updateProduct);
    }

    //조회
    @GetMapping("/{id}")
    public ApiResult<Object> findById(@PathVariable Long id){
        Product findProduct = productSVC.findById(id);
        return new ApiResult<>("00", "find", findProduct);
    }


    //삭제
    @DeleteMapping("/{id}")
    public ApiResult<Object> delete(@PathVariable Long id){
        int deleteProduct = productSVC.delete(id);
        return new ApiResult<>("00", "delete", deleteProduct);
    }

    //전체조회
    @GetMapping
    public ApiResult<Object> listAll(){
        List<Product> list = productSVC.listAll();
        return new ApiResult<>("00", "all", list);
    }
}
