package com.fertifa.app.dao;


import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.EmployerDiscountsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class EmployerDiscountModelDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    private void closingConnections(Connection connection, PreparedStatement statment, ResultSet set) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (set != null) {
            set.close();
        }
        if (statment != null) {
            statment.close();
        }
    }

    public int save(EmployerDiscountsModel employerDiscountsModel) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `employer-discounts`" +
                "(`id`, `employer_id`,`monthly_employee_fee`,`percentage_discount`,`months_of_discount`,`lastupdated`,`total_discount_amount`,`total_fee_by_months`) "
                + "VALUES (Default,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentDiscount(employerDiscountsModel, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection,statment,null);
        }
        return rowsAffected;
    }

    private void setStatmentDiscount(EmployerDiscountsModel employerDiscountsModel, PreparedStatement statment) throws SQLException {

        statment.setInt(1, employerDiscountsModel.getEmployerId());
        statment.setDouble(2, employerDiscountsModel.getMonthlyEmployeeFee());
        statment.setDouble(3, employerDiscountsModel.getPercentageDiscount());
        statment.setDouble(4, employerDiscountsModel.getMonthsOfDiscount());
        statment.setTimestamp(5, new Timestamp(new Date().getTime()));
        statment.setDouble(6,employerDiscountsModel.getTotalDiscountAmount());
        statment.setDouble(7,employerDiscountsModel.getTotalFeeByMonths());
    }

    public EmployerDiscountsModel findById(int linkId) throws SQLException {
        EmployerDiscountsModel employerDiscountsModel = new EmployerDiscountsModel();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `employer-discounts` where `employer_id`=" + linkId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                employerDiscountsModel = EmployerObject(set);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closingConnections(connection,statment,set);
        }
        return employerDiscountsModel;
    }

    private EmployerDiscountsModel EmployerObject(ResultSet set) throws SQLException {
        Integer discountAmount = EmployerDiscountsModel.CalculateDiscount(set.getInt("monthly_employee_fee"),set.getInt("monthly_employee_fee"),set.getInt("percentage_discount"));
        Integer totalAfterMonths = EmployerDiscountsModel.CalculateFinalAmount(discountAmount,set.getInt("months_of_discount"));
        return EmployerDiscountsModel.builder()
            .id(set.getInt("id"))
            .employerId(set.getInt("employer_id"))
            .monthlyEmployeeFee(set.getInt("monthly_employee_fee"))
            .percentageDiscount(set.getInt("percentage_discount"))
            .monthsOfDiscount(set.getInt("months_of_discount"))
            .totalDiscountAmount(discountAmount)
            .totalFeeByMonths(totalAfterMonths)
            .build();
    }


    public int updateDiscounts(EmployerDiscountsModel employerDiscountsModel) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `employer-discounts`  " +
                "SET `percentage_discount`=?, `months_of_discount`=?, `total_discount_amount`=?, `total_fee_by_months`=?,`lastupdated`=? WHERE `employer_id`=" + employerDiscountsModel.getEmployerId();
            statment = connection.prepareStatement(sql);
            statment.setInt(1, employerDiscountsModel.getPercentageDiscount());
            statment.setInt(2, employerDiscountsModel.getMonthsOfDiscount());
            statment.setFloat(3, employerDiscountsModel.getTotalDiscountAmount());
            statment.setFloat(4, employerDiscountsModel.getTotalFeeByMonths());
            statment.setTimestamp(5, employerDiscountsModel.getUpdated());

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closingConnections(connection,statment,null);
        }
        return rowsUpdated;
    }
}
