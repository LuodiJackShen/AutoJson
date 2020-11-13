package com.jack.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;

public class CommonUtil {
    public static String findLibPathOfFile(AnActionEvent e) {
        try {
            PsiFile file = PsiUtilBase.getPsiFileInEditor(
                    e.getRequiredData(CommonDataKeys.EDITOR),
                    e.getProject());
            String path = file.getOriginalFile().getVirtualFile().getPath();
            if (!path.contains("/lib/")) {
                DialogUtil.showInfo("Can not find the below directory: lib");
                return "";
            }
            int index = path.lastIndexOf("/lib");
            return path.substring(0, index);
        } catch (Exception exception) {
            return null;
        }
    }
}
