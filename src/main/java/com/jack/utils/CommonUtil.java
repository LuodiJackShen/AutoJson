package com.jack.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.jack.utils.Constants.LOCK_FILE;

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

    public static boolean lockFileExists(AnActionEvent e) {
        String libPath = findLibPathOfFile(e);

        if (libPath == null || libPath.isEmpty()) {
            return false;
        }

        File file = new File(libPath, LOCK_FILE);
        return file.exists();
    }

    public static AnnotationType getBuildType(AnActionEvent e) {
        AnnotationType annotationType = AnnotationType.ONLY_JSON_ANNOTATION;
        if (lockFileExists(e)) {
            String libPath = findLibPathOfFile(e);

            boolean hasJaguarAnno = false;
            boolean hasJaguarCli = false;
            boolean hasJsonAnno = false;
            boolean hasJsonCli = false;

            InputStream is = null;
            BufferedReader reader = null;
            try {
                is = new FileInputStream(libPath + "/" + LOCK_FILE);
                String line;
                reader = new BufferedReader(new InputStreamReader(is));
                line = reader.readLine();
                while (line != null) {
                    if (!hasJaguarAnno) {
                        hasJaguarAnno = line.contains(Constants.JAGUAR_ANNOTATION);
                    }
                    if (!hasJaguarCli) {
                        hasJaguarCli = line.contains(Constants.JAGUAR_CLI);
                    }
                    if (!hasJsonAnno) {
                        hasJsonAnno = line.contains(Constants.JSON_ANNOTATION);
                    }
                    if (!hasJsonCli) {
                        hasJsonCli = line.contains(Constants.JSON_CLI);
                    }
                    line = reader.readLine();
                }
            } catch (IOException exception) {
//                DialogUtil.showInfo(exception.toString());
                exception.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }

            if (hasJaguarAnno && !hasJaguarCli) {
                return AnnotationType.ONLY_JAGUAR_ANNOTATION;
            }

            if (!hasJaguarAnno && hasJaguarCli) {
                return AnnotationType.ONLY_JAGUAR_CLI;
            }

            if (hasJaguarAnno && hasJaguarCli) {
                return AnnotationType.JAGUAR;
            }

            if (hasJsonAnno && !hasJsonCli) {
                return AnnotationType.ONLY_JSON_ANNOTATION;
            }

            if (!hasJsonAnno && hasJsonCli) {
                return AnnotationType.ONLY_JSON_CLI;
            }

            if (hasJsonAnno && hasJsonCli) {
                return AnnotationType.JSON;
            }
        }

        return annotationType;
    }
}
