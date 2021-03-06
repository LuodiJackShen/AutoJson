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
 * work with json_serializable
 * by 老黑牛
 * 2019.01.24 17:27.
 */
public class GenerateJson extends AbsGenerateAnAction {
    private String JSON_PACKAGE_IMPORT;
    private String PART_IMPORT;
    private String ANNOTATION;
    private String JSON_METHOD;
    private String mFileName = "";
    private String mClassName = "";
    private String mFileContent = "";
    private boolean isHasThirdImport = false;
    private int mClassLine = -1;
    private int mLastRightBraces = -1;

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

            JSON_PACKAGE_IMPORT = "import 'package:json_annotation/json_annotation.dart';\n";
            PART_IMPORT = "part '" + mFileName + ".g.dart';\n";
            ANNOTATION = "@JsonSerializable()\n";
            if (isHasThirdImport) {
                ANNOTATION = "@JsonSerializable(explicitToJson: true)\n";
            }
            JSON_METHOD = "\nfactory " + mClassName
                    + ".fromJson(Map<String, dynamic> json) => _$" + mClassName
                    + "FromJson(json);\n\n"
                    + "Map<String, dynamic> toJson() => _$"
                    + mClassName
                    + "ToJson(this);\n";

            int line = mClassLine;
            if (line == -1) {
                return;
            }

            WriteCommandAction.runWriteCommandAction(mProject, () -> {
                mDocument.insertString(0, JSON_PACKAGE_IMPORT);
            });

            line += 1;
            mCaret.moveToVisualPosition(new VisualPosition(Math.max(line - 1, 0), 0));
            WriteCommandAction.runWriteCommandAction(mProject, () -> {
                mDocument.insertString(mCaret.getOffset(), ANNOTATION);
            });

            line += 1;
            mCaret.moveToVisualPosition(new VisualPosition(Math.max(line - 2, 0), 0));
            WriteCommandAction.runWriteCommandAction(mProject, () -> {
                mDocument.insertString(mCaret.getOffset(), PART_IMPORT);
            });

            mLastRightBraces += (line - mClassLine + 1);
            mCaret.moveToVisualPosition(new VisualPosition(mLastRightBraces, 0));
            int offset = mCaret.getOffset();
            WriteCommandAction.runWriteCommandAction(mProject, () -> {
                mDocument.insertString(offset == -1 ? 0 : offset, JSON_METHOD);
            });

            if (isRunCommand) {
                CommandUtil.runFlutterPubRun(e, CommonUtil.findLibPathOfFile(e));
            }
        } else {
            DialogUtil.showInfo("AutoJson: Can not find any Class.");
        }
    }

    private void initParams(AnActionEvent e) {
        isHasThirdImport = false;
        mClassLine = -1;

        mEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        mProject = e.getProject();
        mDocument = mEditor.getDocument();
        mCaret = mEditor.getCaretModel();
        mFile = PsiUtilBase.getPsiFileInEditor(mEditor, mProject);
        mFileName = mFile.getName();
        int dotIndex = mFileName.indexOf('.');
        mFileName = mFileName.substring(0, dotIndex);
        mFileContent = mDocument.getText();

        if (mFileContent.contains("import")) {
            isHasThirdImport = true;
        }

        mClassName = parseClassName(mFileContent);
        if (mFileContent != null && !mFileContent.equals("")) {
            int index = mFileContent.indexOf(mClassName);
            if (index != -1) {
                String targetStr = mFileContent.substring(0, index);
                String[] lines = targetStr.split("\n");
                mClassLine = lines.length;
            }

        }

        if (mFileContent != null && !mFileContent.equals("")) {
            int index = mFileContent.lastIndexOf("}");
            if (index != -1) {
                String targetStr = mFileContent.substring(0, index);
                String[] lines = targetStr.split("\n");
                mLastRightBraces = lines.length;
            }
        }
//        mClass = getTargetClass(mEditor, mFile);
//        mClassName = mClass.getName();
    }
}
