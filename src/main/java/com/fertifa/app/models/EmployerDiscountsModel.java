package com.fertifa.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString

public class EmployerDiscountsModel {
    private Integer id;
    private Integer employerId;
    private Integer monthlyEmployeeFee = 20;
    private Integer percentageDiscount = 20;
    private Integer monthsOfDiscount = 1;
    private Integer totalDiscountAmount;
    private Integer totalFeeByMonths;
    private Timestamp updated;


    public static Integer CalculateFinalAmount(Integer discountAmount, int months_of_discount) {
        return discountAmount * months_of_discount;
    }

    public static Integer CalculateDiscount(Integer perEmployeeFee, Integer monthly_employee_fee, Integer percentage_discount) {
        return perEmployeeFee - ((monthly_employee_fee) * (percentage_discount / 10) / 10);
    }

}
