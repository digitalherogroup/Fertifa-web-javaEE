package com.fertifa.app.controllers;


import com.fertifa.app.adminSide.news.NewsRequest;
import com.fertifa.app.adminSide.news.NewsResponse;
import com.fertifa.app.dao.NewsDao;
import com.fertifa.app.models.NewsModel;
import com.fertifa.app.services.NewsService;

import java.sql.SQLException;
import java.util.List;

public class NewsController implements NewsService {
    private NewsDao newsDao = new NewsDao();

    public boolean validateNewsData(String title, String shortDescription, String description, String thumbnailUrl) {
        return newsDao.validateNewsData(title, shortDescription, description, thumbnailUrl);
    }

    public int save(NewsRequest newsRequest) throws SQLException {
        return newsDao.save(newsRequest);
    }

    public int update(NewsModel newsModel, int newsId) throws SQLException {
        return newsDao.update(newsModel, newsId);
    }

    public List<NewsModel> getById(int id) throws SQLException {
        return newsDao.getById(id);
    }

    public List<NewsModel> getAll() throws SQLException {
        return newsDao.getAll();
    }

    public int UpdateImageById(NewsModel newsModel, int id) throws SQLException {
        return newsDao.UpdateImage(newsModel,id);
    }

    public int DeleteNews(int newsId) throws SQLException {
        return newsDao.deleteNews(newsId);
    }

    @Override
    public int getPerivousId(int newsId) {
        return newsDao.getPerivousId(newsId);
    }

    @Override
    public int getNextId(int newsId) {
        return newsDao.getNextId(newsId);
    }

    public boolean checkboxDuplicated(String title) throws SQLException {
        return newsDao.checkboxDuplicated(title);
    }

    public NewsResponse getNewsObjectById(int newsId) throws SQLException {
        return newsDao.getNewsObjectById(newsId);
    }

    public int updateObject(NewsRequest newsRequest) throws SQLException {
        return newsDao.updateObject(newsRequest);
    }

    public List<NewsModel> getAllActives() throws SQLException {
        return newsDao.getAllActives();
    }

    public void updateCategoryOneByNewsId(String newsCategory, int id) throws SQLException {
        newsDao.updateCategoryOneByNewsId(newsCategory,id);
    }

    public void updateCategoryTwoByNewsId(String newsCategory, int id) throws SQLException {
        newsDao.updateCategoryTwoByNewsId(newsCategory,id);
    }

    public List<NewsModel> getAllNewsWithCategoryOne(String newsObject) throws SQLException {
        return newsDao.getAllNewsWithCategoryOne(newsObject);
    }

    public List<NewsModel> getAllNewsWithCategoryTwo(String newsObject) throws SQLException {
        return newsDao.getAllNewsWithCategoryTwo(newsObject);
    }

    public List<NewsModel> getNextPreviews(int newsId) throws SQLException {
        return newsDao.getNextPreviews(newsId);
    }

    public List<NewsModel> getAllNewsWithCategorysOne(String newsCategory) throws SQLException {
        return newsDao.getAllNewsWithCategoryOne(newsCategory);
    }

    public List<NewsModel> getAllNewsWithCategorysTwo(String newsCategory) throws SQLException {
        return newsDao.getAllNewsWithCategoryTwo(newsCategory);
    }

    public int updateCategoriesOne(String newsCategory, int id) throws SQLException {
        return newsDao.updateCategoriesOne(newsCategory,id);
    }

    public int updateCategoriesTwo(String newsCategory, int id) throws SQLException {
        return newsDao.updateCategoriesTwo(newsCategory,id);
    }

    public List<NewsModel> getAllPagination(int start) throws SQLException {
        return newsDao.getAllPagination(start);
    }

    public int countNews() throws SQLException {
        return newsDao.countNews();
    }
}
