import org.example.Block;
import org.example.CompositeBlock;
import org.example.RealBlock;
import org.example.RealCompositeBlock;
import org.example.Wall;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WallTest {
    private static final Block concreteBlock = new RealBlock("gray", "concrete");
    private static final Block gypsumBlock = new RealBlock("gray", "gypsum");
    private static final Block stoneBlock = new RealBlock("gray", "stone");
    private static final Block pineBlock = new RealBlock("bronze", "wood");
    private static final Block oakBlock = new RealBlock("bronze", "wood");
    private static final Block brickBlock = new RealBlock("red", "brick");

    private static final Block plasticBlock = new RealBlock("yellow", "plastic");
    private static final Block plasticBlock2 = new RealBlock("black", "plastic");
    private static final Block plasticBlock3 = new RealBlock("blue", "plastic");
    private static final Block plasticBlock4 = new RealBlock("white", "plastic");

    private static final CompositeBlock compositeBlock = new RealCompositeBlock("gray", "multi-material");
    private static final CompositeBlock compositeWoodenBlock = new RealCompositeBlock("bronze", "wood");
    private static final CompositeBlock compositeWoodenBlock2 = new RealCompositeBlock("bronze", "wood");

    private static final CompositeBlock compositePlasticBlock = new RealCompositeBlock("multicolour", "plastic");
    private static final CompositeBlock compositePlasticBlock2 = new RealCompositeBlock("multicolour", "plastic");
    private static final CompositeBlock compositePlasticBlock3 = new RealCompositeBlock("multicolour", "plastic");
    private static final CompositeBlock compositePlasticBlock4 = new RealCompositeBlock("multicolour", "plastic");

    private static final Wall wall = new Wall();
    private static final Wall emptyWall = new Wall();

    @BeforeAll
    public static void buildWall() {
        compositeBlock.addBlocks(concreteBlock, gypsumBlock, stoneBlock);
        compositeWoodenBlock.addBlocks(pineBlock, oakBlock, compositeWoodenBlock2);
        compositePlasticBlock4.addBlocks(plasticBlock3, plasticBlock4);
        compositePlasticBlock3.addBlocks(compositePlasticBlock4, plasticBlock2);
        compositePlasticBlock2.addBlocks(compositePlasticBlock3, plasticBlock);
        compositePlasticBlock.addBlocks(compositePlasticBlock2);

        wall.addBlocks(brickBlock, compositeWoodenBlock, compositePlasticBlock, compositeBlock);

    }

    @org.junit.jupiter.api.Test
    void shouldReturnCorrectCountValue() {
        assertEquals(17, wall.count());
    }

    @org.junit.jupiter.api.Test
    void shouldReturnZeroCountValue() {
        assertEquals(0, emptyWall.count());
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAllBrickBlock() {
        assertThat(wall.findBlocksByMaterial("brick"), hasItems(brickBlock));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAllConcreteBlock() {
        assertThat(wall.findBlocksByMaterial("concrete"), hasItems(concreteBlock));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAllGypsumBlock() {
        assertThat(wall.findBlocksByMaterial("gypsum"), hasItems(gypsumBlock));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAllStoneBlock() {
        assertThat(wall.findBlocksByMaterial("stone"), hasItems(stoneBlock));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAllMultiMaterialBlock() {
        assertThat(wall.findBlocksByMaterial("multi-material"), hasItems(compositeBlock));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAllWoodBlock() {
        assertThat(wall.findBlocksByMaterial("wood"), hasItems(pineBlock, oakBlock, compositeWoodenBlock, compositeWoodenBlock2));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnAllPlasticBlock() {
        assertThat(wall.findBlocksByMaterial("plastic"), hasItems(plasticBlock, plasticBlock2, plasticBlock3, plasticBlock4,
                compositePlasticBlock, compositePlasticBlock2, compositePlasticBlock3, compositePlasticBlock4));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnEmptyCollectionWhenMaterialIsUnknown() {
        assertThat(wall.findBlocksByMaterial("unknown"), is(empty()));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnEmptyOptionalWhenColorIsUnknown() {
        assertThat(wall.findBlockByColor("unknown"), is(Optional.empty()));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenMaterialIsNull() {
        assertThrows(IllegalArgumentException.class, () -> wall.findBlocksByMaterial(null));
    }

    @org.junit.jupiter.api.Test
    void shouldThrowExceptionWhenColorIsNull() {
        assertThrows(IllegalArgumentException.class, () -> wall.findBlockByColor(null));
    }

    @org.junit.jupiter.api.Test
    void shouldReturnRedBlock() {
        testColor("red", brickBlock);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnYellowBlock() {
        testColor("yellow", plasticBlock);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnBlackBlock() {
        testColor("black", plasticBlock2);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnBlueBlock() {
        testColor("blue", plasticBlock3);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnWhiteBlock() {
        testColor("white", plasticBlock4);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnGrayBlock() {
        testColor("gray", compositeBlock, concreteBlock, gypsumBlock, stoneBlock);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnMultiColourBlock() {
        testColor("multicolour", compositePlasticBlock, compositePlasticBlock2, compositePlasticBlock3, compositePlasticBlock4);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnBronzeBlock() {
        testColor("bronze", oakBlock, pineBlock, compositeWoodenBlock, compositeWoodenBlock2);
    }

    private void testColor(String color, Block... acceptableBlocks) {
        assertTrue(wall.findBlockByColor(color).isPresent());
        assertThat(wall.findBlockByColor(color).get()).isIn(Arrays.asList(acceptableBlocks));
    }
}
