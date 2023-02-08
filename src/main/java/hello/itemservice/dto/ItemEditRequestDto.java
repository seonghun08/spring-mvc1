package hello.itemservice.dto;

import lombok.Data;

@Data
public class ItemEditRequestDto {

    private String itemName;
    private Integer price;
    private Integer quantity;
}
