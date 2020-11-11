package com.jack;

import com.intellij.openapi.actionSystem.AnActionEvent;

public class GenerateJaguarTerminal extends GenerateJaguar {
    @Override
    public void actionPerformed(AnActionEvent e) {
        isRunCommand = true;
        super.actionPerformed(e);
    }
}
