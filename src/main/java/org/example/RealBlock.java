package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RealBlock implements Block {

    private final String color;
    private final String material;

    @Override
    public Integer getCount() {
        return COUNTER_VALUE;
    }
}
