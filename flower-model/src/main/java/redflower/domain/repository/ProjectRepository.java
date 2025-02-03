package redflower.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import redflower.schema.ProjectSchema;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectSchema, String> {}
