package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.FaqsCatFaqs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FaqCatFaqsDao {

    /**
     * Data gates
     *
     * @return
     * @throws SQLException
     */
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    /**
     * Getting all com.fertifa.app.Faqs and com.fertifa.app.Faqs Categories by Inner Join
     * @return
     * @throws SQLException
     */
    public List<FaqsCatFaqs> getAllFaqsCatsFasq() throws SQLException {
        List<FaqsCatFaqs> faqsCatFaqsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set =null;
        FaqsCatFaqs faqsCatFaqs = null;
        try{
            connection = ConnectToData();
            String sql = "select * from `faqs` inner join `faqcat` on `faqs`.`faqcatid`=`faqcat`.`id` ORDER BY `faqs`.`id` DESC";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()){
                faqsCatFaqs = new FaqsCatFaqs();
                faqsCatFaqs.setFaqId(set.getInt("id"));
                faqsCatFaqs.setCategoryId(set.getInt("faqcatid"));
                faqsCatFaqs.setCategoryName(set.getString("faqcatname"));
                faqsCatFaqs.setFaqquestion(set.getString("faqquestion"));
                faqsCatFaqs.setFaqanswear(set.getString("faqanswear"));
                faqsCatFaqsList.add(faqsCatFaqs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                connection.close();
            }
            if(set != null){
                set.close();
            }
            if(statement != null){
                statement.close();
            }
        }
        return faqsCatFaqsList;
    }
}
