package com.zl.github.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


import static lombok.AccessLevel.PRIVATE;

/**
 * @Author zl
 * @Date 2019-09-16
 * @Des ${todo}
 */
@AllArgsConstructor(access = PRIVATE)
@Getter
public enum  Punctuation {
    /**
     * Dot symbol. ".".
     */
    DOT("."),
    /**
     * Semicolon symbol. ";".
     */
    SEMICOLON(";"),
    /**
     * Left parenthesis symbol. "(".
     */
    LPAREN("("),
    /**
     * Right parenthesis symbol. ")".
     */
    RPAREN(")"),
    /**
     * Comma symbol. ",".
     */
    COMMA(",");

    /**
     * Punctuation character.
     */
    private final String symbol;
}
