package hello.itemservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = Item.builder()
                .itemName("spring")
                .price(15000)
                .quantity(10)
                .build();

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = Item.builder()
                .itemName("item1")
                .price(10000)
                .quantity(10)
                .build();
        Item item2 = Item.builder()
                .itemName("item2")
                .price(15000)
                .quantity(15)
                .build();
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item = Item.builder()
                .itemName("spring")
                .price(15000)
                .quantity(10)
                .build();
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = Item.builder()
                .itemName("JPA")
                .price(25000)
                .quantity(20)
                .build();
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo("JPA");
        assertThat(findItem.getPrice()).isEqualTo(25000);
        assertThat(findItem.getQuantity()).isEqualTo(20);
    }
}