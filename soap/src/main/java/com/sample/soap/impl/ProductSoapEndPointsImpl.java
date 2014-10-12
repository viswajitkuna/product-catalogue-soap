package com.sample.soap.impl;

import com.sample.service.ProductCatalogueService;
import com.sample.service.dto.ProductDetails;
import com.sample.soap.ProductSoapEndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.sample.soap.ProductSoapEndPoints", serviceName = "productSoap")
public class ProductSoapEndPointsImpl implements ProductSoapEndPoints {

    private ProductCatalogueService productCatalogueService;

    public void setProductCatalogueService(ProductCatalogueService productCatalogueService) {
        this.productCatalogueService = productCatalogueService;
    }

    @Override
    public List<ProductDetails> getAllProducts() {
        return productCatalogueService.getAllProducts();
    }

    @Override
    public ProductDetails getProduct(long id) {
        return productCatalogueService.getProduct(id);
    }

    @Override
    public ProductDetails purchaseProduct(long id, int quantity) {
        return productCatalogueService.purchaseProduct(id, quantity);
    }
}
