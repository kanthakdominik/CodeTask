package org.example;

import lombok.Getter;

import java.util.List;

@Getter
public class RealCompositeBlock extends RealBlock implements CompositeBlock {

    List<Block> blocks;

    public RealCompositeBlock(String color, String material, List<Block> blocks) {
        super(color, material);
        this.blocks = blocks;
    }

    @Override
    public Integer getCount() {
        return COUNTER_VALUE + getBlocks().stream()
                .mapToInt(Block::getCount)
                .sum();
    }
}
