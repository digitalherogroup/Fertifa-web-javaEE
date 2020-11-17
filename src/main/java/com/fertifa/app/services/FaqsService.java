package com.fertifa.app.services;

import com.fertifa.app.models.Faq;

import java.sql.SQLException;
import java.util.List;

public interface FaqsService {

    List<Faq> getAllFaqs() throws SQLException;
    int UpdateFaqAnsAndQues(Faq faq, int faqId) throws SQLException;
    int UpdateFaqCategoryInFaq(Faq faq, int faqId) throws SQLException;
    int AddNewFaq(Faq faq) throws SQLException;
    List<Faq> getFaqById(int id) throws SQLException;
    int deleteFaq(int id) throws SQLException;


    List<Faq> getAllFaqsForCompany() throws SQLException;
    List<Faq> getAllFaqsForUsers() throws SQLException;
}
