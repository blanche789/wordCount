package com.blanche.service.impl;

import com.blanche.Run;
import com.blanche.service.HandlerService;
import com.blanche.view.WindowFrame;
import lombok.Data;
import lombok.Getter;

/**
 * @Auther:Blanche
 * @Date:2019/9/22
 * @Description:com.blanche.service.impl
 * @version:1.0
 */
public class CharServiceImpl implements HandlerService {
    private int count;

    @Override
    public void operate(String lineText) {
        count += lineText.length();
    }

    @Override
    public void clear() {
        count = 0;
    }

    @Override
    public void print() {
        if(WindowFrame.selectedFile != null){
            WindowFrame.content.append("字符数：" + count + "\n");
        }else {
            System.out.println("字符数：" + count);
        }
    }
}
