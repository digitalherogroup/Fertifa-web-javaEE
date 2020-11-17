package com.fertifa.app.controllers;

import com.fertifa.app.dao.FaqsDao;
import com.fertifa.app.models.Faq;
import com.fertifa.app.services.FaqsService;

import java.sql.SQLException;
import java.util.List;

public class FaqController implements FaqsService {
    private FaqsDao faqsDao  = new FaqsDao();

    @Override
    public List<Faq> getAllFaqs() throws SQLException {
        return faqsDao.showAllFaqs();
    }

    @Override
    public int UpdateFaqAnsAndQues(Faq faq, int faqId) throws SQLException {
        return faqsDao.updateFaqQuesAnsw(faq,faqId);
    }

    @Override
    public int UpdateFaqCategoryInFaq(Faq faq, int faqId) throws SQLException {
        return faqsDao.updateFaqCategoryInFaq(faq,faqId);
    }

    @Override
    public int AddNewFaq(Faq faq) throws SQLException {
        return faqsDao.AddNewFaq(faq);
    }

    @Override
    public List<Faq> getFaqById(int id) throws SQLException {
        return faqsDao.getFaqBYId(id);
    }

    @Override
    public int deleteFaq(int id) throws SQLException {
        return faqsDao.deleteFaq(id);
    }

    @Override
    public List<Faq> getAllFaqsForCompany() throws SQLException {
        return faqsDao.getAllFaqsForCompany();
    }

    @Override
    public List<Faq> getAllFaqsForUsers() throws SQLException {
        return faqsDao.getAllFaqsForUsers();
    }


    public List<Faq> GetFaqByFaqcCatId(int intCategoryFor) throws SQLException {
        return faqsDao.GetFaqByFaqcCatId(intCategoryFor);
    }

    public List<Faq> getFaqBYFaqId(int id) throws SQLException {
        return faqsDao.getFaqBYFaqId(id);
    }
}
