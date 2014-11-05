package com.zhk.jdk.concurrent.exector;

import java.util.Random;
import java.util.concurrent.Semaphore;

import com.zhk.util.DateUtil;

public class SemaphoreTest {  
    private final static Semaphore MAX_SEMA_PHORE = new Semaphore(10);  
    public static void main(String []args) {  
         for(int i = 0 ; i < 100 ; i++) {  
              final int num = i;  
              final Random radom = new Random();  
              new Thread() {  
                   public void run() {  
                       boolean acquired = false;  
                       try {  
                            MAX_SEMA_PHORE.acquire();  
                            acquired = true;  
                            System.out.println("�����̣߳�" + num + " �һ����ʹ��Ȩ��" + DateUtil.getDate());  
                            long time = 1000 * Math.max(1, Math.abs(radom.nextInt() % 10));  
                            Thread.sleep(time);  
                            System.out.println("�����̣߳�" + num + " ��ִ�����ˣ�" + DateUtil.getDate());  
                       }catch(Exception e) {  
                            e.printStackTrace();  
                       }finally {  
                            if(acquired) {  
                               MAX_SEMA_PHORE.release();  
                            }  
                       }  
                    }  
              }.start();  
         }  
    }  
}  
