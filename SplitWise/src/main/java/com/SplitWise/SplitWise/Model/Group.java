package com.SplitWise.SplitWise.Model;

import jakarta.persistence.*;
import jdk.jfr.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    @Column(unique = true)
    private String groupUserName;
    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> members = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Events> events = new ArrayList<>();
    public void addEvent(Events event) {
        if (events.size() >= 10) {
            throw new RuntimeException("Maximum number of events reached for this group");
        }
        events.add(event);
        event.setGroup(this);
    }
    public void removeEvent(Events event) {
        events.remove(event);
        event.setGroup(null);
    }
}