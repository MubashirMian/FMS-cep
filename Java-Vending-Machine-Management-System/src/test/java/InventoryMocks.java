import static org.junit.jupiter.api.Assertions.*;

import org.example.ProductCategory;
import org.example.StockManagement.Inventory;
import org.example.StockManagement.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;

public class InventoryMocks {

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("Chips", 1.5, 10, ProductCategory.SNACK, LocalDate.of(2025, 1, 1));
        inventory.addProduct(product);

        Product retrievedProduct = inventory.getProduct("Chips");
        assertNotNull(retrievedProduct, "Product should be added to the inventory.");
        assertEquals("Chips", retrievedProduct.getName(), "Product name should match.");
        assertEquals(10, retrievedProduct.getQuantity(), "Product quantity should match.");
    }

    @Test
    public void testGetProduct() {
        Product product = new Product("Soda", 1.0, 20, ProductCategory.DRINK, LocalDate.of(2024, 12, 31));
        inventory.addProduct(product);

        Product retrievedProduct = inventory.getProduct("Soda");
        assertNotNull(retrievedProduct, "Product should be retrievable from the inventory.");
        assertEquals("Soda", retrievedProduct.getName(), "Retrieved product name should match.");
    }

    @Test
    public void testGetProductNotFound() {
        Product retrievedProduct = inventory.getProduct("NonExistentProduct");
        assertNull(retrievedProduct, "Retrieving a non-existent product should return null.");
    }

    @Test
    public void testUpdateStock() {
        Product product = new Product("Water", 0.5, 5, ProductCategory.DRINK, LocalDate.of(2025, 3, 15));
        inventory.addProduct(product);

        inventory.updateStock("Water", 15);
        Product updatedProduct = inventory.getProduct("Water");

        assertNotNull(updatedProduct, "Product should still exist in the inventory.");
        assertEquals(15, updatedProduct.getQuantity(), "Product quantity should be updated to 15.");
    }

    @Test
    public void testUpdateStockProductNotFound() {
        inventory.updateStock("NonExistentProduct", 10);
        // Since this test does not change state, there's nothing to assert, but we expect no exception.
    }

    @Test
    public void testRemoveProduct() {
        Product product = new Product("Juice", 2.5, 8, ProductCategory.DRINK, LocalDate.of(2024, 11, 20));
        inventory.addProduct(product);

        inventory.removeProduct("Juice");
        Product removedProduct = inventory.getProduct("Juice");

        assertNull(removedProduct, "Product should be removed from the inventory.");
    }

    @Test
    public void testRemoveProductNotFound() {
        inventory.removeProduct("NonExistentProduct");
        // Since this test does not change state, there's nothing to assert, but we expect no exception.
    }
}
