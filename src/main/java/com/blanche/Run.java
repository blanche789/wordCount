package com.blanche;

import com.blanche.service.HandlerService;
import com.blanche.service.impl.CharServiceImpl;
import com.blanche.service.impl.ComplexLineServiceImpl;
import com.blanche.service.impl.LineServiceImpl;
import com.blanche.service.impl.WordServiceImpl;
import com.blanche.view.WindowFrame;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import javax.swing.*;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Run
{   HandlerService handlerService;
    public static boolean IS_RECURSION;
    public static boolean CALL_UI;
    private static final Map<String,HandlerService> orderMap;
    private static  File file;
    public static String Rex;
    public static String[] passArgs;
    static {
        orderMap = new HashMap<>();
        orderMap.put(Order.W.getOrderName(),new WordServiceImpl());
        orderMap.put(Order.C.getOrderName(),new CharServiceImpl());
        orderMap.put(Order.L.getOrderName(),new LineServiceImpl());
        orderMap.put(Order.A.getOrderName(),new ComplexLineServiceImpl());
    }


    public static void main( String[] args)
    throws IOException{
        passArgs = args;
        try {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals(Order.S.getOrderName())) {
                        IS_RECURSION = true;
                    }
                    if (args[i].equals(Order.X.getOrderName())) {
                        //用户界面
                        CALL_UI = true;
                    }
                }
                file = new File(args[args.length - 1]);
                if (!file.canRead() && file.getName().contains("*")) {
                    Rex = file.getName().replaceAll("\\.", "").replaceAll("[*?]", "");
                    File parentFile = file.getParentFile();
                    file = parentFile;
                }
                if(file.canRead()){
                    if (file.isDirectory()){
                        File[] files = file.listFiles();
                        if (IS_RECURSION) {
                            for (File file1 : files) {
                                read(file1, args);
                            }
                        }else{
                            for (File file1 : files) {
                                if (!file1.isDirectory()) {
                                    read(file1, args);
                                }else {
                                    System.out.println(file1.getPath());
                                }
                            }
                        }
                    }else {
                        read(file,args);
                    }

                }else {
                    throw new RuntimeException("文件不存在");
                }
            }
        } finally {
            if(CALL_UI){
                WindowFrame windowFrame = new WindowFrame();
                windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowFrame.setVisible(true);
            }
        }
    }

    public static void read(File file1, String[] args) throws IOException{
        if (file1.isDirectory()) {
            File[] files = file1.listFiles();
            if (files != null) {
                for (File file2 : files) {
                    read(file2,args);
                }
            }else {
                return;
            }
        }else{
            if (Rex == null) {
                Rex = file.getName().replaceAll("\\w*\\.","");
            }
            if(file1.getName().contains(Rex)){
                    System.out.println( file1.getPath());
                    for (int i = 0; i <args.length - 1; i++) {
                        if (orderMap.containsKey((args[i]))) {
                            HandlerService handlerService = orderMap.get((args[i]));
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file1),"gbk"));
                            String strLine;
                            while ((strLine = bufferedReader.readLine()) != null) {
                                handlerService.operate(strLine);
                            }
                            handlerService.print();
                            handlerService.clear();
                        } else if (args[i].equals(Order.S.getOrderName())) {

                        } else if(args[i].equals(Order.X.getOrderName())){
                        } else {
                            throw new RuntimeException(args[i] + "指令不存在");
                        }
                    }
                }
        }

    }
}
