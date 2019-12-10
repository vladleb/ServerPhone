import java.util.ArrayList;
import java.util.Random;

public class Phone {

    private double P;
    private ArrayList<Integer> messageErrorIndex = new ArrayList<>();
    private ArrayList<Integer> messageIndex = new ArrayList<>();

    public Phone(double P) {
        this.P = P;
    }

    public void getMessage(int index) {

        Random r = new Random();
        double randomValue = 0.0 + (1.0 - 0.0) * r.nextDouble();

        if (randomValue <= P && !messageErrorIndex.contains(index))
            messageErrorIndex.add(index);
        else if(messageErrorIndex.contains(index) && randomValue > P) {
            messageErrorIndex.remove((Integer) index);
            messageIndex.add(index);
        }
        else if (randomValue> P)
            messageIndex.add(index);


    }

    public ArrayList<Integer> feedback() {
        return messageErrorIndex;
    }
}
