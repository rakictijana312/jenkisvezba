import org.junit.Test;

public class Main {

    @Test
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("Batch 9 is great");
            Thread.sleep(1000);
        }
    }
}
