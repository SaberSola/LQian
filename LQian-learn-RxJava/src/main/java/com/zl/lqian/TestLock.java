package com.zl.lqian;

import  java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public  class  TestLock {

    private Map map =  new LinkedHashMap(2);

    public  TestLock() {
        try {
            Thread t1 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.put(new Integer(i), i);
                    }
                    System.out.println("t1 over");
                }
            };

            Thread t2 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.put(new Integer(i), i);
                    }

                    System.out.println("t2 over");
                }
            };

            Thread t3 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.put(new Integer(i), i);
                    }

                    System.out.println("t3 over");
                }
            };

            Thread t4 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.put(new Integer(i), i);
                    }

                    System.out.println("t4 over");
                }
            };

            Thread t5 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.put(new Integer(i), i);
                    }

                    System.out.println("t5 over");
                }
            };

            Thread t6 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.get(new Integer(i));
                    }

                    System.out.println("t6 over");
                }
            };

            Thread t7 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.get(new Integer(i));
                    }

                    System.out.println("t7 over");
                }
            };

            Thread t8 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.get(new Integer(i));
                    }

                    System.out.println("t8 over");
                }
            };

            Thread t9 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.get(new Integer(i));
                    }

                    System.out.println("t9 over");
                }
            };

            Thread t10 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.get(new Integer(i));
                    }

                    System.out.println("t10 over");
                }
            };

            Thread t11 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.remove(map.keySet().iterator().next());
                    }

                    System.out.println("t5 over");
                }
            };

            Thread t12 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.remove(map.keySet().iterator().next());
                    }

                    System.out.println("t6 over");
                }
            };

            Thread t13 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.remove(map.keySet().iterator().next());
                    }

                    System.out.println("t7 over");
                }
            };

            Thread t14 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.remove(map.keySet().iterator().next());
                    }

                    System.out.println("t8 over");
                }
            };

            Thread t15 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.remove(map.keySet().iterator().next());
                    }

                    System.out.println("t9 over");
                }
            };

            Thread t16 = new Thread() {
                public void run() {
                    for (int i = 0; i < 50000; i++) {
                        map.remove(map.keySet().iterator().next());
                    }

                    System.out.println("t10 over");
                }
            };

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();

            t6.start();
            t7.start();
            t8.start();
            t9.start();
            t10.start();

            t11.start();
            t12.start();
            t13.start();
            t14.start();
            t15.start();
            t16.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static  void  main(String[] args) {
        for (int i= 0; i < 5; i++) {
            ExecutorService executorService = Executors.newFixedThreadPool(50);
            for (int j = 0 ; j < 5; i++) {
                executorService.execute(new worker());
            }
        }
    }

}
class worker implements Runnable{

    @Override
    public void run() {
        new  TestLock();
    }
}