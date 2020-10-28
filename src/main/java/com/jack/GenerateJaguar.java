package com.jack;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SyntheticElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtilBase;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * work with jaguar_serializer
 * by shenmingliang1
 * 2019.01.24 17:27.
 */
public class GenerateJaguar extends AnAction {
    private PsiClass mClass;
    private Editor mEditor;
    private Project mProject;
    private Document mDocument;
    private CaretModel mCaret;
    private PsiFile mFile;

    private String JSON_PACKAGE_IMPORT;
    private String PART_IMPORT;
    private String ANNOTATION;
    private String mFileName = "";
    private String mClassName = "";
    private String mFileContent = "";
    private int mClassLine = -1;

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

            JSON_PACKAGE_IMPORT = "import 'package:jaguar_serializer/jaguar_serializer.dart';\n\n";
            PART_IMPORT = "part '" + mFileName + ".jser.dart';\n\n";
            ANNOTATION = "@GenSerializer()\n";
            String classStr = ANNOTATION + "class " + mClassName + "Serializer extends Serializer<"
                    + mClassName + "> with _$" + mClassName + "Serializer{}\n\n";

            mCaret.moveToVisualPosition(new VisualPosition(0, 0));
            WriteCommandAction.runWriteCommandAction(mProject, () -> {
                mDocument.insertString(0, classStr);
                mDocument.insertString(0, JSON_PACKAGE_IMPORT + PART_IMPORT);
            });

            selectionModel.selectWordAtCaret(true);
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        e.getPresentation().setEnabled(false);
        Project project = e.getProject();
        if (project != null) {
            Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
            PsiFile file = PsiUtilBase.getPsiFileInEditor(editor, project);
            if (file != null) {
                String fileName = file.getName();
                if (fileName.contains(".dart")) {
                    e.getPresentation().setEnabled(true);
                }
            }
        }
    }

    private void initParams(AnActionEvent e) {
        mEditor = e.getRequiredData(CommonDataKeys.EDITOR);
        mProject = e.getProject();
        mDocument = mEditor.getDocument();
        mCaret = mEditor.getCaretModel();
        mFile = PsiUtilBase.getPsiFileInEditor(mEditor, mProject);
        mFileName = mFile.getName();
        int dotIndex = mFileName.indexOf('.');
        mFileName = mFileName.substring(0, dotIndex);
        mFileContent = mDocument.getText();
        mClassName = parseClassName(mFileContent);
        if (mFileContent != null && !mFileContent.equals("")) {
            int index = mFileContent.indexOf(mClassName);
            String targetStr = mFileContent.substring(0, index);
            String[] lines = targetStr.split("\n");
            mClassLine = lines.length;
        }
//        mClass = getTargetClass(mEditor, mFile);
//        mClassName = mClass.getName();
    }

    private String parseClassName(String fileContent) {
        if (fileContent == null || fileContent.equals("")) {
            return "";
        }

        String rgex = "class(.*?)(extends(.*?))?(implements(.*?))?\\{";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(fileContent);
        while (m.find()) {
            return m.group(1).trim();
        }
        return "";
    }

    private PsiClass getTargetClass(Editor editor, PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
        PsiElement element = file.findElementAt(offset);
        if (element == null) {
            return null;
        } else {
            PsiClass target = PsiTreeUtil.getParentOfType(element, PsiClass.class);
            return target instanceof SyntheticElement ? null : target;
        }
    }

    private void showInfoDialog(String message) {
        Messages.showErrorDialog(message, "Info");
    }
}
