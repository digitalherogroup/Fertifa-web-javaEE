package com.fertifa.app.users;

import com.fertifa.app.baseUrl.BaseUrl;
import com.fertifa.app.constants.EmployerConstance;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.Users;
import com.fertifa.app.products.api.ProductsResponseDto;
import com.fertifa.app.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderTestingController {

    private final ProductsService productsService;
    private Users users = new Users();
    private final UsersController usersController = new UsersController();
    private final BaseUrl baseUrl = new BaseUrl();

    @RequestMapping(value = "/employee/order-testing", method = RequestMethod.GET)
    public ModelAndView showTestingProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        getCookies(request, response);
        List<ProductsResponseDto> productsResponseDto = getTestOrders(productsService.findAll());
        modelAndView.addObject("TestingOrdersList", productsResponseDto);
        modelAndView.addObject("EmployeeObject", users);
        modelAndView.setViewName("/employee/order-testing/OrderTesting.jsp");
        return modelAndView;
    }

    private List<ProductsResponseDto> getTestOrders(List<ProductsResponseDto> all) {
        List<ProductsResponseDto> testOrderList = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCategories().get(0).getCategoryName().contains("TEST ORDER") && all.get(i).getPublished().contains("0")) {
                testOrderList.add(all.get(i));
            }
        }
        return testOrderList;
    }

    private void getCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (baseUrl.employeeCookieManager(request, response)) {
            users = usersController.findById(EmployerConstance.EMPLOYER_ID_INDATA, baseUrl.getEmployeeId(request, response));
        }
    }

    @RequestMapping(value = "/employee/book-appointment", method = RequestMethod.GET)
    public ModelAndView getBookings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        getCookies(request, response);
        List<ProductsResponseDto> productsResponseDto = productsService.findAll();
        List<ProductsResponseDto> serviceList = getServiceList(productsResponseDto);
        List<ProductsResponseDto> treatmentList = getThreatmentList(productsResponseDto);
        modelAndView.addObject("TreatmentList", serviceList);
        modelAndView.addObject("ServiceList",treatmentList);
        modelAndView.addObject("EmployeeObject", users);
        modelAndView.setViewName("/employee/book-appointment/BookAppointment.jsp");
        return modelAndView;
    }

    private List<ProductsResponseDto> getThreatmentList(List<ProductsResponseDto> productsResponseDto) {
        List<ProductsResponseDto> testOrderList = new ArrayList<>();
        for (int i = 0; i < productsResponseDto.size(); i++) {
            if (productsResponseDto.get(i).getCategories().get(0).getCategoryName().contains("CONSULTATION") && productsResponseDto.get(i).getPublished().equals("0")) {
                testOrderList.add(productsResponseDto.get(i));
            }
        }
        return testOrderList;
    }

    private List<ProductsResponseDto> getServiceList(List<ProductsResponseDto> productsResponseDto) {
        List<ProductsResponseDto> testOrderList = new ArrayList<>();
        for (int i = 0; i < productsResponseDto.size(); i++) {
            if (productsResponseDto.get(i).getCategories().get(0).getCategoryName().contains("CLINIC") && productsResponseDto.get(i).getPublished().equals("0")) {
                testOrderList.add(productsResponseDto.get(i));
            }
        }
        return testOrderList;
    }

}
