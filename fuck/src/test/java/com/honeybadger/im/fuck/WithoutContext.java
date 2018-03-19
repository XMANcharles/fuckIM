/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: WithoutContext
 * Author:   Neo Geng
 * Date:     2018/2/1 15:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2018/2/1
 * @since 1.0.0
 */
public class WithoutContext {

    @Test
    public void bCrypt() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("0001");
        System.out.println(hashedPassword);
    }

    @Test
    public void factorial() {
        int n = 20;
        long sum = 1;
        for (;n>0;n--){
            sum *= n;
        }
        System.out.println(sum);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int temp = 0;
        while (scanner.hasNextInt()){
            temp+=scanner.nextInt();
        }
        System.out.println(temp);
    }

}
