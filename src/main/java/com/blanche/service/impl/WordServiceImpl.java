package com.blanche.service.impl;

import com.blanche.service.HandlerService;
import com.blanche.view.WindowFrame;
import com.sun.deploy.util.StringUtils;

/**
 * @Auther:Blanche
 * @Date:2019/9/22
 * @Description:com.blanche.service.impl
 * @version:1.0
 */
public class WordServiceImpl implements HandlerService {
    private int wordNum;

    @Override
    public void operate(String fileText) {
        fileText.replaceAll("[(\\w+\\W+)]"," ");
        String[] s = StringUtils.splitString(fileText, " ");
        wordNum += s.length;
    }

    @Override
    public void clear() {
        wordNum = 0;

    }

    @Override
    public void print() {
        if (WindowFrame.selectedFile != null) {
            WindowFrame.content.append("词的数目:" + wordNum + "\n");
        }else {
            System.out.println("词的数目:" + wordNum);
        }
    }
}
