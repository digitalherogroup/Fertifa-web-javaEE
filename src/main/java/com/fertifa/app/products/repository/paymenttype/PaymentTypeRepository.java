package com.fertifa.app.products.repository.paymenttype;

import com.fertifa.app.products.model.paymenttype.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType,Long> {
}
