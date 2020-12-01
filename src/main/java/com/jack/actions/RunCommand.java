package com.jack.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;

import org.jetbrains.annotations.NotNull;

import com.jack.utils.CommandUtil;
import com.jack.utils.CommonUtil;

public class RunCommand extends AbsGenerateAnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        CommandUtil.runFlutterPubRun(e, CommonUtil.findLibPathOfFile(e));
    }
}
