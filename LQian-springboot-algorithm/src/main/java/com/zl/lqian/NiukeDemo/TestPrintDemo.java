package com.zl.lqian.NiukeDemo;

public class TestPrintDemo {

    public static void main(String[] args)
    {
        int x = 0;
        int y = 0;
        int k = 0;
        for (int z = 0; z < 5; z++) {
            System.out.println(x +" " + y + " " + k );
            if ((++x > 2) && (++y > 2) && (k++ > 2))
            {
                System.out.println(x +" " + y + " " + k );
                x++;
                ++y;
                k++;
                System.out.println(x +" " + y + " " + k );
            }
        }
        System.out.println(x + "" +y + "" +k);
    }
}
