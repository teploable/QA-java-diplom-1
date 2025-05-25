package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class DatabaseTest {
    private final Database database = new Database();

    @Test
    public void availableBunsTest() {
        assertFalse(database.availableBuns().isEmpty());
    }

    @Test
    public void availableIngredientsTest() {
        assertFalse(database.availableIngredients().isEmpty());
    }
}
