package com.fertifa.app.services;

import com.fertifa.app.models.FaqCat;

import java.sql.SQLException;
import java.util.List;

public interface FaqCategoryService {

    int addFaqCat(FaqCat faqCat) throws SQLException;
    List<FaqCat> findFaqCatById(int id) throws SQLException;
    List<FaqCat> findFaqCatAll() throws SQLException;
    int deletefaqCatById(int id) throws SQLException;
    int updateFaqCategory(FaqCat faqCat, int faqCatId) throws SQLException;
    boolean CheckIfCategoryExists(String Category) throws SQLException;
    String FindCategoryNameById(int faqCatId) throws SQLException;

    List<FaqCat> findFaqCatAllForCompany() throws SQLException;
    List<FaqCat> findFaqCatAllForUsers() throws SQLException;
}
