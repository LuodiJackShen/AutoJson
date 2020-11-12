package com.jack;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import org.jetbrains.annotations.NotNull;

import util.CommandUtil;
import util.CommonUtil;

public class RunCommand extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        CommandUtil.runFlutterPubRun(e, CommonUtil.findLibPathOfFile(e));
    }
}
