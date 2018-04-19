/*
 * Copyright (c) 2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brotherhui.tcc.order.domain.repository;

import com.brotherhui.tcc.order.domain.entity.OrderItemParticipantEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemParticipantRepository extends JpaRepository<OrderItemParticipantEntry, String> {

    
    List<OrderItemParticipantEntry> findAllById(long id);

    @Modifying
    @Query("update OrderItemParticipantEntry oipe set oipe.tccStatus = 1 where oipe.orderId = :id")
    public void confirmStatus(@Param(value = "id") long id);

    @Modifying
    @Query("update OrderItemParticipantEntry oipe set oipe.tccStatus = 2 where oipe.orderId = :id")
    void cancelStatus(@Param("id") long id);
}
