package 剑指offer.no41_50;

/*
    思路：做加法运算时，分三步走：
    1、不考虑进位的情况，将两个数的各位进行相加，此时0和0，1和1得到0，1和0，0和1得到1，这个结果和异或结果一致；
    2、考虑进位情况，得到进位的值。1和1产生进位，得到10，其余的都不产生进位，这个结果可以看作两个数位与运算，
    再左移一位；
    3、将1和2得到的值相加，得到最终的值。注意：相加时也要考虑进位的情况，一直到没有进位为止
 */
public class Add_48 {
    public int Add(int num1,int num2) {
        while (num2 !=0){
            int sum = num1 ^ num2;
            int temp = (num1 & num2) <<1;

            num1 = sum;
            num2 = temp;
        }

        return num1;
    }

    public static void main(String[] args) {
        Add_48 add = new Add_48();
        System.out.println(add.Add(0,3));
    }
}
