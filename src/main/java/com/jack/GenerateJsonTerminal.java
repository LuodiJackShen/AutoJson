package com.jack;

import com.intellij.openapi.actionSystem.AnActionEvent;

public class GenerateJsonTerminal extends GenerateJson {
    @Override
    public void actionPerformed(AnActionEvent e) {
        isRunCommand = true;
        super.actionPerformed(e);
    }
}
