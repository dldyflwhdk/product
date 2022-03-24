package com.kh.product.domain.dao;

import com.kh.product.domain.dto.Product;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@ToString
@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

    private final JdbcTemplate jdbcTemplate;

    /**
     * 등록
     * @param product
     * @return
     */
    @Override
    public Product save(Product product) {
        log.info("product1={}",product);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into product ");
        sql.append("values(product_pid_seq.nextval, ?, ?, ?) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        sql.toString(),
                        new String[]{"pid"}
                );
                pstmt.setString(1,product.getPname());
                pstmt.setLong(2,product.getAmount());
                pstmt.setLong(3,product.getPrice());
                return pstmt;
            }
        },keyHolder);

        log.info("상품등록");
        Long pid = keyHolder.getKey().longValue();
        return findById(pid);
   }

    /**
     * 조회
     * @param id
     * @return
     */
    @Override
    public Product findById(Long id) {

        StringBuffer sql = new StringBuffer();
        sql.append("select pid , pname, amount, price ");
        sql.append("from product ");
        sql.append("where pid = ? ");

        List<Product> product = jdbcTemplate.query(
                sql.toString(),
                new BeanPropertyRowMapper<>(Product.class),
                id
        );
        log.info("product2={}",product);
        log.info("상품조회");
        return (product.size() == 1) ? product.get(0) : null;
    }

    /**
     * 수정
     * @param id, product
     * @return
     */
    @Override
    public Product update(Long id, Product product) {
        log.info("product3={}",product);
        StringBuffer sql = new StringBuffer();
        sql.append("update product ");
        sql.append("set pname = ?, ");
        sql.append("amount = ?, ");
        sql.append("price = ? ");
        sql.append("where pid = ? ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        sql.toString(),
                        new String[]{"pid"}  // update 후 update 레코드중 반환할 컬럼명, KeyHolder에 저장됨.
                );

                pstmt.setString(1, product.getPname());
                pstmt.setLong(2, product.getAmount());
                pstmt.setLong(3, product.getPrice());
                pstmt.setLong(4, id);

                return pstmt;
            }
        },keyHolder);

        long pid = Long.valueOf(keyHolder.getKeys().get("pid").toString());
        return findById(pid);

    }

    /**
     * 삭제
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from product ");
        sql.append("where pid = ? ");

        int result = jdbcTemplate.update(sql.toString(), id);
        log.info("product4={}",result);
        log.info("상품삭제");
        return result;
    }

    /**
     * 전체조회
     * @return
     */
    @Override
    public List<Product> listAll() {
        StringBuffer sql = new StringBuffer();

        sql.append("select pid, pname, amount, price ");
        sql.append("from product ");
        sql.append("order by pid ");

        List<Product> list = jdbcTemplate.query(
                sql.toString(),
                new BeanPropertyRowMapper<>(Product.class)
        );
        log.info("product5={}",list);
        return list;
    }
}
