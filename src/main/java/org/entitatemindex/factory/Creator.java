package org.entitatemindex.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.entitatemindex.utils.StringUtils;

public class Creator<T> {

    private List<Method> methods;
    private Object instance;
    private Class<T> type;

    public Creator(Class<T> type,Object creatorInstance) {
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
            return type.cast(method.invoke(instance, params));
        } catch (IllegalArgumentException e) {
            throw new FactoryError("Could not create " + method.getReturnType().getName() + " because " + e, e);
        } catch (IllegalAccessException e) {
            throw new FactoryError("Could not create " + method.getReturnType().getName() + " because " + e, e);
        } catch (InvocationTargetException e) {
            throw new FactoryError("Could not create " + method.getReturnType().getName() + " because " + e, e);
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
            if (methodParamTypes.length == params.length) {
                for (int i = 0; i < params.length; i++) {
                    if (!methodParamTypes[i].isAssignableFrom(params[i].getClass())) {
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

    private String getNoMethodErrorMessage(Object[] params) {
        StringBuffer tmp = new StringBuffer();
        tmp.append("Could not find method named create* with param types ");
        boolean first = true;
        for (Object param : params) {
            first = StringUtils.addIfFalse(first, tmp, ",");
            tmp.append(param.getClass().getName());
        }
        return tmp.toString();
    }

}
