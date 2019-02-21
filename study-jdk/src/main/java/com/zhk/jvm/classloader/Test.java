package com.zhk.jvm.classloader;


import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 上午9:06 19/2/21
 * @copyright Navi WeCloud
 */
public class Test {
    public static void main(String[] args) {

        Class<Launcher> launcherClass = Launcher.class;
        try {
            //获取Launcher中的loader对象，它是一个AppClassLoader
            Field loaderField = launcherClass.getDeclaredField("loader");
            Field launcherField = launcherClass.getDeclaredField("launcher");
            launcherField.setAccessible(true);
            //先获取到一个launcher对象，在Launcher类中是一个静态成员
            Object launcher = launcherField.get(null);
            loaderField.setAccessible(true);
            //launcher中的appClassLoader对象
            Object appClassLoader = loaderField.get(launcher);
            //获取AppClassLoader.getAppClassLoader静态方法重新创建一个新的appClassLoader对象
            Class<?> loaderClass = appClassLoader.getClass();
            Method getAppClassLoaderMethod = loaderClass.getMethod("getAppClassLoader", ClassLoader.class);
            getAppClassLoaderMethod.setAccessible(true);

            //需要获取原来默认的appclassloader的parent，在构造新的appclassloader的时候需要一个父类加载器
            Method getParentMethod = loaderClass.getMethod("getParent");
            Object parentClassLoader = getParentMethod.invoke(appClassLoader);
            //创建了一个新的appclassloader
            Object newAppClassLoader = getAppClassLoaderMethod.invoke(null, parentClassLoader);

            //获取loadClass方法
            Method loadClassMethod = loaderClass.getMethod("loadClass", String.class, boolean.class);
            loadClassMethod.setAccessible(true);
            //使用新创建的AppClassLoader来加载当前的Test类，当前的Test没有写包名，所以直接写Test就可以了
            Object testClass = loadClassMethod.invoke(newAppClassLoader, "Test", false);
            System.out.println(testClass);
            System.out.println(Test.class);
            //判断两个Test.class是否相等
            System.out.println(Test.class.equals(testClass));
            //验证新加载的Test.class创建的对象能否强转为当前默认的Test.class
            Test t = (Test) ((Class) testClass).newInstance();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}