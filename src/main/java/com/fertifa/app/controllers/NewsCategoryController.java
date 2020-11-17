package com.fertifa.app.controllers;


import com.fertifa.app.dao.NewsCategoryDao;
import com.fertifa.app.models.NewsCategory;

import java.sql.SQLException;
import java.util.List;

public class NewsCategoryController {

    private NewsCategoryDao newsCategoryDao = new NewsCategoryDao();

    public int save(NewsCategory newCategoryForNews) throws SQLException {
        return newsCategoryDao.save(newCategoryForNews);
    }

    public List<NewsCategory> getByCategoryName(String name) throws SQLException {
        return newsCategoryDao.getByCategoryName(name);
    }

    public List<NewsCategory> showAll() throws SQLException {
        return newsCategoryDao.showAll();
    }

    public NewsCategory getByCategoryId(int newsCategoryId) throws SQLException {
        return newsCategoryDao.getByCategoryId(newsCategoryId);
    }

    public int update(NewsCategory newsObject) throws SQLException {
        return newsCategoryDao.update(newsObject);
    }

    public int DeleteCategoryNews(int newsCategoryId) throws SQLException {
        return newsCategoryDao.deleteById(newsCategoryId);
    }

    public String getCategoryNameById(int newsCategoryId) throws SQLException {
        return newsCategoryDao.getCategoryNameById(newsCategoryId);
    }

    public int getLastID() throws SQLException {
        return newsCategoryDao.getLastID();
    }
}
