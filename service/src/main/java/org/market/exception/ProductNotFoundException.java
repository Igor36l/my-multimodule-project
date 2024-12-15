package org.market.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException (){
        super("Извините продукт не найден. Попробуйте позже или обратитесь в службу поддержки.");
    }
}
