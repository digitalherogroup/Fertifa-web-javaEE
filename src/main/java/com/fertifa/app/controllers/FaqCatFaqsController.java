package com.fertifa.app.controllers;

import com.fertifa.app.dao.FaqCatFaqsDao;
import com.fertifa.app.models.FaqsCatFaqs;
import com.fertifa.app.services.FaqCatFaqsControllerService;

import java.sql.SQLException;
import java.util.List;

public class FaqCatFaqsController implements FaqCatFaqsControllerService {
    private FaqCatFaqsDao  faqCatFaqsDao = new FaqCatFaqsDao();

    @Override
    public List<FaqsCatFaqs> getAllFasqAndCategories() throws SQLException {
        return faqCatFaqsDao.getAllFaqsCatsFasq();
    }
}
