package com.ken.other.condition;

public class WindowsListService implements ListService {

    @Override
    public String showListCmd() {
        return "dir";
    }
}
