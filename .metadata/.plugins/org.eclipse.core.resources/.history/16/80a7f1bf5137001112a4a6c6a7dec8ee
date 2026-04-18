package com.example.inventory.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class InventoryServiceTest {

    @InjectMocks
    private InventoryService service;

    @Mock
    private InventoryRepository repo;

    private Inventory item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        item = new Inventory();
        item.setId(1);
        item.setItemName("Phone");
        item.setQuantity(5);
        item.setPrice(20000);
    }

    @Test
    void testAddItem() {
        when(repo.save(item)).thenReturn(item);

        Inventory result = service.addItem(item);

        assertNotNull(result);
        assertEquals("Phone", result.getItemName());
        verify(repo, times(1)).save(item);
    }

    @Test
    void testGetAllItems() {
        List<Inventory> list = Arrays.asList(item);

        when(repo.findAll()).thenReturn(list);

        List<Inventory> result = service.getAllItems();

        assertEquals(1, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testUpdateItem() {
        when(repo.save(any(Inventory.class))).thenReturn(item);

        Inventory updated = service.updateItem(1, item);

        assertEquals(1, updated.getId());
        verify(repo, times(1)).save(item);
    }

    @Test
    void testDeleteItem() {
        doNothing().when(repo).deleteById(1);

        service.deleteItem(1);

        verify(repo, times(1)).deleteById(1);
    }
}