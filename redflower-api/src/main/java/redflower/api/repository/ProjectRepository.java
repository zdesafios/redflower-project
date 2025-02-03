package redflower.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import redflower.schema.core.ProjectSchema;

@Repository
public interface ProjectRepository extends MongoRepository<ProjectSchema, String> {}
