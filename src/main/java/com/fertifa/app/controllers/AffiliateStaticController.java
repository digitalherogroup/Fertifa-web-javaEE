package com.fertifa.app.controllers;

import com.fertifa.app.dao.AffiliateStaticDao;
import com.fertifa.app.affiliate.builder.AffiliateStatics;

import java.sql.SQLException;

public class AffiliateStaticController {


    private AffiliateStaticDao affiliateStaticDao = new AffiliateStaticDao();

    public int saveStatic(AffiliateStatics affiliatStatic) throws SQLException {
        return affiliateStaticDao.saveStatic(affiliatStatic);
    }

    public boolean checkingClicksByIpInAffiliateStatic(String affiliateStatics, String ip, String additionalWhere, int i) throws SQLException {
        return affiliateStaticDao.checkingClicksByIpInAffiliateStatic(affiliateStatics,ip,additionalWhere,i);
    }

    public int CountClicksByAffiliateId(String toCount,int affiliateId) throws SQLException {
        return affiliateStaticDao.CountClicksByAffiliateId(toCount,affiliateId);
    }

    public int CountLostClicks(String toCount, int affiliateId) throws SQLException {
        return affiliateStaticDao.CountLostClicks(toCount,affiliateId);
    }
}
