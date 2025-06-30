package com.demo.service;

import com.demo.dto.ItemRequest;
import com.demo.dto.ItemResponse;

import java.util.List;

public interface ItemService {

    ItemResponse createItem(ItemRequest itemRequest);

    ItemResponse getItemById(Long id);

    List<ItemResponse> getAllItems();

    ItemResponse updateItem(Long id, ItemRequest itemRequest);

    void deleteItem(Long id);
}
