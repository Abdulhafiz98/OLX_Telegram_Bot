package model;

import lombok.*;
import model.base.Base;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Category extends Base {
    private int parentId;
    private String name;

}
