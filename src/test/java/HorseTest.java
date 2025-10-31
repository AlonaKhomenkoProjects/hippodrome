import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void constructorShouldThrowExceptionIfNameIsNull() {
        IllegalArgumentException exception = assertThrows (
                IllegalArgumentException.class, () -> new Horse (null,  10,5)
        );
        assertEquals ("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    @DisplayName("Конструктор: порожнє або пробільне name → " +
            "IllegalArgumentException з повідомленням 'Name cannot be blank.'")
    public void constructorShouldThrowExceptionIfNameIsBlank (String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10, 5)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionIfSpeedIsNegative() {
    IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, () -> new Horse("TestName", -1, 5)
    );
    assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionIfDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, ()-> new Horse("TestName", 1, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String name = "TestName";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(name, speed,distance);

        String actualName = horse.getName();
        assertEquals(name, actualName);
    }

    @Test
    void getSpeed() {
        String name = "TestName";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(name, speed,distance);

        double actualSpeed = horse.getSpeed();
        assertEquals(speed, actualSpeed);
    }

    @Test
    void getDistanceThirdArg() {
        String name = "TestName";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(name, speed,distance);

        double actualDistance = horse.getDistance();
        assertEquals(distance, actualDistance);
    }

    @Test
    void getDistanceShouldBeZeroIfCreatedWithTwoArgs() {
        Horse horse = new Horse("TestName", 1);
        assertEquals( 0, horse.getDistance());
    }

    @Test
    void moveWithTwoArg() {
        try (
            MockedStatic<Horse> mock = mockStatic(Horse.class)) {
    Horse horse = new Horse("TestName", 1,2);

    horse.move();

    mock.verify(()->Horse.getRandomDouble(0.2, 0.9));
            }
        }
        @ParameterizedTest
        @ValueSource(doubles = {0.2, 0.5, 0.9})
        void moveShouldCalculateNewDistance(double randomValue) {
        String name = "TestName";
        double min = 0.2;
        double max = 0.9;
        double speed = 2.5;
        double distance = 250;
        Horse horse = new Horse(name, speed,distance);
            double expectedDistance = distance + speed * randomValue;

            try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
                horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(randomValue);

                horse.move();
            }
                assertEquals(expectedDistance, horse.getDistance());

        }
}