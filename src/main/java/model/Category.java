package model;

import lombok.*;
import model.base.Base;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Category extends Base {
    private int parentId;
    private String name;

}
