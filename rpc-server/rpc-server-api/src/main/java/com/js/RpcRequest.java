package com.js;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -978999773045241224L;
    private String className;

    private String methodName;

    private Object[] parames;

    private Class[] types;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParames() {
        return parames;
    }

    public void setParames(Object[] parames) {
        this.parames = parames;
    }

    public Class[] getTypes() {
        return types;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"className\":\"")
                .append(className).append('\"');
        sb.append(",\"methodName\":\"")
                .append(methodName).append('\"');
        sb.append(",\"parames\":")
                .append(Arrays.toString(parames));
        sb.append(",\"types\":")
                .append(Arrays.toString(types));
        sb.append('}');
        return sb.toString();
    }
}
