package com.renren.x2.feed.beans;

import java.util.ArrayList;


public class TimeHolderList extends ArrayList<TimeHolder> {

    private static final long serialVersionUID = 4582457315594129861L;
    

    @Override
    public String toString() {
        long totalCost = 0;
        StringBuilder sb = new StringBuilder();
        for(TimeHolder th : this){
            totalCost += th.getCost();
            sb.append(th);
            sb.append("->");
        }
        sb.insert(0, "[" + totalCost + "]-");
        return sb.substring(0, sb.lastIndexOf("->"));
    }
}
