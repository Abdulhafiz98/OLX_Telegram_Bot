package model;

import lombok.*;
import model.base.Base;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Category extends Base {

    private int parentId;
    private String name;

    List<Product> productList = new ArrayList<>();
}
