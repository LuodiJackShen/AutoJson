package com.jack.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.psi.util.PsiUtilBase;

import com.jack.utils.CommandUtil;
import com.jack.utils.CommonUtil;
import com.jack.utils.DialogUtil;

/**
 * work with jaguar_serializer
 * by 老黑牛
 * 2019.01.24 17:27.
 */
public class GenerateJaguar extends AbsGenerateAnAction {
    private String JSON_PACKAGE_IMPORT;
    private String PART_IMPORT;
    private String ANNOTATION;
    private String mFileName = "";
    private String mClassName = "";
    private String mFileContent = "";
    private int mClassLine = -1;
    private String mFilePath = "";

    protected boolean isRunCommand = false;

    @Override
    public void actionPerformed(AnActionEvent e) {
        initParams(e);
        SelectionModel selectionModel = mEditor.getSelectionModel();
        if (selectionModel.hasSelection() || (mClassName != null && !mClassName.equals(""))) {
            String selectedStr = selectionModel.getSelectedText();
            selectedStr = selectedStr == null || selectedStr.equals("") ? "" : selectedStr;
            mClassName = mClassName == null || mClassName.equals("") ? selectedStr : mClassName;

            if (mClassName.equals("")) {
                return;
            }

            JSON_PACKAGE_IMPORT = "import 'package:jaguar_serializer/jaguar_serializer.dart';\n";
            PART_IMPORT = "part '" + mFileName + ".jser.dart';\n";
            ANNOTATION = "@GenSerializer()\n";
            String classStr = ANNOTATION + "class " + mClassName + "Serializer extends Serializer<"
                    + mClassName + "> with _$" + mClassName + "Serializer{}\n";

            mCaret.moveToVisualPosition(new VisualPosition(Math.max(mClassLine - 1, 0), 0));
            WriteCommandAction.runWriteCommandAction(mProject, () -> {
                mDocument.insertString(mCaret.getOffset(), JSON_PACKAGE_IMPORT + PART_IMPORT + classStr);
            });

            selectionModel.selectWordAtCaret(true);

            if (isRunCommand) {
                CommandUtil.runFlutterPubRun(e, CommonUtil.findLibPathOfFile(e));
            }
        } else {
            DialogUtil.showInfo("AutoJson: Can not find any Class.");
        }
    }

    private void initParams(AnActionEvent e) {
        mEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        mProject = e.getProject();
        mDocument = mEditor.getDocument();
        mCaret = mEditor.getCaretModel();
        mFile = PsiUtilBase.getPsiFileInEditor(mEditor, mProject);
        mFilePath = mFile.getOriginalFile().getVirtualFile().getPath();

        mFileName = mFile.getName();
        int dotIndex = mFileName.indexOf('.');
        mFileName = mFileName.substring(0, dotIndex);
        mFileContent = mDocument.getText();
        mClassName = parseClassName(mFileContent);
        if (mFileContent != null && !mFileContent.equals("")) {
            int index = mFileContent.indexOf(mClassName);
            if (index != -1) {
                String targetStr = mFileContent.substring(0, index);
                String[] lines = targetStr.split("\n");
                mClassLine = lines.length;
            }
        }
    }

}
