package otherJavaCodeTests;

public class ArrayOfObjects {
    public static void main(String args[]) {

        Account []testArray = new Account[3];
        testArray[0] = new Account();
        testArray[1] = new Account();
        testArray[2] = new Account();

        testArray[0].setData(1, 2);
        testArray[1].setData(3, 4);
        testArray[2].setData(5, 6);

        for (int i = 0; i < testArray.length; i++) {
            testArray[i].printData();
        }
    }
}

class Account{
    int firstData;
    int secondData;

    public void setData(int firstData, int secondData){
        this.firstData = firstData;
        this.secondData = secondData;
    }

    public void printData(){
        System.out.println(this.firstData);
        System.out.println(this.secondData);
    }
}