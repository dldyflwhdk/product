package com.kh.product.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Long pid;       //상품번호
    private String pname;   //상품명
    private Long amount;    //상품수량
    private Long price;     //상품가격
}
