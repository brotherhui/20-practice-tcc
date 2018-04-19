package com.brotherhui.tcc.stock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brotherhui.tcc.stock.domain.entity.ProductEntry;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntry, String> {

    ProductEntry findOneByIdAndAvailableStatus(long id, int availableStatus);
    
}
