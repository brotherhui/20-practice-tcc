
package com.brotherhui.tcc.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brotherhui.tcc.order.domain.entity.OrderParticipantEntry;

@Repository
public interface OrderParticipantRepository extends JpaRepository<OrderParticipantEntry, String> {

	OrderParticipantEntry findOneById(long id);
	
	OrderParticipantEntry findOneByOrderId(long id);
	
    @Modifying
    @Query("update OrderParticipantEntry ope set ope.tccStatus = 1 where ope.orderId = :id")
    void confirmStatus(@Param(value = "id") long id);

    @Modifying
    @Query("update OrderParticipantEntry ope set ope.tccStatus = 2 where ope.orderId = :id")
    void cancelStatus(@Param("id") long id);
}
