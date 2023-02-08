package hello.itemservice.dto;

import hello.itemservice.domain.Item;
import lombok.Data;

@Data
public class ItemAddRequestDto {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item toEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
