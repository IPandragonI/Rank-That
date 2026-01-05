package fr.esgi.tierlist.application.form;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TierListForm {
    private Long id;
    private String name;
    private Long creatorId;
    //link items when creating a tierlist
    private List<String> items;
}