package com.fertifa.app.services;

import com.fertifa.app.models.FaqsCatFaqs;

import java.sql.SQLException;
import java.util.List;

public interface FaqCatFaqsControllerService {
    List<FaqsCatFaqs> getAllFasqAndCategories() throws SQLException;
}
