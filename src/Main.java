public class Main {

    public static void main(String[] args){

        int userNumber = 10;
        int messageLength = 100000;
        double P = 0.01;

        System.out.println("UserNumber: " + userNumber);
        System.out.println("MessageLength: " + messageLength);
        System.out.println("P: " + P);

        BaseStation BS1 = new BaseStation(userNumber, messageLength, P);
        BS1.StartSendingUnicast();
        System.out.println("Unicast T middle: " + BS1.getMiddleT());


        BaseStation BS2 = new BaseStation(userNumber, messageLength, P);
        BS2.StartSendingBroadcast();
        System.out.println("Broadcast T middle: " + BS2.getMiddleT());

    }
}
