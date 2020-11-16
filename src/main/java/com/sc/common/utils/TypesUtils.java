package com.sc.common.utils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public abstract class TypesUtils {

    /**
     * @see Class#getTypeParameters
     * @see Class#getGenericSuperclass
     * @see Class#getGenericInterfaces
     * @see java.lang.reflect.Constructor#getTypeParameters
     * @see java.lang.reflect.Constructor#getGenericParameterTypes
     * @see java.lang.reflect.Method#getGenericExceptionTypes
     * @see java.lang.reflect.Method#getTypeParameters
     * @see java.lang.reflect.Method#getGenericReturnType
     * @see java.lang.reflect.Method#getGenericParameterTypes
     * @see java.lang.reflect.Method#getGenericExceptionTypes
     * @see java.lang.reflect.Field#getGenericType
     */

    /**
     * packing ClazzUtils.isAssignableFrom
     * @see ClazzUtils#isAssignableFrom(Class, Class)
     * @param leftType
     * @param rightType
     * @return
     */
    public static boolean isAssignableFrom(Type leftType, Type rightType) {
        return false;
    }

    /**
     * array类型
     *  1.Type是Class, primitive array(eg: int[]), array(eg: JsonResult[])
     *  2.Type是GenericArrayType, eg: List<String>[]; class Book<T> {  T[] items; List<List<T>>[] books }
     * @param type
     * @return 如果是array，返回true，否则返回false; if type==null return false
     */
    public static boolean isArray(Type type) {
        return type != null && (
                    (type instanceof Class && ((Class<?>) type).isArray())
                 || (type instanceof GenericArrayType)
                );
    }










}
