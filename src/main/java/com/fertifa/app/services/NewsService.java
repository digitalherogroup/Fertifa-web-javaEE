package com.fertifa.app.services;


import com.fertifa.app.adminSide.news.NewsRequest;
import com.fertifa.app.models.NewsModel;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {

    boolean validateNewsData( String title, String shortDescription, String description, String thumbnailUrl);

    int save(NewsRequest news) throws SQLException;

    int update(NewsModel news, int newsId) throws SQLException;


    List<NewsModel> getById(int newsId) throws SQLException;


    List<NewsModel> getAll() throws SQLException;
    int UpdateImageById(NewsModel newsModel, int id) throws SQLException;
    int DeleteNews(int newsId) throws SQLException;
    int getPerivousId(int newsId);

    int getNextId(int newsId);
}
