package util;

import com.intellij.openapi.ui.Messages;

public class DialogUtil {
    public static void showInfo(String info) {
        Messages.showErrorDialog(info, "Info");
    }
}
