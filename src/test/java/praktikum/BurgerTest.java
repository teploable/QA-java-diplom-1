package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.BurgerConstants.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;

    @Mock
    private Bun bunMock;
    @Mock
    private Ingredient sauceMock;
    @Mock
    private Ingredient fillingMock;


    @Before
    public void createBurger() {
        burger = new Burger();
    }

    @Test
    public void setBunTest() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(sauceMock);
        burger.addIngredient(sauceMock);
        assertEquals(expectedIngredients.size(), burger.ingredients.size());
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(fillingMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        List<Ingredient> expectedOrder = new ArrayList<>(2);
        expectedOrder.add(sauceMock);
        expectedOrder.add(fillingMock);
        burger.addIngredient(fillingMock);
        burger.addIngredient(sauceMock);
        burger.moveIngredient(0, 1);
        assertEquals(expectedOrder, burger.ingredients);
    }

    @Test
    public void getPriceBurgerTest() {
        Mockito.when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(sauceMock.getPrice()).thenReturn(SAUCE_PRICE);
        Mockito.when(fillingMock.getPrice()).thenReturn(FILLING_PRICE);

        burger.setBuns(bunMock);
        burger.addIngredient(fillingMock);
        burger.addIngredient(sauceMock);
        assertEquals(BURGER_PRICE_CALCULATION, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        Mockito.when(bunMock.getName()).thenReturn(BUN_NAME);
        Mockito.when(fillingMock.getName()).thenReturn(FILLING_NAME);
        Mockito.when(sauceMock.getName()).thenReturn(SAUCE_NAME);
        Mockito.when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(fillingMock.getPrice()).thenReturn(FILLING_PRICE);
        Mockito.when(sauceMock.getPrice()).thenReturn(SAUCE_PRICE);
        Mockito.when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bunMock);
        burger.addIngredient(fillingMock);
        burger.addIngredient(sauceMock);

        String burgerPrice = String.format("%f%n", BURGER_PRICE_CALCULATION);
        String expectedReceipt = String.format("(==== %s ====)\n= %s %s =\n= %s %s =\n" + "(==== %s ====)\n\nPrice: %s",
                BUN_NAME,
                IngredientType.FILLING.toString().toLowerCase(), FILLING_NAME,
                IngredientType.SAUCE.toString().toLowerCase(), SAUCE_NAME,
                BUN_NAME,
                burgerPrice);
        assertEquals("Полученный чек не совпадает с ожидаемым", expectedReceipt, burger.getReceipt());
        String newLine = System.lineSeparator();
        System.out.println("Вывод чека:" + newLine + burger.getReceipt());
    }
}

