package com.demo.serviceimpl;

import com.demo.dto.ItemRequest;
import com.demo.dto.ItemResponse;
import com.demo.model.Category;
import com.demo.model.Item;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ItemRepository;
import com.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new item and save it to DB
    @Override
    public ItemResponse createItem(ItemRequest itemRequest) {
        Category category = categoryRepository.findById(itemRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Item item = Item.builder()
                .name(itemRequest.getName())
                .description(itemRequest.getDescription())
                .price(itemRequest.getPrice())
                .stockQuantity(itemRequest.getStockQuantity())
                .category(category)
                .build();

        Item savedItem = itemRepository.save(item);
        return mapToResponse(savedItem);
    }

    // Get single item by ID
    @Override
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return mapToResponse(item);
    }

    // Get all items
    @Override
    public List<ItemResponse> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // Update existing item
    @Override
    public ItemResponse updateItem(Long id, ItemRequest itemRequest) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Category category = categoryRepository.findById(itemRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        item.setPrice(itemRequest.getPrice());
        item.setStockQuantity(itemRequest.getStockQuantity());
        item.setCategory(category);

        Item updated = itemRepository.save(item);
        return mapToResponse(updated);
    }

    // Delete item by ID
    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    // Helper method to convert entity to response DTO
    private ItemResponse mapToResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .categoryName(item.getCategory().getName())
                .build();
    }
}
