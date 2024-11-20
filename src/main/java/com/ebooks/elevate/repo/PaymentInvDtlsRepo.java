package com.ebooks.elevate.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebooks.elevate.entity.PaymentInvDtlsVO;
import com.ebooks.elevate.entity.PaymentVO;

@Repository
public interface PaymentInvDtlsRepo extends JpaRepository<PaymentInvDtlsVO, Long>{

	List<PaymentInvDtlsVO> findByPaymentVO(PaymentVO paymentVO);

}