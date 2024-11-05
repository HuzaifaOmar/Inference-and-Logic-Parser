package com.foe.utilities;

import java.util.Stack;

public class StringUtility {

    public static Stack<Character> StringToReversedStack(String input) {
        Stack<Character> stack = new Stack<>();
        for(int i = input.length() - 1; i >= 0; i--) {
            stack.push(input.charAt(i));
        }
        return stack;
    }
}
