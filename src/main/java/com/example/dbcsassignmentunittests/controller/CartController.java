package com.example.dbcsassignmentunittests.controller;

import com.example.dbcsassignmentunittests.dto.CartDto;
import com.example.dbcsassignmentunittests.model.Cart;
import com.example.dbcsassignmentunittests.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    private Cart save(@RequestBody CartDto dto) {
        return cartService.save(dto);
    }

    @GetMapping
    private List<Cart> getAll() {
        return cartService.getAllSortedByTotal();
    }

    @GetMapping("{id}")
    private Cart get(@PathVariable Long id) {
        return cartService.get(id);
    }

    @DeleteMapping("{id}")
    private String delete(@PathVariable Long id) {
        cartService.delete(id);
        return "Successfuly deleted cart #" + id;
    }
}
