package com.jack.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jack.utils.AnnotationType;
import com.jack.utils.CommonUtil;
import com.jack.utils.Constants;
import com.jack.utils.DialogUtil;

import org.jetbrains.annotations.NotNull;

public class GenerateAndBuildAction extends AbsGenerateAnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (CommonUtil.lockFileExists(e)) {
            AnnotationType type = CommonUtil.getBuildType(e);
            if (type == AnnotationType.JAGUAR || type == AnnotationType.ONLY_JAGUAR_CLI) {
                new GenerateJaguarTerminal().actionPerformed(e);
            } else if (type == AnnotationType.JSON || type == AnnotationType.ONLY_JSON_CLI) {
                new GenerateJsonTerminal().actionPerformed(e);
            } else if (type == AnnotationType.ONLY_JAGUAR_ANNOTATION) {
                DialogUtil.showInfo("Can not find dependencies: \njaguar_serializer_cli");
            } else if (type == AnnotationType.ONLY_JSON_ANNOTATION) {
                DialogUtil.showInfo("Can not find dependencies: \njson_serializable");
            }

        } else {
            DialogUtil.showInfo("Can not find " + Constants.LOCK_FILE);
        }
    }
}
