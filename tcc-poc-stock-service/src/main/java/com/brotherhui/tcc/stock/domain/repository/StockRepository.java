package com.brotherhui.tcc.stock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brotherhui.tcc.stock.domain.entity.StockEntry;

@Repository
public interface StockRepository extends JpaRepository<StockEntry, String> {

    
	StockEntry findOneByProductIdAndAvailableStatus(String productId, int availableStatus);
	
    @Modifying
    @Query("update StockEntry se set se.stockNumber = se.stockNumber-:number where se.productId = :id")
    public void reduceStock(@Param(value = "id") String id, @Param(value = "number") int number);
    
    @Modifying
    @Query("update StockEntry se set se.stockNumber = se.stockNumber+:number where se.productId = :id")
    public void addStock(@Param(value = "id") String id, @Param(value = "number") int number);

}
