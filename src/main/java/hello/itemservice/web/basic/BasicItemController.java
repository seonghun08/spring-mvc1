package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import hello.itemservice.dto.ItemAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/basic/items")
@Controller
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long id, Model model) {
        model.addAttribute(itemRepository.findById(id));
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return "basic/addForm";
    }

    /**
     * @ModelAttribute 다음과 같이 생략이 가능하다.
     * => ItemRequestDto itemRequestDto
     * => model.addAttribute("item", item)
     */
//    @PostMapping("/add")
//    public String addItem(ItemAddRequestDto itemAddRequestDto) {
//        Item item = itemRepository.save(itemAddRequestDto.toEntity());
////        model.addAttribute("item", item);
//        return "redirect:/basic/items/" + item.getId();
//    }

    @PostMapping("/add")
    public String addItem(ItemAddRequestDto itemAddRequestDto, RedirectAttributes attributes) {
        Item item = itemRepository.save(itemAddRequestDto.toEntity());

        /**
         * redirect:/basic/items/{itemId}
         * pathVariable 바인딩: {itemId}
         * 나머지는 쿼리 파라미터로 처리: ?status=true
         */
        attributes.addAttribute("itemId", item.getId());
        attributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long id, Model model) {
        model.addAttribute("item", itemRepository.findById(id));
        return "basic/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String editItem(@PathVariable("itemId") Long id, Item item, Model model) {
        itemRepository.update(id, item);
        model.addAttribute("item", item);

        // @PathVariable => {itemId} 매핑된 값을 넣을 수 있도록 지원
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 15000, 30));
        itemRepository.save(new Item("itemB", 12000, 50));
    }
}