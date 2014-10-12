package com.sample.soap;

import com.sample.service.dto.ProductDetails;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductSoapEndPoints {
    @WebMethod(operationName = "getAllProducts")
    List<ProductDetails> getAllProducts();

    @WebMethod(operationName = "getProduct")
    ProductDetails getProduct(long id);

    @WebMethod(operationName = "purchaseProduct")
    ProductDetails purchaseProduct(long id, int quantity);
}
