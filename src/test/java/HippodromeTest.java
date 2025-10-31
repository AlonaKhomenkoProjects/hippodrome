import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
   public void getHorsesConstructorShouldThrowExceptionIfNameIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows( IllegalArgumentException.class,
                ()-> new Hippodrome(null));
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }

    @Test
    void constructorShouldThrowExceptionIfBeEmpty () {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(Collections.EMPTY_LIST));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses_ReturnWithAllHorses (){
        String name = "TestName";

        ArrayList<Horse> horseArrayList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horseArrayList.add(new Horse(name + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horseArrayList);
        List<Horse> returnHorses = hippodrome.getHorses();

        assertEquals(horseArrayList, returnHorses);
        assertEquals(horseArrayList.get(0).getName(), returnHorses.get(0).getName());
        assertEquals(horseArrayList.get(25).getName(), returnHorses.get(25).getName());
        assertEquals(30, returnHorses.size());
        assertEquals("TestName1", returnHorses.get(0).getName());
        assertEquals("TestName26", returnHorses.get(25).getName());
    }

    @Test
    void move_CallsMoveToAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_returnCorrectWinner() {

        Hippodrome hippodrome = new Hippodrome(List.of(

                new Horse("horse1", 1,2),
                new Horse("horse2", 1,20),
                new Horse("horse3", 1, 30))
        );

        assertEquals("horse3", hippodrome.getWinner().getName());

    }
}