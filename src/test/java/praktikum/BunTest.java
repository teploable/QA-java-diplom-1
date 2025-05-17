package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private final Bun bun;

    public BunTest(String name, float price) {
        bun = new Bun(name, price);
    }

    @Parameterized.Parameters(name = "Булочка: {0}; Цена: {1}")
    public static Object[][] getParameters() {
        return new Object[][]{
                {"Флюоресцентная", 988},
                {"Crater", 1255},
                {null, 0},
                {"", 5.5F }
        };
    }

    @Test
    public void getBunNameTest() {
        assertEquals("Метод getName() возвращает некорректное название булочки", bun.name, bun.getName());
    }

    @Test
    public void getBunPriceTest() {
        assertEquals("Метод getPrice() возвращает некорректную цену булочки", bun.price, bun.getPrice(), 0);
    }
}
