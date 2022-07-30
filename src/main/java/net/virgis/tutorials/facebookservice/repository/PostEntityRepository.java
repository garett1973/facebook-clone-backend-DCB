package net.virgis.tutorials.facebookservice.repository;

import net.virgis.tutorials.facebookservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostEntityRepository extends JpaRepository<PostEntity, String> {

}

