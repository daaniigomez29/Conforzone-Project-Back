package com.project.conforzone.repository;

import com.project.conforzone.model.PurchaseBookingModel;
import com.project.conforzone.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseBookingRepository extends JpaRepository<PurchaseBookingModel, Integer> {

    @Query("SELECT b FROM PurchaseBookingModel b WHERE b.userPurchase = :user")
    public List<PurchaseBookingModel> getAllUserPurchases(@Param("user") UserModel userModel);
}
