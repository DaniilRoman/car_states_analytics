package com.app.admin.repository;

import com.app.admin.data.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParameterRepository extends JpaRepository<Parameter, UUID> {
}
