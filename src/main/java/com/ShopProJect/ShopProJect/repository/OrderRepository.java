package com.ShopProJect.ShopProJect.repository;

import com.ShopProJect.ShopProJect.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
