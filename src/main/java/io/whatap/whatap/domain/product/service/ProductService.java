package io.whatap.whatap.domain.product.service;

import io.whatap.whatap.domain.product.Product;
import io.whatap.whatap.domain.product.dto.AddProductRequest;
import io.whatap.whatap.domain.product.dto.ProductResponse;
import io.whatap.whatap.domain.product.exception.ProductNotFoundException;
import io.whatap.whatap.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse save(AddProductRequest request) {
        return new ProductResponse(productRepository.save(request.toEntity()));
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.EXCEPTION);

        return new ProductResponse(product);
    }
}
