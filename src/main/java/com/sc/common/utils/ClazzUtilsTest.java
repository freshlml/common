package com.sc.common.utils;


import com.sc.common.component.*;
import com.sc.common.enums.ScanTypeEnum;
import com.sc.common.vo.JsonResult;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.List;

/**
 * test for ClazzUtils
 */
public class ClazzUtilsTest {

    public static void main(String argv[]) throws Exception {

        //testForInnerClazz();

        //testForName();

        //testIsAssignableFrom();

        //testGetClassPathofCurrentPackage();


        testClazzTree();

        System.out.println(1);
    }

    public static void testForInnerClazz() {
        TestForInnerClazz instance = new TestForInnerClazz();
        TestForInnerClazz.PubInnerClazz pubInnerClazz = instance.new PubInnerClazz();//public inner class or instance.newPubInnerClazz();
        Class<? extends TestForInnerClazz.PubInnerClazz> pubInnerClazzClass = pubInnerClazz.getClass(); //public inner class的Class Object or instance.getPubInnerClazzClass
        System.out.println(pubInnerClazzClass.getName()); //com.sc.common.utils.TestForInnerClazz$PubInnerClazz
        System.out.println(pubInnerClazzClass.getDeclaringClass().getName()); //com.sc.common.utils.TestForInnerClazz

        TestForInnerClazz.PubStaticInnerClazz pubStaticInnerClazz = new TestForInnerClazz.PubStaticInnerClazz(); //public static inner class
        Class<TestForInnerClazz.PubStaticInnerClazz> pubStaticInnerClazzClass = TestForInnerClazz.PubStaticInnerClazz.class; //public static inner class的Class Object
        System.out.println(pubStaticInnerClazzClass.getName()); //com.sc.common.utils.TestForInnerClazz$PubStaticInnerClazz
        System.out.println(pubStaticInnerClazzClass.getDeclaringClass().getName()); //com.sc.common.utils.TestForInnerClazz

        instance.useNonPubInnerClazz();//NonPubInnerClass
        instance.useNonPubStaticInnerClazz(); //NonPubStaticInnerClass

        //通过外部类获取内部类的Class Object
        Class<TestForInnerClazz> clazz = TestForInnerClazz.class;
        Class<?>[] currentAllInnerClazz = clazz.getDeclaredClasses();
        //from supper class
        Class<? super TestForInnerClazz> supperClazz = clazz.getSuperclass();


    }

    public static void testForName() throws Exception {
        Class<?> intClass = ClazzUtils.forName("int", null);
        System.out.println("int: " + intClass);
        System.out.println("int.class == intClass: " + (intClass == int.class));
        Class<?> intArrayClass = ClazzUtils.forName("int[]", null);
        System.out.println("int[]: " + intArrayClass);
        System.out.println("int[].class == intArrayClass: " + (intArrayClass==int[].class));
        Class<?> objArray1 = ClazzUtils.forName("[Lcom.sc.common.vo.JsonResult;", null);
        System.out.println("objArray1: " + objArray1);
        System.out.println("obj[].class ==  objArray1: " + (objArray1 == com.sc.common.vo.JsonResult[].class));
        Class<?> objArray2 = ClazzUtils.forName("com.sc.common.vo.JsonResult[]", null);
        System.out.println("objArray2: " + objArray2);
        System.out.println("obj[].class ==  objArray2: " + (objArray1 == com.sc.common.vo.JsonResult[].class));
        Class<?> clazz = ClazzUtils.forName("com.sc.common.utils.TestForInnerClazz", null);
        System.out.println(clazz);
        Class<?> innerClazz = ClazzUtils.forName("com.sc.common.utils.TestForInnerClazz$PubStaticInnerClazz", null);
        System.out.println(innerClazz);

        System.out.println("testForName");
    }


    private static void testIsAssignableFrom() {
        //primitive
        System.out.println(int.class.isAssignableFrom(int.class));
        System.out.println(int.class.isAssignableFrom(Integer.class));
        System.out.println(Integer.class.isAssignableFrom(int.class));

        //array(primitive)
        System.out.println(int[].class.isAssignableFrom(int[].class));
        System.out.println(Integer[].class.isAssignableFrom(int[].class));
        System.out.println(int[].class.isAssignableFrom(Integer[].class));

        System.out.println(ClazzUtils.isAssignableFrom(int.class, Integer.class));
        System.out.println(ClazzUtils.isAssignableFrom(Integer.class, int.class));


    }

    private static void testGetClassPathofCurrentPackage() {
        System.out.println(ClazzUtils.getClassPathOfCurrentPackage(null, null));
        System.out.println(ClazzUtils.getClassPathOfCurrentPackage(null, "/com"));
        System.out.println(ClazzUtils.getClassPathOfCurrentPackage(int.class, "/com"));
        System.out.println(ClazzUtils.getClassPathOfCurrentPackage(int[].class, "/com"));
        System.out.println(ClazzUtils.getClassPathOfCurrentPackage(TestForInnerClazz[].class, "NumberUnitUtils.class"));
        String classpath = ClazzUtils.getClassPathOfCurrentPackage(JsonResult.class, "BasicJsonResult.class");
        System.out.println(classpath);
        URL classpathResource = ClazzUtils.getDefaultClassLoader().getResource(classpath);
        System.out.println(classpathResource);

    }


    private static void testClazzTree() {

        Component<Class<?>> result = ClazzUtils.clazzTree(A.class);
        Component<Class<?>> result2 = ClazzUtils.clazzTree(int.class);
        Component<Class<?>> result3 = ClazzUtils.clazzTree(int[].class);
        Component<Class<?>> result4 = ClazzUtils.clazzTree(A[].class);

        Class<?> node = result.getInfo();
        List<Component<Class<?>>> childs = result.getChilds();

        List<Class<?>> supper1 = ClazzUtils.getAllSuperClass(A.class);
        List<Class<?>> supper2 = ClazzUtils.getAllSuperClass(ScanTypeEnum.class);
        List<Class<?>> supper3 = ClazzUtils.getAllSuperClass(int.class);
        List<Class<?>> supper4 = ClazzUtils.getAllSuperClass(ClazzComponentResolver.class);
        List<Class<?>> supper5 = ClazzUtils.getAllSuperClass(int[].class);
        List<Class<?>> supper6 = ClazzUtils.getAllSuperClass(A[].class);

        List<Class<?>> inter1 = ClazzUtils.getAllInterfaces(A.class);
        List<Class<?>> inter2 = ClazzUtils.getAllInterfaces(ScanTypeEnum.class);
        List<Class<?>> inter3 = ClazzUtils.getAllInterfaces(int.class);
        List<Class<?>> inter4 = ClazzUtils.getAllInterfaces(ClazzComponentResolver.class);
        List<Class<?>> inter5 = ClazzUtils.getAllInterfaces(int[].class);
        List<Class<?>> inter6 = ClazzUtils.getAllInterfaces(A[].class);


        System.out.println(result);

    }

    public interface D1 {}
    public interface C2 {}
    public interface C1 extends C2 {}
    public static class B213 {}
    public static class B21 extends B213{}
    public interface B223{}
    public interface B22 extends B223 {}
    public static class B1 extends B21 implements B22{}
    public class A extends B1 implements C1, D1 {}

}
