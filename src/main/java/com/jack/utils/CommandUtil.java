package com.jack.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory;
import org.jetbrains.plugins.terminal.TerminalView;

import java.io.IOException;

public class CommandUtil {
    public static void runFlutterPubRun(AnActionEvent e, @Nullable String filePath) {
        // 4.0.x
//        String terminalName = "Local (2)";

        // 4.1.x
        String terminalName = "AutoJson";

        String workingDirectory = e.getProject().getBasePath();
        String command = "flutter pub run build_runner build --delete-conflicting-outputs";
        TerminalView terminalView = TerminalView.getInstance(e.getProject());
        ToolWindow window = ToolWindowManager.getInstance(e.getProject())
                .getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID);
        if (window == null) {
            DialogUtil.showInfo("Please check that the following two plugins are installed: Terminal and Shell Script");
            return;
        }

        try {
            Content content = window.getContentManager().findContent(terminalName);
            if (content != null) {
                // 4.0.x
//                terminalView.detachWidgetAndRemoveContent(content);
                // 4.1.x
                terminalView.closeTab(content);
            }

            Content localTerminal = window.getContentManager().findContent("Local");
            if (localTerminal != null) {
                // 4.0.x
//                terminalView.detachWidgetAndRemoveContent(content);
                // 4.1.x
                terminalView.closeTab(content);
            }
        } catch (Exception exception) {}

        try {
            String workPath = filePath;
            if (workPath == null || workPath.isEmpty()) {
                workPath = workingDirectory;
            }

            // 4.0.x
//            ShellTerminalWidget terminalWidget = terminalView.createLocalShellWidget(workPath);
            // 4.1.x
            ShellTerminalWidget terminalWidget = terminalView.createLocalShellWidget(workPath, terminalName);
            terminalWidget.executeCommand(command);
        } catch (IOException exception) {
            DialogUtil.showInfo("Cannot run command:" + command + "  " + exception.getMessage());
        }
    }
}
