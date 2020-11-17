package com.fertifa.app.utils;

import com.fertifa.app.constants.Constances;

public class TokenMaker {


    /**
     * Token builder with length
     * @param count
     * @return
     */
    public static String buildToken(int count) {

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * Constances.TOKE_BASE.length());
            builder.append(Constances.TOKE_BASE.charAt(character));
        }
        return builder.toString();

    }
}
