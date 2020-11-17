package com.fertifa.app.products.controller;

import com.fertifa.app.adminSide.CookiesManager.AdminCookiManager;
import com.fertifa.app.constants.AdminConstances;
import com.fertifa.app.controllers.AdminController;
import com.fertifa.app.models.Admins;
import com.fertifa.app.products.api.ProductsRequestDto;
import com.fertifa.app.products.api.ProductsResponseDto;
import com.fertifa.app.products.service.CategoryService;
import com.fertifa.app.products.service.ProductsService;
import com.fertifa.app.storage.controller.FileService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products/")
@Log4j2

public class ProductsController {
    private final ProductsService productsService;
    private final FileService filesController;
    private final CategoryService categoryService;
    private final AdminController adminController = new AdminController();
    private Admins admins = new Admins();

    @GetMapping("/add")
    public ModelAndView addProducts(HttpServletRequest request, HttpServletResponse response) {
        getCokkies(request, response);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("AdminsObject", admins);
        modelAndView.setViewName("/admin/products/createProducts.jsp");
        return modelAndView;
    }

    private void getCokkies(HttpServletRequest request, HttpServletResponse response) {
        AdminCookiManager.UserCookie userCookie = AdminCookiManager.getCookiData(request, response);
        admins = adminController.getAdmin(AdminConstances.ADMIN_ID_INDATA, String.valueOf(userCookie.getUserId()));
    }

    @PostMapping("/create")
    @CrossOrigin
    public ModelAndView createProduct(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute("products") ProductsRequestDto productsRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (productsRequestDto.getDescription().length() > 1200 ||
                productsRequestDto.getShortDescription().length() > 151 ||
                productsRequestDto.getTitle().length() > 151 ||
                productsRequestDto.getCategories().getCategoryName().isEmpty()) {
            modelAndView.setViewName("redirect:/admin/products/add");
            return modelAndView;
        }

        String fileName = "";
        if (null == file.getOriginalFilename() || file.getOriginalFilename().isEmpty()) {
            productsRequestDto.setImageLink("/img/no-image.png");
        } else {
            ResponseEntity<?> imageUploadResponse = filesController.uploadFile(file);
            if (null != imageUploadResponse) {
                log.info(imageUploadResponse);
                String imageLinkString = new Gson().toJson(imageUploadResponse.getBody().toString(), String.class);
                String withoutFirstCharacter = imageLinkString.substring(1);
                String filteringString = withoutFirstCharacter.replace("\"", "");
                productsRequestDto.setImageLink(filteringString);
            }
        }
        productsService.save(productsRequestDto);
        modelAndView.setViewName("redirect:/admin/products/all");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateProduct(@RequestParam("file") MultipartFile file,
                                      @ModelAttribute("product") ProductsRequestDto productsRequestDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (productsRequestDto.getDescription().length() > 1200 ||
                productsRequestDto.getShortDescription().length() > 151 ||
                productsRequestDto.getTitle().length() > 151 ||
                productsRequestDto.getCategories().getCategoryName().isEmpty()) {
            modelAndView.setViewName("redirect:/admin/products/add");
            return modelAndView;
        }
        ResponseEntity<?> imageUploadResponse = null;
        if (null == file.getOriginalFilename() || file.getOriginalFilename().isEmpty()) {
            productsRequestDto.setImageLink(productsRequestDto.getImageLink());

        } else {
            imageUploadResponse = filesController.uploadFile(file);
            if (null != imageUploadResponse) {
                String imageLinkString = new Gson().toJson(imageUploadResponse.getBody().toString(), String.class);
                String withoutFirstCharacter = imageLinkString.substring(1);
                String filteringString = withoutFirstCharacter.replace("\"", "");
                String withoutLastCharacter = filteringString.replace(" ", "-");
                productsRequestDto.setImageLink(withoutLastCharacter);
            }
        }
        productsService.updateProduct(productsRequestDto);
        modelAndView.setViewName("redirect:/admin/products/all");
        return modelAndView;
    }

    @CrossOrigin
    @GetMapping("/all")
    public ModelAndView allProducts(HttpServletRequest request, HttpServletResponse response) {
        getCokkies(request, response);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", productsService.findAll());
        modelAndView.addObject("AdminsObject", admins);
        modelAndView.setViewName("/admin/products/products.jsp");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        getCokkies(request, response);
        modelAndView.addObject("productObject", productsService.getOne(id));
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("AdminsObject", admins);
        modelAndView.setViewName("/admin/products/editProducts.jsp");
        return modelAndView;
    }


    @PostMapping("/delete")
    public ModelAndView deleteProduct(@ModelAttribute("product") ProductsResponseDto productsResponseDto) {
        ModelAndView modelAndView = new ModelAndView();
        productsService.deleteById(productsResponseDto.getId());
        modelAndView.setViewName("redirect:/admin/products/all");
        return modelAndView;
    }
}
