package ru.evgenii.SecurityWithJWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.evgenii.SecurityWithJWT.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
