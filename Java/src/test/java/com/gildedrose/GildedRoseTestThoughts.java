package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GildedRoseTestThoughts {

    private Item update(Item item) {
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return items[0];
    }

    // ---------------------------
    // Itens normais
    // ---------------------------

    @Test
    void normalItem_decreasesQuality() {
        Item item = update(new Item("Normal Item", 10, 20));

        assertEquals(19, item.quality);
    }

    @Test
    void normalItem_decreasesSellIn() {
        Item item = update(new Item("Normal Item", 10, 20));

        assertEquals(9, item.sellIn);
    }

    @Test
    void normalItem_qualityNeverNegative() {
        Item item = update(new Item("Normal Item", 10, 0));

        assertEquals(0, item.quality);
    }

    @Test
    void normalItem_degradesTwiceAfterExpiration() {
        Item item = update(new Item("Normal Item", 0, 10));

        assertEquals(8, item.quality);
    }

    // ---------------------------
    // Aged Brie
    // ---------------------------

    @Test
    void agedBrie_increasesQuality() {
        Item item = update(new Item("Aged Brie", 10, 10));

        assertEquals(11, item.quality);
    }

    @Test
    void agedBrie_increasesTwiceAfterExpiration() {
        Item item = update(new Item("Aged Brie", 0, 10));

        assertEquals(12, item.quality);
    }

    @Test
    void agedBrie_neverExceeds50() {
        Item item = update(new Item("Aged Brie", 10, 50));

        assertEquals(50, item.quality);
    }

    // ---------------------------
    // Backstage passes
    // ---------------------------

    @Test
    void backstagePass_increaseBy1_whenMoreThan10Days() {
        Item item = update(new Item(
            "Backstage passes to a TAFKAL80ETC concert", 15, 10));

        assertEquals(11, item.quality);
    }

    @Test
    void backstagePass_increaseBy2_when10DaysOrLess() {
        Item item = update(new Item(
            "Backstage passes to a TAFKAL80ETC concert", 10, 10));

        assertEquals(12, item.quality);
    }

    @Test
    void backstagePass_increaseBy3_when5DaysOrLess() {
        Item item = update(new Item(
            "Backstage passes to a TAFKAL80ETC concert", 5, 10));

        assertEquals(13, item.quality);
    }

    @Test
    void backstagePass_qualityDropsToZeroAfterConcert() {
        Item item = update(new Item(
            "Backstage passes to a TAFKAL80ETC concert", 0, 10));

        assertEquals(0, item.quality);
    }

    // ---------------------------
    // Sulfuras
    // ---------------------------

    @Test
    void sulfuras_neverChangesQuality() {
        Item item = update(new Item(
            "Sulfuras, Hand of Ragnaros", 10, 80));

        assertEquals(80, item.quality);
    }

    @Test
    void sulfuras_neverChangesSellIn() {
        Item item = update(new Item(
            "Sulfuras, Hand of Ragnaros", 10, 80));

        assertEquals(10, item.sellIn);
    }
}