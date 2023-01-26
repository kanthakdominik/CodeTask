package org.example;

import java.util.List;
import java.util.Optional;

public interface Block {
    Integer COUNTER_VALUE = 1;

    String getColor();

    String getMaterial();

    Integer getCount();

    Optional<Block> checkColor(String color);

    List<Block> checkMaterial(String material);
}