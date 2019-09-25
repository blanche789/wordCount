package com.blanche.service.impl;

import com.blanche.service.HandlerService;
import com.blanche.view.WindowFrame;

/**
 * @Auther:Blanche
 * @Date:2019/9/22
 * @Description:com.blanche.service.impl
 * @version:1.0
 */
public class ComplexLineServiceImpl implements HandlerService {
    private int blankLineNum;
    private int codeLineNum;
    private int noteLineNum;
    private boolean flag=true;
    @Override
    public void operate(String fileText) {
        String str = fileText.replaceAll("\\s+", "");
        if(str.matches("/\\**.*")){
            flag = false;
        }else if(str.matches(".*\\**/")){
            flag = true;
        }
        if(flag){
            if(str.matches("( *\\{?)||( *}?)")){
                blankLineNum++;
            } else if (str.matches("\\w+.*")) {
                codeLineNum++;
            }else if(str.matches("(\\{?\\//+.*)|(\\}?\\//+.*) |(.*\\**/)")){
                noteLineNum++;
            }
        }else {
            noteLineNum++;
        }

    }

    @Override
    public void clear() {
        blankLineNum = 0;
        codeLineNum = 0;
        noteLineNum = 0;
    }

    @Override
    public void print() {
        if(WindowFrame.selectedFile != null){
            WindowFrame.content.append("空白行:" + blankLineNum + "\n");
            WindowFrame.content.append("代码行:" + codeLineNum + "\n");
            WindowFrame.content.append("注释行:" + noteLineNum + "\n");
        }else {
            System.out.println("空白行:" + blankLineNum);
            System.out.println("代码行:" + codeLineNum);
            System.out.println("注释行:" + noteLineNum);
        }
    }
}
