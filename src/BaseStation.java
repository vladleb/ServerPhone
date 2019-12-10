import java.util.ArrayList;
import java.util.List;

public class BaseStation {

    private int messageLength;
    private int userNumber;
    private int transmissionNumber = 0;
    private List<Phone> phoneList = new ArrayList<>();


    public BaseStation(int userNumber, int messageLength, double P) {

        this.messageLength = messageLength;
        this.userNumber = userNumber;

        for (int i = 0; i < userNumber; i++) {
            phoneList.add(new Phone(P));
        }

    }

    public void StartSendingUnicast() {

        for (int phoneIndex = 0; phoneIndex < phoneList.size(); phoneIndex++) {
            SendMessageUnicast(phoneIndex);
        }

    }

    public void StartSendingBroadcast() {

        ArrayList<Integer> feedback = new ArrayList<>();

        for (int messageIndex = 0; messageIndex < messageLength; messageIndex++, transmissionNumber++) {
            SendMessageToPhone(messageIndex);
        }

        for (Phone phone : phoneList) {
            feedback.addAll(0, phone.feedback());

            if (feedback.size() != 0) {
                while (feedback.size() != 0) {
                    for (Integer integer : feedback) {
                        SendMessageToPhone(integer);
                    }
                    feedback.clear();
                    feedback.addAll(0, phone.feedback());
                }
            }
        }


    }

    private void SendMessageToPhone(int messageIndex) {

        for (int phoneIndex = 0; phoneIndex < phoneList.size() - 1; phoneIndex++, transmissionNumber++) {
            phoneList.get(phoneIndex).getMessage(messageIndex);
        }
    }

    private void SendMessageUnicast(int phoneIndex) {

        ArrayList<Integer> feedback = new ArrayList<>();

        for (int messageIndex = 0; messageIndex < messageLength; messageIndex++, transmissionNumber++) {
            phoneList.get(phoneIndex).getMessage(messageIndex);
        }

        feedback.addAll(0, phoneList.get(phoneIndex).feedback());

        while (feedback.size() != 0){
            for(int messageIndex = 0; messageIndex < feedback.size(); messageIndex++, transmissionNumber++)
            {
                phoneList.get(phoneIndex).getMessage(feedback.get(messageIndex));
            }
            feedback.clear();
            feedback.addAll(0, phoneList.get(phoneIndex).feedback());
        }

    }

    public int getMiddleT() {
        return transmissionNumber / userNumber;
    }

}
