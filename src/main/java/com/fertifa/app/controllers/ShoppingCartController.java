package com.fertifa.app.controllers;

import com.fertifa.app.dao.EnrollmentModel;
import com.fertifa.app.dao.ShoppingCartDao;
import com.fertifa.app.models.ShoppingCardSalesModel;
import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.ShoppingCartFinal;
import com.fertifa.app.payments.User;
import com.fertifa.app.services.ShoopingCartService;

import java.sql.SQLException;
import java.util.List;

public class ShoppingCartController implements ShoopingCartService {
    ShoppingCartDao shoppingCartDao = new ShoppingCartDao();

    @Override
    public int addShoppingCart(ShoppingCart shoppingCart) throws SQLException {
        return shoppingCartDao.AddNewOrder(shoppingCart);
    }
    public int AddNewOrderToChat(ShoppingCart shoppingCart) throws SQLException {
        return shoppingCartDao.AddNewOrderToChat(shoppingCart);
    }
    @Override
    public List<ShoppingCart> getAllById(int userId) throws SQLException {
        return shoppingCartDao.getAllById(userId);
    }


    public List<ShoppingCart> getAllOrdersById(int userId) throws SQLException {
        return shoppingCartDao.getAllOrdersById(userId);
    }

    @Override
    public int deleteOrderByUseridAndOrderId(int Orderid, int userIdFromWeb) throws SQLException {
        return shoppingCartDao.deleteOrderByUseridAndOrderId(Orderid,userIdFromWeb);
    }

    public ShoppingCart getObjectById(int fertifaUser_id) throws SQLException {
        return shoppingCartDao.getObjectById(fertifaUser_id);

    }

    public int AddToFinal(int usersCompanyId,int serviceId, int userid, User user, String orderId, ShoppingCart shoppingCart, int total, String stripeId) throws SQLException {
        return shoppingCartDao.AddToFinal(usersCompanyId,serviceId,userid,user,orderId,shoppingCart,total,stripeId);
    }

    public int deleteOrderByOrderId(int orderIdFroShop, int userId) throws SQLException {
        return shoppingCartDao.deleteOrderByOrderId(orderIdFroShop,userId);
    }

    public List<ShoppingCartFinal> GetAllByDates(String sevenDays, String today, int ServiceId, int CompanyId) throws SQLException {
        return shoppingCartDao.GetAllByDates(sevenDays,today,ServiceId,CompanyId);
    }
    public List<ShoppingCardSalesModel> getSalesShoppingCard(String sevenDays, String today, int ServiceId, int CompanyId) throws SQLException {
        return shoppingCartDao.getSalesShoppingCard(sevenDays,today,ServiceId,CompanyId);
    }


    public ShoppingCart getObjectByUserId(int fertifaUser_id) throws SQLException {
        return shoppingCartDao.getObjectByUserId(fertifaUser_id);
    }

    public List<ShoppingCartFinal> getListObjectByUserId(int userId) throws SQLException {
        return shoppingCartDao.getListObjectByUserId(userId);
    }

    public String  getTotalSavingsByMonths(String lastMonth, String now, double percentage, int companyId, int serviceId) throws SQLException {
        return shoppingCartDao.getTotalSavingsByMonths(lastMonth,now,percentage,companyId,serviceId);
    }

    public List<EnrollmentModel> getAllSavingDetails(String lastMonth, String now, int companyId, double percentageForSaving, int serviceId) throws SQLException {
        return shoppingCartDao.getAllSavingDetails(lastMonth,now,percentageForSaving,companyId,serviceId);
    }


    public int getLastId() {
        return shoppingCartDao.getlatestID();
    }

    public int getLatestShopID() {
        return shoppingCartDao.getLatestShopID();
    }

    public int UpdateStatusById(int orderIdFroShop, int userId) throws SQLException {
         return shoppingCartDao.UpdateStatusById(orderIdFroShop,userId);
    }

    public boolean getAllByOrderId(int dataId) throws SQLException {
        return shoppingCartDao.getAllByOrderId(dataId);
    }

    public int updateShoppingCartByOrderId(ShoppingCart shoppingCart, int dataId) throws SQLException {
        return shoppingCartDao.updateShoppingCartByOrderId(shoppingCart,dataId);
    }

    public int updateChatIdByShopId(int chatId, int shoppingCardId) throws SQLException {
        return shoppingCartDao.updateChatIdByShopId(chatId,shoppingCardId);
    }

    public int updateShoppingCardWithPayment(ShoppingCart shoppingCartModel, int userId) throws SQLException {
        return shoppingCartDao.updateShoppingCardWithPayment(shoppingCartModel,userId);
    }
}
