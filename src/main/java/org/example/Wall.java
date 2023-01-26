package org.example;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall() {
        blocks = buildWall();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.stream()
                .mapToInt(Block::getCount)
                .sum();
    }

    private List<Block> buildWall() {
        Block concreteBlock = new RealBlock("gray", "concrete");
        Block gypsumBlock = new RealBlock("gray", "gypsum");
        Block stoneBlock = new RealBlock("gray", "stone");
        Block pineBlock = new RealBlock("bronze", "wood");
        Block oakBlock = new RealBlock("bronze", "wood");
        Block brickBlock = new RealBlock("red", "brick");

        CompositeBlock compositeBlock = new RealCompositeBlock("gray", "multi-material", List.of(concreteBlock, gypsumBlock, stoneBlock));
        CompositeBlock woodenBlock = new RealCompositeBlock("bronze", "wood", List.of(pineBlock, oakBlock));
        CompositeBlock woodenBlock2 = new RealCompositeBlock("bronze", "wood", List.of());

        CompositeBlock plasticBlock = new RealCompositeBlock("multicolour", "plastic",
                List.of(new RealBlock("yellow", "plastic"), new RealCompositeBlock("multicolour", "plastic",
                        List.of(new RealBlock("black", "plastic"), new RealCompositeBlock("multicolour", "plastic",
                                List.of(new RealBlock("white", "plastic"), new RealBlock("blue", "plastic")))))));

        return List.of(concreteBlock, gypsumBlock, stoneBlock, pineBlock, oakBlock, brickBlock, compositeBlock, woodenBlock, woodenBlock2, plasticBlock);
    }
}
