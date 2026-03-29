import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class skusobne {


    private  Scanner skener;

    public skusobne() {

        try {
            this.skener = new Scanner(new File("src/TEST_mini.hrn"));
        } catch (FileNotFoundException e) {
            System.out.println("Súbor sa nenašiel");
        }

        while(this.skener.hasNextInt()){

            System.out.println(this.skener.nextInt());

        }

    }
}
