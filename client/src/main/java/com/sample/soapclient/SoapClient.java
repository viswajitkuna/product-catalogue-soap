package com.sample.soapclient;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;

import com.sample.soap.*;

/**
 * Invokes the defined soap end points
 */
public class SoapClient {


    /**
     * Starting point of execution. Invoked by JVM
     *
     * @param args
     * @throws RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        System.out.println("Get a Product....!!!");
        invokeGetProduct();

        System.out.println("Purchase a Product....!!!");
        invokePurchaseProduct();

        System.out.println("Get all Products....!!!");
        invokeGetAllProducts();
    }

    /**
     * Invokes the SOAP end point defined to retrieve the list of available products
     *
     * @throws RemoteException
     */
    private static void invokeGetAllProducts() throws RemoteException {
        ProductSoapStub productSoapStub = new ProductSoapStub();

        GetAllProducts getAllProducts = GetAllProducts.Factory.newInstance();

        GetAllProductsDocument getAllProductsDocument = GetAllProductsDocument.Factory.newInstance();
        getAllProductsDocument.setGetAllProducts(getAllProducts);

        GetAllProductsResponseDocument productList = productSoapStub.getAllProducts(getAllProductsDocument);
        ProductDetails[] productDetails = productList.getGetAllProductsResponse().getReturnArray();
        for(int i = 0; i < productDetails.length; i++) {
            System.out.println(productDetails[i]);
        }
    }

    /**
     * Invokes the SOAP end point defined to purchase a product and to update its available quantity
     *
     * @throws RemoteException
     */
    private static void invokePurchaseProduct() throws RemoteException {
        ProductSoapStub productSoapStub = new ProductSoapStub();

        PurchaseProduct purchaseProduct = PurchaseProduct.Factory.newInstance();
        purchaseProduct.setProductId(1);
        purchaseProduct.setQuantity(1);

        PurchaseProductDocument purchaseProductDocument = PurchaseProductDocument.Factory.newInstance();
        purchaseProductDocument.setPurchaseProduct(purchaseProduct);

        PurchaseProductResponseDocument product = productSoapStub.purchaseProduct(purchaseProductDocument);
        System.out.println(product.getPurchaseProductResponse().getReturn());
    }

    /**
     * Invokes the SOAP end point to retrieve the details of a selected product
     *
     * @throws RemoteException
     */
    private static void invokeGetProduct() throws RemoteException {
        ProductSoapStub productSoapStub = new ProductSoapStub();

        GetProduct getProduct = GetProduct.Factory.newInstance();
        getProduct.setProductId(2);

        GetProductDocument getProductDocument = GetProductDocument.Factory.newInstance();
        getProductDocument.setGetProduct(getProduct);

        GetProductResponseDocument product = productSoapStub.getProduct(getProductDocument);
        System.out.println(product.getGetProductResponse().getReturn());
    }
}
