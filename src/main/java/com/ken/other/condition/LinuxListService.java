package com.ken.other.condition;

public class LinuxListService implements ListService {

    @Override
    public String showListCmd() {
        return "ls";
    }
}
