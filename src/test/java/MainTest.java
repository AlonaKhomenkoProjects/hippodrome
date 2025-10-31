import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(value = 22)
    @Disabled
    void main() throws Exception {
        Main.main( new String[]{});
    }
}