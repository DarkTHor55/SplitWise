package com.SplitWise.SplitWise.Repository;

import com.SplitWise.SplitWise.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User ,Long> {
}
