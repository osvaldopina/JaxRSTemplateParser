package org.entitatemindex.factory;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.entitatemindex.utils.StringUtils;

public class Creator<T> {
    
    private static Class<?>[][] compatibleClasses = {
        {byte.class, Byte.class},
        {short.class, Short.class},
        {int.class, Integer.class},
        {long.class, Long.class},
        {float.class, Float.class},
        {double.class, Double.class},
        {boolean.class, Boolean.class}
    };

    private List<Method> methods;
    private Object instance;
    private Class<T> type;

    public Creator(Class<T> type, Object creatorInstance) {
        this.type = type;
        this.methods = getCreateMethods(creatorInstance.getClass());
        checkMethodReturnTypes(methods);
        this.instance = creatorInstance;
    }

    public boolean isCreatorFor(Class<?> clazz) {
        return clazz.isAssignableFrom(type);
    }

    public T with(Object... params) {
        Method method = null;
        try {
            method = findMethod(params);
            Object[] methodParams = generateArrayIfNecessary(params, method);
            return type.cast(method.invoke(instance, methodParams));
        } catch (IllegalArgumentException e) {
            throw new FactoryError("Could not create " + method.getReturnType().getName() + " because " + e, e);
        } catch (IllegalAccessException e) {
            throw new FactoryError("Could not create " + method.getReturnType().getName() + " because " + e, e);
        } catch (InvocationTargetException e) {
            throw new FactoryError("Could not create " + method.getReturnType().getName() + " because " + e, e);
        }
    }

    private Object[] generateArrayIfNecessary(Object[] params, Method method) {
        if (isParamsSingleArray(method)) {
            Object[] paramsAsArray =
                    (Object[]) Array.newInstance(method.getParameterTypes()[0].getComponentType(), params.length);
            for (int i = 0; i < params.length; i++) {
                paramsAsArray[i] = params[i];
            }
            return new Object[] { paramsAsArray };
        } else {
            return params;
        }
    }

    private List<Method> getCreateMethods(Class<?> clazz) {
        List<Method> createMethods = new ArrayList<Method>();
        for (Method method : clazz.getMethods()) {
            if (method.getName().startsWith("create")) {
                createMethods.add(method);
            }
        }
        if (createMethods.size() == 0) {
            throw new FactoryError("Could not find a method with name create*");
        }
        return createMethods;
    }

    private void checkMethodReturnTypes(List<Method> methods) {
        for (Method method : methods) {
            if (!type.isAssignableFrom(method.getReturnType())) {
                throw new FactoryError("method " + method + " does not return " + type.getName());
            }
        }
    }

    private Method findMethod(Object[] params) {
        Method methodToReturn = null;
        for (Method method : methods) {
            methodToReturn = method;
            Class<?>[] methodParamTypes = method.getParameterTypes();
            if (isParamsSingleArray(method)) {
                Class<?> allParamTypes = allParametersClass(params);

                if (allParamTypes != null && allParamTypes.equals(methodParamTypes[0].getComponentType())) {
                    return method;
                }
            }
            if (methodParamTypes.length == params.length) {
                for (int i = 0; i < params.length; i++) {
                    if (!areClassesCompatible(methodParamTypes[i],params[i].getClass())) {
                        methodToReturn = null;
                        break;
                    }
                }
                if (methodToReturn != null) {
                    return methodToReturn;
                }
            }
        }

        throw new FactoryError(getNoMethodErrorMessage(params));
    }
    
    private boolean areClassesCompatible(Class<?> class1, Class<?> class2) {
        return (class1.isAssignableFrom(class2) || (wrapperAndPrimitives(class1,class2)));
    }

    private boolean wrapperAndPrimitives(Class<?> class1, Class<?> class2) {
        for(int i=0; i< compatibleClasses.length/2;i++) {
            if ((compatibleClasses[i][0].equals(class1) && compatibleClasses[i][1].equals(class2)) || 
                (compatibleClasses[i][0].equals(class2) && compatibleClasses[i][1].equals(class1))) { 
                  return true;
            }
        }
        return false;
        
    }

    private boolean isParamsSingleArray(Method method) {
        return method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isArray();
    }

    private Class<?> allParametersClass(Object[] params) {
        Class<?> tmp = null;
        for (Object object : params) {
            if (tmp == null) {
                tmp = object.getClass();
            } else {
                if (!object.getClass().equals(tmp)) {
                    return null;
                }
            }
        }
        return tmp;

    }

    private String getNoMethodErrorMessage(Object[] params) {
        StringBuffer tmp = new StringBuffer();
        tmp.append("Could not find method named create* for type ");
        tmp.append(type.getName());
        tmp.append(" with param types ");
        boolean first = true;
        for (Object param : params) {
            first = StringUtils.addIfFalse(first, tmp, ",");
            tmp.append(param.getClass().getName());
        }
        return tmp.toString();
    }

}
