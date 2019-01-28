package com.jack;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * by shenmingliang1
 * 2019.01.28 11:00.
 */

class Main {
    public static void main(String[] args) {
        String fileContent = "class TestFood {\n" +
                "    final String name;\n" +
                "    final double price;\n" +
                "\n" +
                "    TestFood(this.name,this.price);\n" +
                "}\n";

        String rgex = "class(.*?)\\{";
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(fileContent);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }

        System.out.println(list);

    }
}
