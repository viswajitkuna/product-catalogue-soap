import com.sample.service.ProductCatalogueService;
import com.sample.service.dto.ProductDetails;
import com.sample.soap.impl.ProductSoapEndPointsImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductSoapEndPointsTest {

    @InjectMocks
    private ProductSoapEndPointsImpl productSoapEndPoints;

    @Mock
    private ProductCatalogueService productCatalogueService;

    private ProductDetails productDetails;

    private static final int INITIAL_QUANTITY = 30;

    @Before
    public void setUp() {
        List<ProductDetails> productDetailsList = new ArrayList<ProductDetails>();
        productDetails = new ProductDetails(1, "SAMSUNG LED TV", 30);
        productDetailsList.add(productDetails);
        productDetailsList.add(new ProductDetails(2, "SONY LED TV", 30));

        when(productSoapEndPoints.getAllProducts()).thenReturn(productDetailsList);
        when(productSoapEndPoints.getProduct(1)).thenReturn(productDetails);
        when(productSoapEndPoints.purchaseProduct(1, 2)).thenReturn(productDetails);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductDetails> productDetailsList = productSoapEndPoints.getAllProducts();
        assertThat(productDetailsList, notNullValue());
        ProductDetails product = productDetailsList.get(0);
        assertThat(product, notNullValue());
        assertThat(product.getName(), is("SAMSUNG LED TV"));
    }

    @Test
    public void testGetProduct() {
        ProductDetails product = productSoapEndPoints.getProduct(1);
        assertThat(product, notNullValue());
        assertThat(product.getName(), is("SAMSUNG LED TV"));
        assertThat(product.getQuantity(), is(INITIAL_QUANTITY));
    }

    @Test
    public void testPurchaseProduct() {
        ProductDetails product = productSoapEndPoints.purchaseProduct(1, 2);
        assertThat(product, notNullValue());
        assertThat(product.getName(), is("SAMSUNG LED TV"));
        assertThat(product.getQuantity(), is(INITIAL_QUANTITY));
    }
}
