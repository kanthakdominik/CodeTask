package org.example;

import java.util.List;
import java.util.Optional;

public interface Block {
    Integer COUNTER_VALUE = 1;

    String getColor();

    String getMaterial();

    /**
     * Returns Block count value
     */
    Integer getCount();

    /**
     * Returns Block by specified color
     */
    Optional<Block> getBlockByColor(String color);

    /**
     * Returns list of Blocks by specified material
     */
    List<Block> getBlocksByMaterial(String material);
}