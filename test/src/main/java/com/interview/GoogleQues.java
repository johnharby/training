package com.interview;

public class GoogleQues {
    int x;
    // x = 124791011888990
    // should output 1-2, 4, 7, 9-11, 88-89, 90

    public GoogleQues(int x) {
        this.x = x;
    }

    public String convert() {
        String s = "";
        String t = Integer.toString(x);
        for (int i = 1; i < 100 && t.length() > 0; i++) {
            if (t.contains(i + "")) {
                s = s.concat(i + "");
                t = t.substring(1);

                if (t.contains((i + 1) + "")) {
                    s = s.concat("-");
                    t = t.substring(1);
                    int j = 2;
                    while (t.contains((i + j) + "")) {
                        j++;
                        t = t.substring(1);
                    }
                    s = s.concat((i + j - 1) + "");
                } else {
                    s = s.concat(",");
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        int x = 1235;
        GoogleQues q = new GoogleQues(x);
        System.out.println(q.convert());
    }
}
