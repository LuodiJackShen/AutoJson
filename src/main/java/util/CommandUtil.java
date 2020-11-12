package util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;

import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory;
import org.jetbrains.plugins.terminal.TerminalView;

import java.io.IOException;

import javax.annotation.Nullable;

public class CommandUtil {
    public static void runFlutterPubRun(AnActionEvent e, @Nullable String filePath) {
        String terminalName = "AutoJson";
        String workingDirectory = e.getProject().getBasePath();
        String command = "flutter pub run build_runner build --delete-conflicting-outputs";
        TerminalView terminalView = TerminalView.getInstance(e.getProject());
        ToolWindow window = ToolWindowManager.getInstance(e.getProject())
                .getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID);
        if (window == null) {
            DialogUtil.showInfo("Please check that the following two plugins are installed: Terminal 和 Shell Script");
            return;
        }

        try {
            Content content = window.getContentManager().findContent(terminalName);
            if (content != null) {
                terminalView.closeTab(content);
            }

            Content localTerminal = window.getContentManager().findContent("Local");
            if (localTerminal != null) {
                terminalView.closeTab(localTerminal);
            }
        } catch (Exception exception) {}

        try {
            String workPath = filePath;
            if (workPath == null || workPath.isEmpty()) {
                workPath = workingDirectory;
            }

            ShellTerminalWidget terminalWidget = terminalView.createLocalShellWidget(workPath, terminalName);
            terminalWidget.executeCommand(command);
        } catch (IOException exception) {
            DialogUtil.showInfo("Cannot run command:" + command + "  " + exception.getMessage());
        }
    }
}
