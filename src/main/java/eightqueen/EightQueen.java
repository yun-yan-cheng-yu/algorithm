package eightqueen;

/**
 * 八皇后问题
 */
public class EightQueen {
    private int[] queens ;//0-7的数字表示他们的位置

    private void show(){
        for (int queen : queens) {
            System.out.print(queen);
        }
        System.out.println();
    }

    public EightQueen(){
        queens = new int[8];
    }

    public void solve(){
        final int hp = -1;
        final int tp = 8;
        int solveNum = 0;
        int p = 0;
        while (p != hp){
            if( p == tp){
                solveNum++;
                System.out.println("解"+solveNum+":");
                show();
                p--;
            }
            if(tryGiveNum(p)){
                p++;
            }else{
                queens[p] = 0;
                p--;
            }
        }
    }

    private boolean isValid(int index){
        for (int i = 0; i < index; i++) {
            if(queens[index] == queens[i]){
                return false;
            }
            if(Math.abs(queens[index] - queens[i]) == index - i){
                return false;
            }
        }
        return true;
    }

    private boolean tryGiveNum(int index){
        do {
            queens[index]++;
            if(queens[index] >= 9){
                return false;
            }
        }while (!isValid(index));
        return true;
    }
}
