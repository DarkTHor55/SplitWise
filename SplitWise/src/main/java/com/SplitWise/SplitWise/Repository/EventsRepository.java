package com.SplitWise.SplitWise.Repository;

import com.SplitWise.SplitWise.Model.Events;
import com.SplitWise.SplitWise.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events,Long> {

    List<Events> findAllByGroup(Group group);
}
