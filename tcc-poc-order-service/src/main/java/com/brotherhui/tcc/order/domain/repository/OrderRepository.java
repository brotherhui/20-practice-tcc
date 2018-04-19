package com.brotherhui.tcc.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brotherhui.tcc.order.domain.entity.OrderEntry;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntry, String> {

    OrderEntry findOneByIdAndAvailableStatus(long id, int availableStatus);
    
    @Modifying
    @Query("update OrderEntry oe set oe.availableStatus = 1 where oe.id = :id")
    public void confirmStatus(@Param(value = "id") long id);
    
    @Modifying
    @Query("delete from OrderEntry oe where oe.id = :id")
    public void cancelStatus(@Param("id") long id);
}
