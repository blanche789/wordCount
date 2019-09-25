package com.blanche.service.impl;

import com.blanche.service.HandlerService;
import com.blanche.view.WindowFrame;
import lombok.Data;

/**
 * @Auther:Blanche
 * @Date:2019/9/22
 * @Description:com.blanche.service.impl
 * @version:1.0
 */
public class LineServiceImpl implements HandlerService {
    private int line;

    @Override
    public void operate(String fileText) {
        line += 1;
    }

    @Override
    public void clear() {
        line = 0;
    }

    @Override
    public void print() {
        if (WindowFrame.selectedFile != null) {
            WindowFrame.content.append("文本行数：" + line + "\n");
        } else {
            System.out.println("文本行数：" + line);
        }
    }
}
