package com.brotherhui.tcc.stock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brotherhui.tcc.stock.domain.entity.StockParticipantEntry;

@Repository
public interface StockParticipantRepository extends JpaRepository<StockParticipantEntry, String> {

	StockParticipantEntry findOneByTxId(String txId);
	
    @Modifying
    @Query("update StockParticipantEntry spe set spe.tccStatus = 1 where spe.txId = :txId")
    public void confirmStatus(@Param(value = "txId") String txId);
    
    @Modifying
    @Query("update StockParticipantEntry spe set spe.tccStatus = 2 where spe.txId = :txId")
    public void cancelStatus(@Param(value = "txId") String txId);
}
