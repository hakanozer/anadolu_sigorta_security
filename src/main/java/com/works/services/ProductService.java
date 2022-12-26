package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository repository;

    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Optional<Product> optionalProduct = repository.findByTitleEqualsIgnoreCase(product.getTitle());
            if (optionalProduct.isPresent()) {
                throw new RuntimeException("");
            }else {
                repository.save(product);
                hm.put(REnum.status, true);
                hm.put(REnum.result, product);
                return new ResponseEntity(hm, HttpStatus.OK);
            }
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.errors, "Title Unique Error: "+ product.getTitle() );
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, repository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
