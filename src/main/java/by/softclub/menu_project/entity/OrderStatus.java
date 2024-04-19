package by.softclub.menu_project.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum OrderStatus {
    ACCEPTED(1, "Accepted"),
    PREPARING(2, "Preparing"),
    READY(3, "Ready");

    int code;
    String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
