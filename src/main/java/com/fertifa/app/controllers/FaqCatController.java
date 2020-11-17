package com.fertifa.app.controllers;

import com.fertifa.app.dao.FaqCatDao;
import com.fertifa.app.models.FaqCat;
import com.fertifa.app.services.FaqCategoryService;

import java.sql.SQLException;
import java.util.List;

public class FaqCatController implements FaqCategoryService {

    private FaqCatDao faqCatDao = new FaqCatDao();

    @Override
    public int addFaqCat(FaqCat faqCat) throws SQLException {
        return faqCatDao.AddNewFaqCategory(faqCat);
    }

    @Override
    public List<FaqCat> findFaqCatById(int id) throws SQLException {
        return faqCatDao.getFaqCategoryBYId(id);
    }

    @Override
    public List<FaqCat> findFaqCatAll() throws SQLException {
        return faqCatDao.getAllFaqCategory();
    }

    @Override
    public int deletefaqCatById(int id) throws SQLException {
        return faqCatDao.deleteFaqCategory(id);

    }

    @Override
    public int updateFaqCategory(FaqCat faqCat, int faqCatId) throws SQLException {
        return faqCatDao.updateFaqCategory(faqCat,faqCatId);
    }

    @Override
    public boolean CheckIfCategoryExists(String Category) throws SQLException {
        return faqCatDao.isCategoryexsits(Category);

    }

    @Override
    public String FindCategoryNameById(int faqCatId) throws SQLException {
        return faqCatDao.getFaqCatNameById(faqCatId);
    }

    @Override
    public List<FaqCat> findFaqCatAllForCompany() throws SQLException {
        return faqCatDao.findFaqCatAllForCompany();
    }

    @Override
    public List<FaqCat> findFaqCatAllForUsers() throws SQLException {
        return faqCatDao.findFaqCatAllForUsers();
    }
}
