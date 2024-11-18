import static org.junit.jupiter.api.Assertions.*;

import org.example.EndUsers.User;
import org.example.Exceptions.InsufficientBalanceException;
import org.example.Exceptions.ProductExpiredException;
import org.example.ProductCategory;
import org.example.StockManagement.Product;
import org.example.StockManagement.VendingMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class VendingMachineMock {

    private VendingMachine vendingMachine;
    private User user;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
        user = new User("TestUser", 50.0);  // User with an initial balance of $50.0
    }

    @Test
    public void testAddProductToInventory() {
        Product product = new Product("Chips", 1.5, 10, ProductCategory.SNACK, LocalDate.of(2025, 1, 1));
        vendingMachine.addProductToInventory(product);
        assertNotNull(vendingMachine.inventory.getProduct("Chips"), "Product should be added to inventory.");
    }

    @Test
    public void testUpdateStock() {
        Product product = new Product("Chocolate", 2.0, 5, ProductCategory.SNACK, LocalDate.of(2025, 6, 1));
        vendingMachine.addProductToInventory(product);
        vendingMachine.updateStock("Chocolate", 15);
        assertEquals(15, vendingMachine.inventory.getProduct("Chocolate").getQuantity(), "Stock should be updated to 15.");
    }

    @Test
    public void testRemoveProductFromInventory() {
        Product product = new Product("Soda", 1.0, 20, ProductCategory.DRINK, LocalDate.of(2024, 12, 31));
        vendingMachine.addProductToInventory(product);
        vendingMachine.removeProductFromInventory("Soda");
        assertNull(vendingMachine.inventory.getProduct("Soda"), "Product should be removed from inventory.");
    }

    @Test
    public void testDisplayInventory() {
        Product product1 = new Product("Water", 0.5, 50, ProductCategory.DRINK, LocalDate.of(2025, 3, 15));
        Product product2 = new Product("Cookies", 3.0, 8, ProductCategory.SNACK, LocalDate.of(2024, 11, 30));
        vendingMachine.addProductToInventory(product1);
        vendingMachine.addProductToInventory(product2);

        assertDoesNotThrow(() -> vendingMachine.displayInventory(), "Display inventory should not throw exceptions.");
    }

    @Test
    public void testPurchaseProductSuccess() {
        Product product = new Product("Juice", 2.5, 10, ProductCategory.DRINK, LocalDate.of(2024, 11, 20));
        vendingMachine.addProductToInventory(product);

        vendingMachine.purchaseProduct(user, "Juice", 2);

        assertEquals(45.0, user.balance, "User balance should decrease by total cost of purchase.");
        assertEquals(8, product.getQuantity(), "Product quantity should decrease by the amount purchased.");
        assertEquals(5.0, vendingMachine.getEarnings(), "Vending machine earnings should increase by total cost.");
    }

    @Test
    public void testGetEarnings() {
        Product product = new Product("Candy", 0.75, 30, ProductCategory.SNACK, LocalDate.of(2025, 5, 10));
        vendingMachine.addProductToInventory(product);

        vendingMachine.purchaseProduct(user, "Candy", 4);
        assertEquals(3.0, vendingMachine.getEarnings(), "Earnings should reflect the total cost of all purchases.");
    }
}
