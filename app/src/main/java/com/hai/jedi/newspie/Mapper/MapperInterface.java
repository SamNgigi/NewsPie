package com.hai.jedi.newspie.Mapper;

public interface MapperInterface<From, To> {
    To map(From from);
}
