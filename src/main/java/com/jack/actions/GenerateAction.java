package com.jack.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jack.utils.AnnotationType;
import com.jack.utils.CommonUtil;
import com.jack.utils.Constants;
import com.jack.utils.DialogUtil;

import org.jetbrains.annotations.NotNull;

public class GenerateAction extends AbsGenerateAnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (CommonUtil.lockFileExists(e)) {
            AnnotationType type = CommonUtil.getBuildType(e);
            if (type == AnnotationType.JAGUAR || type == AnnotationType.ONLY_JAGUAR_ANNOTATION) {
                new GenerateJaguar().actionPerformed(e);
            } else if (type == AnnotationType.JSON || type == AnnotationType.ONLY_JSON_ANNOTATION) {
                new GenerateJson().actionPerformed(e);
            } else if (type == AnnotationType.ONLY_JAGUAR_CLI) {
                DialogUtil.showInfo("Can not find dependencies: \njaguar_serializer");
            } else if (type == AnnotationType.ONLY_JSON_CLI) {
                DialogUtil.showInfo("Can not find dependencies: \njson_annotation");
            }

        } else {
            DialogUtil.showInfo("Can not find " + Constants.LOCK_FILE);
        }
    }
}
