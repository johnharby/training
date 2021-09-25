package com.learn;

class Interchange {

    static void interchange(int a[][],int r, int c){
        for (int i = 0; i < r; ++i) {
            int[][] tmp = new int[r][c];
            for (int j = 0; j < c; ++j) {
                if (j == 0 || j == c-1) {
                    if (j == 1) {
                        tmp[i][j] = a[i][c-1];
                        a[i][c-1] = a[i][j];
                    }
                    else {
                        a[i][0] = a[i][j];
                    }
                }
            }
        }

        for(int i = 0;i<r;i++){
            for(int j = 0;j<c;j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {3, 4}, {5, 6}};
        interchange(arr, 0, 2);
    }
}
