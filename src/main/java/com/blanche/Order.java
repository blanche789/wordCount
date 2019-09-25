package com.blanche;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Auther:Blanche
 * @Date:2019/9/22
 * @Description:com.blanche
 * @version:1.0
 */
@Getter
@AllArgsConstructor
public enum Order {
    W("-w"),
    C("-c"),
    L("-l"),
    X("-x"),
    S("-s"),
    A("-a");

    private String orderName;
}
