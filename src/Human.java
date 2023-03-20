import java.io.*;

class Human implements Externalizable {
    private String name;
    private String passportNumber;

    public Human() {
    }

    public Human(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    private int[] numberToArray(String passportNumber) {

        int[] intArr = new int[passportNumber.length()];
        String[] strArr = passportNumber.split("");

        for (int i = 0; i < passportNumber.length(); i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private String encryptPassportNumber(int[] arr) {
        StringBuilder encryptedNumber = new StringBuilder();

        for (int i = 0; i < arr.length - 1; i++) {
            encryptedNumber.append((arr[i] + arr[i + 1]) % 10);
        }
        encryptedNumber.append(arr[arr.length - 1]);
        return encryptedNumber.toString();
    }

    private String decryptPassportNumber(int[] arr) {
        StringBuilder decryptedNumber = new StringBuilder();

        int memory = arr[arr.length - 1];
        decryptedNumber.append(memory);

        for (int i = arr.length - 1; i > 0; i--) {
            memory = arr[i - 1] - memory;
            if (memory < 0) {
                memory += 10;
            }
            decryptedNumber.append(memory);
        }
        decryptedNumber.reverse();
        return decryptedNumber.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        out.writeObject(this.encryptPassportNumber(this.numberToArray(this.getPassportNumber())));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        passportNumber = this.decryptPassportNumber(this.numberToArray((String) in.readObject()));
    }
}