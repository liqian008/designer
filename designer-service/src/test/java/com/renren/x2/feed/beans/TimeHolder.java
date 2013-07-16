package com.renren.x2.feed.beans;


public class TimeHolder {
    private String methodName;
    
    private long cost;

    
    public TimeHolder(String methodName, long l) {
        super();
        this.methodName = methodName;
        this.cost = l;
    }


    public TimeHolder() {
        // TODO Auto-generated constructor stub
    }


    public String getMethodName() {
        return methodName;
    }

    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    
    public long getCost() {
        return cost;
    }

    
    public void setCost(long cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return methodName + "(" + cost + ")";
    }
}
