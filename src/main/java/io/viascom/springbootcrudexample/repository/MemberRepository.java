package io.viascom.springbootcrudexample.repository;

import io.viascom.springbootcrudexample.model.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends CrudRepository<MemberEntity, UUID> {

    Optional<MemberEntity> findByUsername(String username);

}
