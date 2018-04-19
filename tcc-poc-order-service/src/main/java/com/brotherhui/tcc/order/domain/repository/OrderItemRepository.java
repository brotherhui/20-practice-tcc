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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brotherhui.tcc.order.domain.entity.OrderItemEntry;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntry, String> {

    
    List<OrderItemEntry> findAllByOrderIdAndAvailableStatus(long id, int availableStatus);
    
    @Modifying
    @Query("update OrderItemEntry oie set oie.availableStatus = 1 where oie.orderId = :id")
    public void confirmStatus(@Param(value = "id") long id);

    @Modifying
    @Query("delete from OrderItemEntry oie where oie.orderId = :id")
    void cancelStatus(@Param("id") long id);
}
