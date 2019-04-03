package com.company;

public class ListModelElement {
    String displayName;
    String expressionName;

    public ListModelElement(String displayName, String expressionName) {
        this.displayName = displayName;
        this.expressionName = expressionName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getExpressionName() {
        return expressionName;
    }
}
