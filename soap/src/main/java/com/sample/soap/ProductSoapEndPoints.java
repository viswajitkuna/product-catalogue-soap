package com.sample.soap;

import com.sample.service.dto.ProductDetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Soap end points for product catalogue management
 */
@WebService
public interface ProductSoapEndPoints {
    @WebMethod(operationName = "getAllProducts")
    List<ProductDetails> getAllProducts();

    @WebMethod(operationName = "getProduct")
    ProductDetails getProduct(@WebParam(name = "productId") long id);

    @WebMethod(operationName = "purchaseProduct")
    ProductDetails purchaseProduct(@WebParam(name = "productId") long id, @WebParam(name = "quantity") int quantity);
}
